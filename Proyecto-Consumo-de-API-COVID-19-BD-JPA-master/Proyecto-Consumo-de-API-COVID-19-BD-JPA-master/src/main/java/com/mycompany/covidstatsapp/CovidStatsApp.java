
package com.mycompany.covidstatsapp;

import com.mycompany.covidstatsapp.config.EntityManagerFactoryProvider;
import com.mycompany.covidstatsapp.repository.*;
import com.mycompany.covidstatsapp.service.*;
import com.mycompany.covidstatsapp.thread.DataCollectorThread;

import javax.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CovidStatsApp {

    public static void main(String[] args) {
        EntityManager entityManager = null;

        try {
            entityManager = EntityManagerFactoryProvider.getEntityManager();

            RegionRepository regionRepository = new RegionRepository(entityManager);
            ProvinceRepository provinceRepository = new ProvinceRepository(entityManager);
            ReportRepository reportRepository = new ReportRepository(entityManager);
            ExecutedReportRepository executedReportRepository = new ExecutedReportRepository(entityManager);

            RegionService regionService = new RegionService(regionRepository);
            ProvinceService provinceService = new ProvinceService(provinceRepository);
            ReportService reportService = new ReportService(reportRepository);

            
            log.info("Ejecutando fetch desde la API...");
            DataCollectorThread dataCollectorThread = new DataCollectorThread(
                    regionService,
                    provinceService,
                    reportService,
                    executedReportRepository
            );
            dataCollectorThread.start();
            dataCollectorThread.join();
            log.info("Fetch finalizado.");

            
            String fecha = "2021-01-05";  
            String iso = "USA";           

            log.info("Consultando datos guardados para fecha={} e ISO={}...", fecha, iso);
            reportService.consultarPorFechaYIso(fecha, iso);

        } catch (InterruptedException e) {
            log.error("Error en la aplicación: {}", e.getMessage(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            log.info("Aplicación finalizada.");
        }
    }
}