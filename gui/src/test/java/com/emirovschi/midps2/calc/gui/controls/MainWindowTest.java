package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.gui.GraphicCalculator;
import com.emirovschi.midps2.calc.gui.KeyListener;
import com.emirovschi.midps2.calc.operators.Operator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainWindowTest
{
    @Mock
    private GraphicCalculator graphicCalculator;

    @InjectMocks
    private MainWindow mainWindow = new MainWindow();

    @Test
    public void shouldRegisterInputButton() throws Exception
    {
        final int digit = 1;
        final InputButton button = new InputButton();
        button.setDigit(digit);

        mainWindow.register(button);
        button.press();

        verify(graphicCalculator).push(digit);
    }

    @Test
    public void shouldRegisterDecimalButton() throws Exception
    {
        final DecimalButton button = new DecimalButton();

        mainWindow.register(button);
        button.press();

        verify(graphicCalculator).startDecimal();
    }

    @Test
    public void shouldRegisterClearButton() throws Exception
    {
        final ClearButton button = new ClearButton();

        mainWindow.register(button);
        button.press();

        verify(graphicCalculator).clear();
    }

    @Test
    public void shouldRegisterOperatorButton() throws Exception
    {
        final Operator operator = mock(Operator.class);
        final OperatorButton button = new OperatorButton();
        button.setOperator(operator);

        mainWindow.register(button);
        button.press();

        verify(graphicCalculator).push(operator);
    }

    @Test
    public void shouldRegisterEqualsButton() throws Exception
    {
        final EqualsButton button = new EqualsButton();

        mainWindow.register(button);
        button.press();

        verify(graphicCalculator).calculate();
    }

    @Test
    public void shouldRegisterChangeSignButton() throws Exception
    {
        final ChangeSignButton button = new ChangeSignButton();

        mainWindow.register(button);
        button.press();

        verify(graphicCalculator).changeSign();
    }

    @Test
    public void shouldAddKeyBinding() throws Exception
    {
        final RegisteredPushButton button = mock(RegisteredPushButton.class);
        final KeyListener keyListener = mock(KeyListener.class);

        mainWindow.setKeyListener(keyListener);
        mainWindow.register(button);

        verify(keyListener).bind(button);
    }
}