package com.testehan.openliberty.configPart2.config;

public class Email {
    private String name;
    private String domain;

    public Email(String value) {
        String[] components = value.split("@");
        if (components.length == 2) {
            name = components[0];
            domain = components[1];
        } else {
            // no point in having an email object created with empty fields
            throw new IllegalArgumentException("Provided value is not an email");
        }
    }

    public String getEmailName() {
        return name;
    }

    public String getEmailDomain() {
        return domain;
    }

    public String toString() {
        return name + "@" + domain;
    }
}
