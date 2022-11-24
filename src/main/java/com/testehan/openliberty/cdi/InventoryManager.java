package com.testehan.openliberty.cdi;

import com.testehan.openliberty.cdi.model.InventoryList;
import com.testehan.openliberty.cdi.model.SystemData;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@ApplicationScoped
public class InventoryManager {

    private List<SystemData> systems = Collections.synchronizedList(new ArrayList<>());

    public void add(String hostname, Properties systemProps) {

        SystemData system = new SystemData(hostname, systemProps);
        if (!systems.contains(system)) {
            systems.add(system);
        }
    }

    public InventoryList list() {
        return new InventoryList(systems);
    }
}
