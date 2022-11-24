package com.testehan.openliberty.cdi;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Properties;

@ApplicationScoped
public class SystemClient {

    @Inject
    @ConfigProperty(name = "system.http.port")
    private String SYS_HTTP_PORT;

    // Wrapper function that gets properties
    public Properties getProperties(String hostname) {
        Properties properties = new Properties();
        properties.put(hostname, SYS_HTTP_PORT);
        return properties;
    }

    @PostConstruct
    private void postConstruct(){
        System.out.println("SystemClient - PostConstruct");
    }

    @PreDestroy
    private void preDestroy(){
        System.out.println("SystemClient - PreDestroy");
    }
}
