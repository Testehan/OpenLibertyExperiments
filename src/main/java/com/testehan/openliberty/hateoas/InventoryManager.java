package com.testehan.openliberty.hateoas;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@ApplicationScoped
public class InventoryManager {
    private ConcurrentMap<String, JsonObject> inv = new ConcurrentHashMap<>();

    public JsonObject get(String hostname) {
        JsonObject properties = inv.get(hostname);
        if (properties == null) {
            if (InventoryUtil.responseOk(hostname)) {
                properties = InventoryUtil.getProperties(hostname);
                this.add(hostname, properties);
            } else {
                return ReadyJson.SERVICE_UNREACHABLE.getJson();
            }
        }
        return properties;
    }

    public void add(String hostname, JsonObject systemProps) {
        inv.putIfAbsent(hostname, systemProps);
    }

    public JsonObject list() {
        JsonObjectBuilder systems = Json.createObjectBuilder();
        inv.forEach((host, props) -> {
            JsonObject systemProps = Json.createObjectBuilder()
                    .add("os.name", props.getString("os.name"))
                    .add("user.name", props.getString("user.name"))
                    .build();
            systems.add(host, systemProps);
        });
        systems.add("hosts", systems);
        systems.add("total", inv.size());
        return systems.build();
    }

    // method accepts a target URL as an argument and returns a JSON object that contains HATEOAS links.
    public JsonObject getSystems(String url) {
        // inventory content
        JsonObjectBuilder systems = Json.createObjectBuilder();
        systems.add("*", InventoryUtil.buildLinksForHost("*", url));

        // collecting systems jsons
        for (String host : inv.keySet()) {
            systems.add(host, InventoryUtil.buildLinksForHost(host, url));
        }

        return systems.build();
    }
}
