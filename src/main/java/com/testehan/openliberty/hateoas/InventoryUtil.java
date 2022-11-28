package com.testehan.openliberty.hateoas;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class InventoryUtil {

    private static final int PORT = 9080;
    private static final String PROTOCOL = "http";
    private static final String SYSTEM_PROPERTIES = "OpenLibertyExperiments/system/properties";

    public static JsonObject getProperties(String hostname) {
        Client client = ClientBuilder.newClient();
        URI propURI = InventoryUtil.buildUri(hostname);
        return client.target(propURI)
                .request(MediaType.APPLICATION_JSON)
                .get(JsonObject.class);
    }

    public static JsonArray buildLinksForHost(String hostname, String invUri)
    {
        JsonArrayBuilder links = Json.createArrayBuilder();

        links.add(Json.createObjectBuilder()
                .add("href", StringUtils.appendIfMissing(invUri, "/") + hostname)
                .add("rel", "self"));

        if (!hostname.equals("*")) {
            links.add(Json.createObjectBuilder()
                    .add("href", InventoryUtil.buildUri(hostname).toString())
                    .add("rel", "properties"));
        }

        return links.build();
    }

    /*
        Although this guide does not cover this case, always make sure that you receive a good response code from a
        service before you link that service. Similarly, make sure that it makes sense for a particular object to access
        a resource it is linked to. For instance, it doesn’t make sense for an account holder to be able to withdraw money
        from their account when their balance is 0. Hence, the account holder should not be linked to a resource that
        provides money withdrawal.

        So this method or something similar should be used to make sure that the returned links are functional, and
        perform other business validations
     */
    public static boolean responseOk(String hostname) {
        try {
            URL target = new URL(buildUri(hostname).toString());
            HttpURLConnection http = (HttpURLConnection) target.openConnection();
            http.setConnectTimeout(50);
            int response = http.getResponseCode();
            return (response != 200) ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    private static URI buildUri(String hostname) {
        return UriBuilder.fromUri(SYSTEM_PROPERTIES)
                .host(hostname)
                .port(PORT)
                .scheme(PROTOCOL)
                .build();
    }
}
