package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.gui.controls.MainWindow;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Keyboard;

import java.io.IOException;

public class Application implements org.apache.pivot.wtk.Application, org.apache.pivot.wtk.Application.UnprocessedKeyHandler
{
    public static final String WINDOW_PROPERTIES = "/window.properties";

    private MainWindow window;

    public static void main(final String[] args) throws IOException
    {
        final ArgumentBuilder argumentBuilder = new ArgumentBuilder(WINDOW_PROPERTIES);
        DesktopApplicationContext.main(Application.class, argumentBuilder.get());
    }

    @Override
    public void startup(final Display display, final Map<String, String> map) throws Exception
    {
        final BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (MainWindow) bxmlSerializer.readObject(MainWindow.class, "/mainWindow.bxml");
        window.open(display);
    }

    @Override
    public boolean shutdown(final boolean b) throws Exception
    {
        window.close();
        return false;
    }

    @Override
    public void suspend() throws Exception
    {

    }

    @Override
    public void resume() throws Exception
    {

    }

    @Override
    public void keyTyped(final char c)
    {
        window.keyTyped(c);
    }

    @Override
    public void keyPressed(final int i, final Keyboard.KeyLocation keyLocation)
    {
        window.keyPressed(i, keyLocation);
    }

    @Override
    public void keyReleased(final int i, final Keyboard.KeyLocation keyLocation)
    {
        window.keyReleased(i, keyLocation);
    }
}
