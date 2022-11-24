package com.testehan.openliberty.restClient.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

public class Artist {
    public String name;
    public Album[] albums;

    // Object property that does not map to a JSON
    @JsonbTransient
    public boolean legendary = true;

    public Artist() {

    }

    @JsonbCreator
    public Artist(@JsonbProperty("name") String name,
                  @JsonbProperty("albums") Album[] albums) {

        this.name = name;
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album[] getAlbums() {
        return albums;
    }

    public void setAlbums(Album[] albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return name + " wrote " + albums.length + " albums";
    }
}
