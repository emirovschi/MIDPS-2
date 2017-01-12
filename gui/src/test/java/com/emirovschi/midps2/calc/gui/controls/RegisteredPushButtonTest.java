package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.gui.ButtonRegister;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RegisteredPushButtonTest
{
    private RegisteredPushButton button = new RegisteredPushButton() {};

    @Test
    public void shouldRegisterItSelf() throws Exception
    {
        final ButtonRegister register = mock(ButtonRegister.class);

        button.setRegister(register);

        verify(register).register(button);
    }

    @Test
    public void shouldGetEmptyKeySet() throws Exception
    {
        assertEquals(Collections.emptySet(), button.getKeys());
    }

    @Test
    public void shouldSetKeyChar() throws Exception
    {
        button.setKeys("+");

        assertEquals(Sets.newSet('+'), button.getKeys());
    }

    @Test
    public void shouldSetKeySpecial() throws Exception
    {
        button.setKeys("\\13");

        assertEquals(Sets.newSet((char) 13), button.getKeys());
    }

    @Test
    public void shouldSetKeyCharAndSpecial() throws Exception
    {
        button.setKeys("+,\\13");

        assertEquals(Sets.newSet('+', (char) 13), button.getKeys());
    }
}