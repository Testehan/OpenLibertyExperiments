package com.testehan.openliberty.configIntro;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class InventoryConfig {

    /*
        You can specify a default value for a configuration property using the defaultValue parameter in the
        @ConfigProperty annotation. This value is used when a value is not specified in any MicroProfile Config
        configuration source.

        Level 0 : default value from here
        Level 1 : microprofile-config.properties
        Level 2 : environmental variables set in liberty/wlp/etc/server.env
        Level 3 : System properties                 see bootstrap.properties
        Level 4 : variable in server.xml
        Level 5 : appProperties in server.xml


        You can override the default ordinal value of a ConfigSource by adding the config_ordinal property to your
        configuration source file. The configuration source with the highest ordinal value takes precedence.
        When updating the default config ordinal value in the server.xml config source, the value is added just like
        any other variable or appProperties element. For example, <variable name="config_ordinal" value="875" />.
        Otherwise, in the other ConfigSources, set config_ordinal as a property.

        To review, here are the six places to inject properties:
            1.Java annotations @Inject @ConfigProperty with defaultValue in InventoryConfig.java (no ordinal)
            2.Properties file through /META-INF/microprofile-config.properties (default ordinal = 100)
            3.Environment variables through server.env (default ordinal = 300)
            4.System properties through bootstrap.properties (default ordinal = 400)
            5.As a variable element in the server.xml file (ordinal = 500)
            6.As a appProperties property element in the server.xml file (ordinal = 600)

     */
    @Inject
    @ConfigProperty(name="port", defaultValue="9080")
    private int port;

    @Inject
    @ConfigProperty(name = "app.context.root")
    private String contextRoot;

    public int getPort() {
        return port;
    }

    public String getContextRoot(){
        return contextRoot;
    }
}
