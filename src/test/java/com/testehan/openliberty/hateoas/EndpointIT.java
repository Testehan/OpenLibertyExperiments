package com.testehan.openliberty.hateoas;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndpointIT {

    private final String SYSTEM_PROPERTIES = "system/properties";
    private final String INVENTORY_HOSTS = "inventory2/hosts";

    private String port;
    private String baseUrl;
    private String contextRoot;

    private Client client;


    @BeforeEach
    public void oneTimeSetup() {
        port = System.getProperty("http.port"); // obtains the port from the pom file property http.port
        contextRoot = System.getProperty("app.context.root");
        baseUrl = "http://localhost:" + port + "/" + contextRoot + "/";

        client = ClientBuilder.newClient();
    }

    @AfterEach
    public void teardown() {
        client.close();
    }

    /**
     * Checks if the HATEOAS link for the inventory contents (hostname=*)
     * is as expected.
     */
    @Test
    @Order(1)
    public void testLinkForInventoryContents() {
        final String testUrl = baseUrl + INVENTORY_HOSTS;
        Response response = this.getResponse(testUrl);
        assertEquals(200, response.getStatus(), "Incorrect response code from " + testUrl);

        JsonObject systems = response.readEntity(JsonObject.class);

        String expected;
        String actual;
        boolean isFound = false;

        if (!systems.isNull("*")) {
            // mark that the correct host info was found
            isFound = true;
            JsonArray links = systems.getJsonArray("*");

            expected = testUrl + "/*";
            actual = links.getJsonObject(0).getString("href");
            assertEquals(expected, actual, "Incorrect href");

            // asserting that rel was correct
            expected = "self";
            actual = links.getJsonObject(0).getString("rel");
            assertEquals(expected, actual, "Incorrect rel");
        }


        // If the hostname '*' was not even found, need to fail the testcase
        assertTrue(isFound, "Could not find system with hostname *");

        response.close();
    }

    /**
     * Checks that the HATEOAS links, with relationships 'self' and 'properties' for
     * a simple localhost system is as expected.
     */
    @Test
    @Order(2)
    public void testLinksForSystem() {
        this.visitLocalhost();

        final String testUrl = baseUrl + INVENTORY_HOSTS;
        Response response = this.getResponse(testUrl);
        assertEquals(200, response.getStatus(), "Incorrect response code from " + testUrl);

        JsonObject systems = response.readEntity(JsonObject.class);

        String expected;
        String actual;
        boolean isHostnameFound = false;


        // Try to find the JSON object for hostname localhost
        if (!systems.isNull("localhost")) {
            isHostnameFound = true;
            JsonArray links = systems.getJsonArray("localhost");

            // testing the 'self' link
            expected = testUrl + "/localhost";
            actual = links.getJsonObject(0).getString("href");
            assertEquals(expected, actual, "Incorrect href");

            expected = "self";
            actual = links.getJsonObject(0).getString("rel");
            assertEquals(expected, actual, "Incorrect rel");

            // testing the 'properties' link
            expected = baseUrl + SYSTEM_PROPERTIES;
            actual = links.getJsonObject(1).getString("href");
            assertEquals(expected, actual, "Incorrect href");

            expected = "properties";
            actual = links.getJsonObject(1).getString("rel");

            assertEquals(expected, actual, "Incorrect rel");
        }


        // If the hostname 'localhost' was not even found, need to fail the testcase
        assertTrue(isHostnameFound, "Could not find system with hostname localhost");
        response.close();

    }

    /**
     * Makes a GET request to localhost at the Inventory service.
     */
    private void visitLocalhost() {
        final String urlToCheck = baseUrl + SYSTEM_PROPERTIES;
        Response response = this.getResponse(urlToCheck);
        assertEquals(200, response.getStatus(), "Incorrect response code from " + urlToCheck);
        response.close();
        Response targetResponse =
                client.target(baseUrl + INVENTORY_HOSTS + "/localhost")
                        .request()
                        .get();
        targetResponse.close();
    }

    /**
     * Returns a Response object for the specified URL.
     */
    private Response getResponse(String url) {
        return client.target(url).request().get();
    }
}
