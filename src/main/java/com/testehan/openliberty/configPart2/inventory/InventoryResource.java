package com.testehan.openliberty.configPart2.inventory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("systems4")
public class InventoryResource {


    @Inject
    private InventoryConfig inventoryConfig;

    @GET
    @Path("{hostname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPropertiesForHost(@PathParam("hostname") String hostname) {

        if (!inventoryConfig.isInMaintenance()) {
            return Response.status(Response.Status.OK)
                        .entity("All is good in the realm of the brave on host : " + hostname)
                        .build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("{ \"error\" : \"Service is currently in maintenance. "
                            + "Contact: " + inventoryConfig.getEmail().toString() + "\" }")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listContents() {
        if (!inventoryConfig.isInMaintenance()) {
            return Response.ok("All is good in the realm of the brave").build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("{ \"error\" : \"Service is currently in maintenance. "
                            + "Contact: " + inventoryConfig.getEmail().toString() + "\" }")
                    .build();
        }
    }

}
