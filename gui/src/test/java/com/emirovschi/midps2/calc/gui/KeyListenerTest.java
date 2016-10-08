package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.gui.controls.RegisteredPushButton;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KeyListenerTest
{
    private RegisteredPushButton button1, button2;
    private KeyListener keyListener;

    @Before
    public void setUp() throws Exception
    {
        button1 = mock(RegisteredPushButton.class);
        when(button1.getKeys()).thenReturn(Sets.newSet('+','\n'));

        button2 = mock(RegisteredPushButton.class);
        when(button2.getKeys()).thenReturn(Sets.newSet('-','\n'));

        keyListener = new KeyListener();
        keyListener.bind(button1);
        keyListener.bind(button2);
    }

    @Test
    public void shouldPressBindButton1() throws Exception
    {
        keyListener.keyTyped(null, '+');

        verify(button1).press();
        verify(button2, never()).press();
    }

    @Test
    public void shouldPressBindButton2() throws Exception
    {
        keyListener.keyTyped(null, '-');

        verify(button1, never()).press();
        verify(button2).press();
    }

    @Test
    public void shouldPressLastBindButton() throws Exception
    {
        keyListener.keyTyped(null, '\n');

        verify(button1, never()).press();
        verify(button2).press();
    }

    @Test
    public void shouldPressNone() throws Exception
    {
        keyListener.keyTyped(null, '*');

        verify(button1, never()).press();
        verify(button2, never()).press();
    }
}