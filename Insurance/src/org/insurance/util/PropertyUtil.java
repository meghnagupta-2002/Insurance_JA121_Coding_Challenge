package org.insurance.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString() {
    	Properties properties = new Properties();
        // get db.properties path and open db.properties file
    	try (FileInputStream fileInputStream = new FileInputStream(DBPropertyUtil.getPropertyFilePath())) {
            properties.load(fileInputStream);
        } catch (IOException err) {
            err.printStackTrace();
            throw new RuntimeException("Failed to load property file.", err);
        }

        // Extract url, username, and password from db.properties
    	String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        // format the connection URL
        return url + "?user=" + username + "&password=" + password;
    }
}

