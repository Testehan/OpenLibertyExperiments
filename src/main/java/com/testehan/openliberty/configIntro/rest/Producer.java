package com.testehan.openliberty.configIntro.rest;

import com.testehan.openliberty.configIntro.InventoryConfig;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("carTypes")
public class Producer {
    @Inject
    private InventoryConfig config;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getCarTypes() {
        int port = config.getPort();

        String appContextRoot = config.getContextRoot();
        String restPath = "inventory/obtainCarTypes";
        String url = "http://" + uriInfo.getBaseUri().getHost() + ":" + port + "/" + appContextRoot + "/" + restPath;
        System.out.println("Obtaining car types from URL " + url);
        JsonObject carTypes = Consumer.retrieveCarTypes(url);

        return carTypes;
    }
}
