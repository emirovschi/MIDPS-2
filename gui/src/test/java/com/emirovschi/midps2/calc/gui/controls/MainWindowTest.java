package com.emirovschi.midps2.calc.gui.controls;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainWindowTest
{
    private MainWindow mainWindow = new MainWindow();

    @Test
    public void shouldRegisterInputButton() throws Exception
    {
        final int digit = 1;
        final InputButton button = new InputButton();
        final NumericLabel numericLabel = mock(NumericLabel.class);

        mainWindow.setNumericLabel(numericLabel);
        button.setDigit(digit);

        mainWindow.register(button);
        button.press();

        verify(numericLabel).append(digit);
    }

    @Test
    public void shouldRegisterDecimalButton() throws Exception
    {
        final int digit = 1;
        final DecimalButton button = new DecimalButton();
        final NumericLabel numericLabel = mock(NumericLabel.class);

        mainWindow.setNumericLabel(numericLabel);

        mainWindow.register(button);
        button.press();

        verify(numericLabel).startDecimal();
    }

}