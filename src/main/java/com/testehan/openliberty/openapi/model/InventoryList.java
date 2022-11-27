package com.testehan.openliberty.openapi.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "InventoryList", description = "POJO that represents the inventory contents.")
public class InventoryList {
    @Schema(required = true)
    private List<SystemData> systems;

    public InventoryList(List<SystemData> systems) {
        this.systems = systems;
    }

    public List<SystemData> getSystems() {
        return systems;
    }

    public int getTotal() {
        return systems.size();
    }
}
