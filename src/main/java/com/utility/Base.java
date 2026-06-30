package com.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Base {

    //load properties file
    protected static Properties properties = new Properties();
    protected static String propertyFilePath = "src/main/resources/properties/config.properties";

    static {
        try (BufferedReader br = new BufferedReader(new FileReader(propertyFilePath))) {
            properties.load(br);
        } catch (IOException e) {
            throw new RuntimeException("Could not load property file at path : " + propertyFilePath);
        }
    }




}
