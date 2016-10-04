package com.emirovschi.midps2.calc.gui;

import org.apache.pivot.wtk.DesktopApplicationContext;

import java.io.IOException;

public class Application
{
    public static final String WINDOW_PROPERTIES = "/window.properties";

    public static void main(final String[] args) throws IOException
    {
        final ArgumentBuilder argumentBuilder = new ArgumentBuilder(WINDOW_PROPERTIES);
        DesktopApplicationContext.main(MainWindow.class, argumentBuilder.get());
    }
}
