package com.testehan.openliberty.configPart2.system;

import com.testehan.openliberty.configPart2.config.Email;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
public class SystemConfig {

    @Inject
    @ConfigProperty(name = "io_openliberty_guides_system_inMaintenance")
    private Provider<Boolean> inMaintenance;

    @Inject
    @ConfigProperty(name = "io_openliberty_guides_email")
    private Provider<Email> email;


    public boolean isInMaintenance() {
        return inMaintenance.get();
    }

    public Email getEmail() {
        return email.get();
    }

}
