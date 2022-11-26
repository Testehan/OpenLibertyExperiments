package com.testehan.openliberty.consumeRESTWithTemplateInterface.client;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Properties;


/*
    The template interface describes the remote service that you want to access (the one from package rest, PropertiesResource).
    The interface defines the resource to access as a method by mapping its annotations, return type,
    list of arguments, and exception declarations.

    The MicroProfile Rest Client feature automatically builds and generates a client implementation based on what is
    defined in the SystemClient interface. There is no need to set up the client and connect with the remote service.

    The @RegisterRestClient annotation registers the interface as a RESTful client. The runtime creates a CDI managed
    bean for every interface that is annotated with the @RegisterRestClient annotation.
*/

@RegisterRestClient(configKey = "systemClient", baseUri = "http://localhost:9080/OpenLibertyExperiments/system")
/*
    The configKey value in the @RegisterRestClient annotation replaces the fully-qualified classname of the properties
    in the microprofile-config.properties configuration file. For example, the <fully-qualified classname>/mp-rest/uri
    property becomes systemClient/mp-rest/uri. The benefit of using Config Keys is when multiple client interfaces have
    the same configKey value, the interfaces can be configured with a single MP config property

    The baseUri value can also be set in the @RegisterRestClient annotation. However, this value will be overridden by
    the base URI property defined in the microprofile-config.properties configuration file, which takes precedence.
    In a production environment, you can use the baseUri variable to specify a different URI for development and
    testing purposes.
 */
@RegisterProvider(UnknownUriExceptionMapper.class)
@Path("/properties")
public interface SystemClient extends AutoCloseable{
/*
    The @RegisterProvider annotation tells the framework to register the provider classes to be used when the framework
    invokes the interface. You can add as many providers as necessary. In the SystemClient interface, add a response
    exception mapper as a provider to map the 404 response code with the UnknownUriException exception.

    TODO will need to see more examples with @RegisterProvider

     When the getProperties() method is invoked, the SystemClient instance sends a GET request to the
     <baseUrl>/properties endpoint, where <baseUrl> is the default base URL of the system service. You will see
     how to configure the base URL in the next section.
*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Properties getProperties() throws UnknownUriException, ProcessingException;
}
