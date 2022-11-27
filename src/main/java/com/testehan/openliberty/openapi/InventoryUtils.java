package com.testehan.openliberty.openapi;

import com.testehan.openliberty.openapi.client.SystemClient;
import com.testehan.openliberty.openapi.client.UnknownUrlException;
import com.testehan.openliberty.openapi.client.UnknownUrlExceptionMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ProcessingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

@ApplicationScoped
public class InventoryUtils {

    @Inject
    @ConfigProperty(name = "app.context.root")
    private String contextRoot;
    private final String SYSTEM_PORT = System.getProperty("default.http.port", "9080");

    public Properties getProperties(String hostname) {
        try {
            String customUrlString = "http://" + hostname + ":" + SYSTEM_PORT + "/" + contextRoot + "/inventory";
            URL customURL = new URL(customUrlString);
            SystemClient customRestClient = RestClientBuilder.newBuilder()
                    .baseUrl(customURL)
                    .register(UnknownUrlExceptionMapper.class)
                    .build(SystemClient.class);
            return customRestClient.getProperties();
        } catch (ProcessingException ex) {
            handleProcessingException(ex);
        } catch (UnknownUrlException ex) {
            System.err.println("The given URL is unreachable.");
        } catch (MalformedURLException ex) {
            System.err.println("The given URL is not formatted correctly: "
                    + ex.getMessage());
        }
        return null;
    }

    public void handleProcessingException(ProcessingException ex) {
        Throwable rootEx = ExceptionUtils.getRootCause(ex);
        if (rootEx != null && rootEx instanceof UnknownHostException) {
            System.err.println("The specified host is unknown: " + ex.getMessage());
        } else {
            throw ex;
        }
    }
}
