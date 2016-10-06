package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.gui.ButtonRegister;
import org.apache.pivot.wtk.PushButton;

@SuppressWarnings("unchecked")
public abstract class RegisteredPushButton extends PushButton
{
    public <T extends PushButton> void setRegister(final ButtonRegister<T> register)
    {
        register.register((T) this);
    }
}
