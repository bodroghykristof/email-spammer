package com.codecool.emailspammer.network;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailPropertyValues {

    private final String email;
    private final String password;
    private final String configFileName = "config.properties";

    public EmailPropertyValues() throws IOException {

        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + configFileName + "' not found in the classpath");
        }

        this.email = properties.getProperty("server-email");
        this.password = properties.getProperty("server-email-password");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
