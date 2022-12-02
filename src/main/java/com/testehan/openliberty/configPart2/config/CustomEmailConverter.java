package com.testehan.openliberty.configPart2.config;

import org.eclipse.microprofile.config.spi.Converter;

/*
    Configuration values are purely Strings. MicroProfile Config API has built-in converters that automatically converts
    configured Strings into target types such as int, Integer, boolean, Boolean, float, Float, double and Double.
    Therefore, in the previous section, it is type-safe to directly set the variable type to Provider<Boolean>.
    To convert configured Strings to an arbitrary class type, such as the Email class type

    Remember that you need to register the implementation in a file similar to this :
    src/main/resources/META-INF/services/org.eclipse.microprofile.config.spi.Converter
*/
public class CustomEmailConverter implements Converter<Email> {

    @Override
    public Email convert(String value) {
        return new Email(value);
    }

}
