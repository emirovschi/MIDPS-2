package com.emirovschi.midps2.calc.gui;

import org.apache.pivot.wtk.Button;

public interface ButtonRegister<T extends Button>
{
    void register(final T button);
}
