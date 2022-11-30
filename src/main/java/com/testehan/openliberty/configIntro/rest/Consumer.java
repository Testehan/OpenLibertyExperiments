package com.testehan.openliberty.configIntro.rest;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class Consumer {
    // this makes a call to @Path("obtainCarTypes") ObtainCarTypes ...the intent being that what if the service that we
    // need is somewhere external etc..
    public static JsonObject retrieveCarTypes (String targetUrl) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(targetUrl).request().get();
        JsonObject payload = response.readEntity(JsonObject.class);
        response.close();
        client.close();

        return payload;
    }
}
