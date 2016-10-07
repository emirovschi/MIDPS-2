package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.gui.ButtonRegister;
import org.apache.pivot.wtk.PushButton;

@SuppressWarnings("unchecked")
public abstract class RegisteredPushButton extends PushButton
{
    public void setRegister(final ButtonRegister register)
    {
        register.register(this);
    }
}
