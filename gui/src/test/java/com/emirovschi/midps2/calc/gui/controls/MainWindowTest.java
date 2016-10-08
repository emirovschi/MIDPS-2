package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.Calculator;
import com.emirovschi.midps2.calc.Operation;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.KeyListener;
import com.emirovschi.midps2.calc.operators.MultiplyOperator;
import com.emirovschi.midps2.calc.operators.Operator;
import org.apache.pivot.wtk.Label;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainWindowTest
{
    @Mock
    private NumberConverter numberConverter;

    @Mock
    private NumericLabel numericLabel;

    @Mock
    private Calculator calculator;

    @Mock
    private Label currentOperation;

    @Mock
    private Label currentValue;

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

        verify(numericLabel).append(digit);
    }

    @Test
    public void shouldRegisterDecimalButton() throws Exception
    {
        final DecimalButton button = new DecimalButton();

        mainWindow.register(button);
        button.press();

        verify(numericLabel).startDecimal();
    }

    @Test
    public void shouldRegisterClearButton() throws Exception
    {
        final ClearButton button = new ClearButton();

        mainWindow.register(button);
        button.press();

        verify(numericLabel).clear();
        verify(calculator).clear();
        verify(currentOperation).setText("");
    }

    @Test
    public void shouldRegisterOperatorButton() throws Exception
    {
        final double number = 100;
        final String text = "text";

        final Operator operator = mock(Operator.class);
        final Operation operation = mock(Operation.class);

        final OperatorButton button = new OperatorButton();
        button.setOperator(operator);

        when(numericLabel.getNumber()).thenReturn(number);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.toString(numberConverter)).thenReturn(text);

        mainWindow.register(button);
        button.press();

        verify(calculator).push(number);
        verify(calculator).push(operator);
        verify(numericLabel).clear();
        verify(currentOperation).setText(text);
    }

    @Test
    public void shouldRegisterOperatorButtonWithZeroValue() throws Exception
    {
        final String text = "text";

        final Operator operator = mock(Operator.class);
        final Operation operation = mock(Operation.class);

        final OperatorButton button = new OperatorButton();
        button.setOperator(operator);

        when(numericLabel.getNumber()).thenReturn(0D);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.toString(numberConverter)).thenReturn(text);

        mainWindow.register(button);
        button.press();

        verify(calculator, never()).push(anyDouble());
        verify(calculator).push(operator);
        verify(numericLabel).clear();
        verify(currentOperation).setText(text);
    }

    @Test
    public void shouldRegisterEqualsButton() throws Exception
    {
        final double number = 100;
        final double result = 200;
        final String text = "text";
        final String resultText = "resultText";

        final Operation operation = mock(Operation.class);

        final EqualsButton button = new EqualsButton();

        when(numericLabel.getNumber()).thenReturn(number);
        when(calculator.push(number)).thenReturn(result);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.canPushRight()).thenReturn(true);
        when(numberConverter.convert(result)).thenReturn(resultText);
        when(operation.toString(numberConverter)).thenReturn(text);

        mainWindow.register(button);
        button.press();

        verify(calculator).push(number);
        verify(numericLabel).clear();
        verify(currentValue).setText(resultText);
        verify(currentOperation).setText(text);
    }

    @Test
    public void shouldRegisterChangeSignButtonToChangeNumericValue() throws Exception
    {
        final Operation operation = mock(Operation.class);

        final ChangeSignButton button = new ChangeSignButton();

        when(calculator.getOperation()).thenReturn(operation);
        when(operation.canPushRight()).thenReturn(true);

        mainWindow.register(button);
        button.press();

        verify(numericLabel).changeSign();
    }

    @Test
    public void shouldRegisterChangeSignButtonToMultiply() throws Exception
    {
        final double result = -200;
        final String text = "text";
        final String resultText = "resultText";

        final Operation operation = mock(Operation.class);

        final ChangeSignButton button = new ChangeSignButton();

        when(calculator.push(-1D)).thenReturn(result);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.isEmpty()).thenReturn(false);
        when(operation.canPushRight()).thenReturn(false);
        when(numberConverter.convert(result)).thenReturn(resultText);
        when(operation.toString(numberConverter)).thenReturn(text);

        mainWindow.register(button);
        button.press();

        verify(calculator).push(any(MultiplyOperator.class));
        verify(calculator).push(-1D);
        verify(numericLabel).clear();
        verify(currentValue).setText(resultText);
        verify(currentOperation).setText(text);
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