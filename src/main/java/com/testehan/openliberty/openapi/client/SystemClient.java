package com.testehan.openliberty.openapi.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Properties;

@RegisterRestClient
@RegisterProvider(UnknownUrlExceptionMapper.class)
@Path("/properties")
public interface SystemClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Properties getProperties() throws UnknownUrlException, ProcessingException;

}
