package com.testehan.openliberty.cdi;

import com.testehan.openliberty.cdi.model.InventoryList;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Properties;

@ApplicationScoped
@Path("/systems")
public class InventoryResource {

    @Inject
    private InventoryManager manager;

    @Inject
    private SystemClient systemClient;

    @GET
    @Path("/{hostname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPropertiesForHost(@PathParam("hostname") String hostname) {
        // Get properties for host
        Properties props = systemClient.getProperties(hostname);
        if (props == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{ \"error\" : \"Unknown hostname " + hostname
                            + " or the inventory service may not be running "
                            + "on the host machine \" }")
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
