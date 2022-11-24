package com.testehan.openliberty.restClient.service;

import jakarta.json.Json;
import jakarta.json.JsonArray;

import java.io.InputStream;

public class Reader {
    public static JsonArray getArtists()
    {
        InputStream inputStream = Reader.class.getResourceAsStream("/artists.json");
        return Json.createReader(inputStream).readArray();

    }
}
