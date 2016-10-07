package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.gui.ButtonRegister;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RegisteredPushButtonTest
{
    @Test
    public void shouldRegisterItSelf() throws Exception
    {
        final ButtonRegister register = mock(ButtonRegister.class);
        final RegisteredPushButton button = new RegisteredPushButton() {};

        button.setRegister(register);

        verify(register).register(button);
    }

}