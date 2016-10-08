package com.emirovschi.midps2.calc.gui;

import org.apache.pivot.wtk.Button;

import java.util.ArrayList;
import java.util.List;

public class DisableButtonErrorHandler implements CalculatorErrorHandler
{
    private final List<Button> buttons = new ArrayList<>();

    @Override
    public void onError()
    {
        buttons.forEach(b -> b.setEnabled(false));
    }

    @Override
    public void onRestore()
    {
        buttons.forEach(b -> b.setEnabled(true));
    }

    public void add(final Button button)
    {
        this.buttons.add(button);
    }
}
