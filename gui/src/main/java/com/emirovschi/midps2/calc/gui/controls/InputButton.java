package com.emirovschi.midps2.calc.gui.controls;

public class InputButton extends RegisteredPushButton
{
    private int digit;

    public int getDigit()
    {
        return digit;
    }

    public void setDigit(final int digit)
    {
        this.digit = digit;
        setButtonData(digit);
    }
}
