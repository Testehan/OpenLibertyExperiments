package com.testehan.openliberty.openapi;

import com.testehan.openliberty.openapi.model.InventoryList;
import com.testehan.openliberty.openapi.model.SystemData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@ApplicationScoped
public class InventoryManager {

    private List<SystemData> systems = Collections.synchronizedList(new ArrayList<>());
    @Inject
    private InventoryUtils invUtils;

    public Properties get(String hostname) {
        return invUtils.getProperties(hostname);
    }

    public void add(String hostname, Properties systemProps) {
        Properties props = new Properties();
        props.setProperty("os.name", systemProps.getProperty("os.name"));
        props.setProperty("user.name", systemProps.getProperty("user.name"));

        SystemData system = new SystemData(hostname, props);
        if (!systems.contains(system)) {
            systems.add(system);
        }
    }

    public InventoryList list() {
        return new InventoryList(systems);
    }
}
