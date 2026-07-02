package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Base {

    //initialize objects for properties file and logger
    protected static Properties properties = new Properties();
    protected static String propertyFilePath = "src/main/resources/properties/config.properties";
    protected static Logger logger = LogManager.getLogger();

    static {
        logger.info("loading properties file...");
        try (BufferedReader br = new BufferedReader(new FileReader(propertyFilePath))) {
            properties.load(br);
        } catch (IOException e) {
            throw new RuntimeException("Could not load property file at path : " + propertyFilePath);
        }
    }




}
