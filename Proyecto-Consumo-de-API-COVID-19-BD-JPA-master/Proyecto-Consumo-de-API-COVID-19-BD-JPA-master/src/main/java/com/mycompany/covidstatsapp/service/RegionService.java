package com.mycompany.covidstatsapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.covidstatsapp.model.Region;
import com.mycompany.covidstatsapp.repository.RegionRepository;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

@Log4j2
public class RegionService {

    private final RegionRepository regionRepository;
    private final String apiUrl;
    private final String apiKey;
    private final String apiHost;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;

        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
            this.apiUrl = props.getProperty("api.base.url") + "/regions";
            this.apiKey = props.getProperty("api.key");
            this.apiHost = props.getProperty("api.host");
        } catch (IOException e) {
            log.error("Error al leer application.properties", e);
            throw new RuntimeException("No se pudieron cargar las propiedades de la API", e);
        }
    }

    public void fetchAndSaveRegions() {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-RapidAPI-Key", apiKey);
            connection.setRequestProperty("X-RapidAPI-Host", apiHost);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                log.info("!!!Conexion exitosa a regions");
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder responseJson = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        responseJson.append(line);
                    }
                    parseAndSave(responseJson.toString());
                }
            } else {
                log.error("Error en la respuesta regions: " + responseCode);
            }
        } catch (IOException e) {
            log.error("Error al consumir regions", e);
        }
    }

    private void parseAndSave(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode data = root.get("data");

            for (JsonNode node : data) {
                String iso = node.get("iso").asText();
                String regionName = node.get("name").asText();

                boolean exists = regionRepository.existsByIsoAndName(iso, regionName);
                if (!exists) {
                    Region region = new Region();
                    region.setIso(iso);
                    region.setName(regionName);

                    regionRepository.save(region);
                    log.info("!!!Region guardada: {}", regionName);
                } else {
                    log.info("!!!Region ya existe: {}", regionName);
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Error al parsear el JSON de regiones", e);
        }
    }
}