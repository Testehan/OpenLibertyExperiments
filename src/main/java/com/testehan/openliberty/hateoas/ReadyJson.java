package com.testehan.openliberty.hateoas;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public enum ReadyJson {

    SERVICE_UNREACHABLE();

    private JsonObject json;

    public JsonObject getJson() {
        switch (this) {
            case SERVICE_UNREACHABLE:
                this.serviceUnreachable();
                break;
            default:
                break;
        }
        return json;
    }

    private void serviceUnreachable() {
        json = Json.createObjectBuilder()
                .add("ERROR", "Unknown hostname or the resource may not be running on the host machine")
                .build();
    }

}
