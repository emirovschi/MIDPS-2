package com.emirovschi.midps2.calc.gui;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ArgumentBuilderTest
{
    @Test
    public void get() throws Exception
    {
        final String text = "key1=val1\nkey2=val2";
        final InputStream stream = new ByteArrayInputStream(text.getBytes());

        final ArgumentBuilder build = new ArgumentBuilder(stream);

        final String[] arguments = build.get();

        assertEquals(2, arguments.length);
        assertEquals("--key2=val2", arguments[0]);
        assertEquals("--key1=val1", arguments[1]);
    }

}