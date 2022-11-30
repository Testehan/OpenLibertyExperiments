package com.testehan.openliberty.configIntro.displayCarTypes.rest;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class Consumer {
    public static JsonObject retrieveCarTypes (String targetUrl) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(targetUrl).request().get();
        JsonObject payload = response.readEntity(JsonObject.class);
        response.close();
        client.close();

        return payload;
    }
}
