package com.testehan.openliberty.consumeRESTWithTemplateInterface.client;

/*
    Error handling is an important step to ensure that the application can fail safely. If there is an error
    response such as 404 NOT FOUND when invoking the remote service, you need to handle it. First, define an exception,
    and map the exception with the error response code. Then, register the exception mapper in the client interface
*/


import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import java.util.logging.Logger;

@Provider
public class UnknownUriExceptionMapper implements ResponseExceptionMapper<UnknownUriException> {
    // An exception mapper maps various response codes from the remote service to throwable exceptions.

    private Logger LOG = Logger.getLogger(UnknownUriExceptionMapper.class.getName());

    /*
        The handles() method inspects the HTTP response code to determine whether an exception is thrown for the
        specific response, and the toThrowable() method returns the mapped exception.
     */
    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        LOG.info("status = " + status);
        return status == 404;
    }

    @Override
    public UnknownUriException toThrowable(Response response) {
        return new UnknownUriException();
    }
}
