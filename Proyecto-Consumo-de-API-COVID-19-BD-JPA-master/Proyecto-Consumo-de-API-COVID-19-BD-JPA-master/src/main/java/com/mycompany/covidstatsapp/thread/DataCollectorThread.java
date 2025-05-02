package com.mycompany.covidstatsapp.thread;

import com.mycompany.covidstatsapp.model.ExecutedReport;
import com.mycompany.covidstatsapp.repository.ExecutedReportRepository;
import com.mycompany.covidstatsapp.service.ProvinceService;
import com.mycompany.covidstatsapp.service.RegionService;
import com.mycompany.covidstatsapp.service.ReportService;
import com.mycompany.covidstatsapp.util.ConfigLoader;  // Nuevo import

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DataCollectorThread extends Thread {

    private final RegionService regionService;
    private final ProvinceService provinceService;
    private final ReportService reportService;
    private final ExecutedReportRepository executedReportRepository;

    public DataCollectorThread(RegionService regionService, ProvinceService provinceService,
                               ReportService reportService, ExecutedReportRepository executedReportRepository) {
        this.regionService = regionService;
        this.provinceService = provinceService;
        this.reportService = reportService;
        this.executedReportRepository = executedReportRepository;
    }

    @Override
    public void run() {
        String reportDate = ConfigLoader.get("api.date");  
        String iso = ConfigLoader.get("api.country.iso");

    
        log.info(" Valor cargado de 'api.date': {}", reportDate);
        log.info(" Valor cargado de 'api.country.iso': {}", iso);

        try {
            log.info("Inicio del proceso de recoleccion para el pais [{}] en la fecha [{}].", iso, reportDate);
            log.info("Esperando 15 segundos antes de iniciar la conexion con la API...");

            Thread.sleep(15000);

          
            if (executedReportRepository.hasExecuted(reportDate, iso)) {
                log.info("Ejecucion omitida: los datos para el pais [{}] y la fecha [{}] ya fueron procesados previamente.", iso, reportDate);
                return;
            }

          
            log.info("Iniciando consumo de datos para el pais [{}] en la fecha [{}].", iso, reportDate);

            regionService.fetchAndSaveRegions();
            log.info("Datos de regiones procesados correctamente para [{}].", iso);

            provinceService.fetchAndSaveProvinces();
            log.info("Datos de provincias procesados correctamente para [{}].", iso);

            reportService.fetchAndSaveReport();
            log.info("Datos de reportes procesados correctamente para [{}].", iso);

          
            ExecutedReport executedReport = new ExecutedReport();
            executedReport.setExecutionDate(reportDate);
            executedReport.setCountryIso(iso);
            executedReportRepository.save(executedReport);

            log.info("Proceso completado: los datos para el pais [{}] en la fecha [{}] han sido procesados y registrados exitosamente.", iso, reportDate);
            
        } catch (InterruptedException e) {
            log.error("El hilo de recoleccion de datos fue interrumpido durante la ejecucion.", e);
            Thread.currentThread().interrupt();  
        } catch (Exception e) {
            log.error("Error critico durante la recoleccion de datos para el pais [{}] en la fecha [{}].", iso, reportDate, e);
        }
    }
}