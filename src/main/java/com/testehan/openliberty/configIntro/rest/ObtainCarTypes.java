package com.testehan.openliberty.configIntro.rest;

import com.testehan.openliberty.restClient.service.Reader;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Path("obtainCarTypes")
public class ObtainCarTypes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarTypes() throws IOException {

        final String dbFile = "/db/dev/CarTypes.json";
        InputStream inputStream = Reader.class.getResourceAsStream(dbFile);
        String carTypes = null;

        Scanner scanner = new Scanner(inputStream);
        try {
            carTypes = scanner.useDelimiter("\\Z").next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        return carTypes;
    }
}
