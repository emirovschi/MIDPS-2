package com.emirovschi.midps2.calc.gui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ArgumentBuilder
{
    private final Properties properties;

    public ArgumentBuilder(final String file) throws IOException
    {
        this(ArgumentBuilder.class.getResourceAsStream(file));
    }

    public ArgumentBuilder(final InputStream inputStream) throws IOException
    {
        properties = new Properties();
        properties.load(inputStream);
    }

    public String[] get()
    {
        return properties.keySet()
                .stream()
                .map(Object::toString)
                .map(this::buildProperty)
                .toArray(String[]::new);
    }

    private String buildProperty(final String key)
    {
        return "--" + key + "=" + properties.get(key);
    }
}
