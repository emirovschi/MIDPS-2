package com.emirovschi.midps2.calc.gui;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

import java.net.URL;

public class MainWindow extends Window implements Application, Bindable
{
    @Override
    public void initialize(final Map<String, Object> map, final URL url, final Resources resources)
    {
        
    }

    @Override
    public void startup(final Display display, final Map<String, String> map) throws Exception
    {
        final BXMLSerializer bxmlSerializer = new BXMLSerializer();
        final Window window = (Window) bxmlSerializer.readObject(MainWindow.class, "/mainWindow.bxml");
        window.open(display);
    }

    @Override
    public boolean shutdown(final boolean b) throws Exception
    {
        this.close();
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
}
