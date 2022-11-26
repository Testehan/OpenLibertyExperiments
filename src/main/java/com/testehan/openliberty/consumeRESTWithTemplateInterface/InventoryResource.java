package com.testehan.openliberty.consumeRESTWithTemplateInterface;

import com.testehan.openliberty.consumeRESTWithTemplateInterface.model.InventoryList;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Properties;

@RequestScoped
@Path("/systems2")
public class InventoryResource {

    @Inject
    private InventoryManager manager;

    @GET
    @Path("/{hostname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPropertiesForHost(@PathParam("hostname") String hostname) {
        // Get properties
        Properties props = manager.get(hostname);
        if (props == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{ \"error\" : \"Unknown hostname or the system service "
                            + "may not be running on " + hostname + "\" }")
                    .build();
        }

        // Add to inventory
        manager.add(hostname, props);
        return Response.ok(props).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public InventoryList listContents() {
        return manager.list();
    }
}
