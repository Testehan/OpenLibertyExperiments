package com.testehan.openliberty.restClient.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class Album {
    public String title;

    @JsonbProperty("artist")
    public String artistName;

    @JsonbProperty("ntracks")
    public int totalTracks;

    public Album() {
    }

    @JsonbCreator
    public Album(
            @JsonbProperty("title") String title,
            @JsonbProperty("artist") String artistName,
            @JsonbProperty("ntracks") int totalTracks) {

        this.title = title;
        this.artistName = artistName;
        this.totalTracks = totalTracks;
    }

    @Override
    public String toString() {
        return "Album titled " + title + " by " + artistName
                + " contains " + totalTracks + " tracks";
    }
}
