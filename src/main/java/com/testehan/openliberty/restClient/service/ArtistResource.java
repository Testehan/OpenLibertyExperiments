package com.testehan.openliberty.restClient.service;

import com.testehan.openliberty.restClient.consumingRest.Consumer;
import com.testehan.openliberty.restClient.model.Artist;
import jakarta.json.JsonArray;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import java.util.Arrays;
import java.util.Optional;

@Path("artists")
public class ArtistResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getArtists() {
        return Reader.getArtists();
    }

    @GET
    @Path("jsonString")
    @Produces(MediaType.TEXT_PLAIN)
    public String getJsonString() {
        Jsonb jsonb = JsonbBuilder.create();

        // we consume our own exposed artists json
        Artist[] artists = Consumer.consumeWithJsonb(uriInfo.getBaseUri().toString()+ "artists");

        // and transform it into a string that we return
        String result = jsonb.toJson(artists);
        return result;
    }

    @GET
    @Path("total/{artist}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTotalAlbums(@PathParam("artist") String artist){
        Artist[] artists = Consumer.consumeWithJsonb(uriInfo.getBaseUri().toString()+ "artists");
        final int numberOfAlbums;

        Optional<Artist> foundArtist = Arrays.stream(artists).filter(a -> a.getName().equalsIgnoreCase(artist)).findAny();

        if (foundArtist.isPresent()){
            numberOfAlbums = foundArtist.get().getAlbums().length;
        } else {
            numberOfAlbums = -1;
            System.out.println("Artist "+ artist + " was not found in the JSON ");
        }

        return numberOfAlbums;
    }

    @GET
    @Path("total")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTotalNumberOfArtists(){
        return Consumer.consumeWithJsonp(uriInfo.getBaseUri().toString()+ "artists").length;
    }
}
