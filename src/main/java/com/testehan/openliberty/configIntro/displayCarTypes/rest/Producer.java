package com.testehan.openliberty.configIntro.displayCarTypes.rest;

import com.testehan.openliberty.configIntro.displayCarTypes.InventoryConfig;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

//@Path("carTypes")
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
        String restPath = "carTypes";
        String url = "http://" + uriInfo.getBaseUri().getHost() + ":" + port + "/" + appContextRoot + "/" + restPath;
        System.out.println("Obtaining car types from URL " + url);
        JsonObject carTypes = Consumer.retrieveCarTypes(url);

        return carTypes;
    }
}
