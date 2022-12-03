package com.testehan.openliberty.servlet;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloServletIT {

    private static String siteURL;

    private Client client;
    private Response response;

    @BeforeAll
    public static void init() {
        String port = System.getProperty("http.port");
        String war = System.getProperty("app.context.root");
        siteURL = "http://localhost:" + port + "/" + war + "/" + "servlet";
    }

    @BeforeEach
    public void setup() {
        client = ClientBuilder.newClient();
    }

    @AfterEach
    public void teardown() {
        client.close();
        response.close();
    }

    @Test
    public void testServletGet() throws Exception {
        response = client.target(siteURL).request().get();
        this.assertResponse(siteURL, response);

        String servletContent = response.readEntity(String.class);

        assertEquals("Hello! How are you today?", servletContent, "Unexpected response body");

    }

    @Test
    public void testServletPost() throws Exception {
        response = client.target(siteURL).request().post(Entity.text("ABC"));
        this.assertResponse(siteURL, response);

        String servletContent = response.readEntity(String.class);

        assertEquals("Hello! How are you today? This is post method :ABC", servletContent, "Unexpected response body");

    }



    private void assertResponse(final String url, final Response response) {
        assertEquals(200, response.getStatus(), "Incorrect response code from " + url);
    }
}
