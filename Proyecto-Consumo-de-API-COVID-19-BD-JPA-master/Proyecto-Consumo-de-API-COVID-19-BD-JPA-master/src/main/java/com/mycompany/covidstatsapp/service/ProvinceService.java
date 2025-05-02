package com.mycompany.covidstatsapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.covidstatsapp.model.Province;
import com.mycompany.covidstatsapp.repository.ProvinceRepository;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

@Log4j2
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final String apiUrl;
    private final String apiKey;
    private final String apiHost;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;

        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
           String iso = props.getProperty("api.country.iso");  
            this.apiUrl = props.getProperty("api.base.url") + "/provinces?iso=" + iso;
            this.apiKey = props.getProperty("api.key");
            this.apiHost = props.getProperty("api.host");
        } catch (IOException e) {
            log.error("Error al leer application.properties", e);
            throw new RuntimeException("No se pudieron cargar las propiedades de la API", e);
        }
    }

    public void fetchAndSaveProvinces() {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-RapidAPI-Key", apiKey);
            connection.setRequestProperty("X-RapidAPI-Host", apiHost);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                log.info("!!!Conexion exitosa a provinces");
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder responseJson = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        responseJson.append(line);
                    }
                    parseAndSave(responseJson.toString());
                }
            } else {
                log.error("Error en la respuesta provinces: " + responseCode);
            }
        } catch (IOException e) {
            log.error("Error al consumir provinces", e);
        }
    }

    private void parseAndSave(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode data = root.get("data");

            for (JsonNode node : data) {
                String iso = node.get("iso").asText();
                String provinceName = node.get("province").asText();

                boolean exists = provinceRepository.existsByIsoAndProvince(iso, provinceName);
                if (!exists) {
                    Province province = new Province();
                    province.setIso(iso);
                    province.setName(node.get("name").asText());
                    province.setProvince(provinceName);
                    province.setLat(node.get("lat").asText());
                    province.setLng(node.get("long").asText());

                    provinceRepository.save(province);
                    log.info("!!!Provincia guardada: {}", province.getName());
                } else {
                    log.info("Provincia ya existe: {}", provinceName);
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Error al parsear el JSON de provincias", e);
        }
    }
}