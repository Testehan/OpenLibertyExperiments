package com.testehan.openliberty.restClient.consumingRest;

import com.testehan.openliberty.restClient.model.Album;
import com.testehan.openliberty.restClient.model.Artist;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

public class Consumer {

    // Processing JSON using JSON-B
    /*
        the consumeWithJsonb() method in the Consumer class makes a GET request to the running artist service
        and retrieves the JSON. To bind the JSON into an Artist array, use the Artist[] entity type in the
        readEntity call.
     */
    public static Artist[] consumeWithJsonb(String targetUrl) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(targetUrl).request().get();
        Artist[] artists = response.readEntity(Artist[].class);

        response.close();
        client.close();

        return artists;
    }

    // Processing JSON using JSON-P
    /*
        The consumeWithJsonp() method in the Consumer class makes a GET request to the running artist service
        and retrieves the JSON. This method then uses the collectArtists and collectAlbums helper methods.
        These helper methods will parse the JSON and collect its objects into individual POJOs. Notice that
        you can use the custom constructors to create instances of Artist and Album.
     */
    public static Artist[] consumeWithJsonp(String targetUrl) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(targetUrl).request().get();
        JsonArray arr = response.readEntity(JsonArray.class);

        response.close();
        client.close();

        return Consumer.collectArtists(arr);
    }

    private static Artist[] collectArtists(JsonArray artistArr) {
        List<Artist> artists = artistArr.stream().map(artistJson -> {
            JsonArray albumArr = ((JsonObject) artistJson).getJsonArray("albums");
            Artist artist = new Artist(
                    ((JsonObject) artistJson).getString("name"),
                    Consumer.collectAlbums(albumArr));
            return artist;
        }).collect(Collectors.toList());

        return artists.toArray(new Artist[artists.size()]);
    }

    private static Album[] collectAlbums(JsonArray albumArr) {
        List<Album> albums = albumArr.stream().map(albumJson -> {
            Album album = new Album(
                    ((JsonObject) albumJson).getString("title"),
                    ((JsonObject) albumJson).getString("artist"),
                    ((JsonObject) albumJson).getInt("ntracks"));
            return album;
        }).collect(Collectors.toList());

        return albums.toArray(new Album[albums.size()]);
    }
}
