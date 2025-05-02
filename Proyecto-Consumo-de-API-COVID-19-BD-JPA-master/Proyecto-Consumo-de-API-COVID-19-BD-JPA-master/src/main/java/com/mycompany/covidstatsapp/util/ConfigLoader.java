/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.covidstatsapp.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Clase utilitaria para cargar propiedades desde el archivo application.properties.
 * Se carga solo una vez y se puede acceder desde cualquier parte del proyecto.
 */
public class ConfigLoader {

    private static final Properties props = new Properties();

    static {
        try {
            props.load(ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties"));
            System.out.println(" [ConfigLoader] application.properties cargado correctamente.");
        } catch (IOException e) {
            throw new RuntimeException(" Error cargando application.properties", e);
        }
    }

    /**
     * Obtiene el valor de la propiedad según la clave.
     *
     * @param key Clave de la propiedad (por ejemplo, "covid.report.date")
     * @return Valor asociado o null si no existe.
     */
    public static String get(String key) {
        return props.getProperty(key);
    }
}