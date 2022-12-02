package com.testehan.openliberty.configPart2.system;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("properties")
public class SystemResource {

    @Inject
    private SystemConfig systemConfig;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProperties() {
        if (!systemConfig.isInMaintenance()) {
            return Response.ok(System.getProperties()).build();
        } else {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity("ERROR: Service is currently in maintenance. Contact: " + systemConfig.getEmail().toString())
                    .build();
        }
    }

}
