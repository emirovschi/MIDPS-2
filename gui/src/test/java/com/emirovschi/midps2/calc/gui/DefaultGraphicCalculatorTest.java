package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.Calculator;
import com.emirovschi.midps2.calc.Operation;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.controls.NumericLabel;
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
public class DefaultGraphicCalculatorTest
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
    private DefaultGraphicCalculator graphicCalculator = new DefaultGraphicCalculator();

    @Test
    public void shouldPushDigit() throws Exception
    {
        final int digit = 1;

        graphicCalculator.push(digit);

        verify(numericLabel).append(digit);
    }

    @Test
    public void shouldPushOperator() throws Exception
    {
        final double number = 100;
        final String text = "text";

        final Operator operator = mock(Operator.class);
        final Operation operation = mock(Operation.class);

        when(numericLabel.getNumber()).thenReturn(number);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.toString(numberConverter)).thenReturn(text);

        graphicCalculator.push(operator);

        verify(calculator).push(number);
        verify(calculator).push(operator);
        verify(numericLabel).clear();
        verify(currentOperation).setText(text);
    }

    @Test
    public void shouldPushOperatorWithZeroValue() throws Exception
    {
        final String text = "text";

        final Operator operator = mock(Operator.class);
        final Operation operation = mock(Operation.class);

        when(numericLabel.getNumber()).thenReturn(0D);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.toString(numberConverter)).thenReturn(text);

        graphicCalculator.push(operator);

        verify(calculator, never()).push(anyDouble());
        verify(calculator).push(operator);
        verify(numericLabel).clear();
        verify(currentOperation).setText(text);
    }

    @Test
    public void shouldStartDecimal() throws Exception
    {
        graphicCalculator.startDecimal();

        verify(numericLabel).startDecimal();
    }

    @Test
    public void shouldCalculate() throws Exception
    {
        final double number = 100;
        final double result = 200;
        final String text = "text";
        final String resultText = "resultText";

        final Operation operation = mock(Operation.class);

        when(numericLabel.getNumber()).thenReturn(number);
        when(calculator.push(number)).thenReturn(result);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.canPushRight()).thenReturn(true);
        when(numberConverter.convert(result)).thenReturn(resultText);
        when(operation.toString(numberConverter)).thenReturn(text);

        graphicCalculator.calculate();

        verify(calculator).push(number);
        verify(numericLabel).clear();
        verify(currentValue).setText(resultText);
        verify(currentOperation).setText(text);
    }

    @Test
    public void shouldClear() throws Exception
    {
        graphicCalculator.clear();

        verify(numericLabel).clear();
        verify(calculator).clear();
        verify(currentOperation).setText("");
    }

    @Test
    public void shouldChangeSignForNumericValue() throws Exception
    {
        final Operation operation = mock(Operation.class);

        when(calculator.getOperation()).thenReturn(operation);
        when(operation.canPushRight()).thenReturn(true);

        graphicCalculator.changeSign();

        verify(numericLabel).changeSign();
    }

    @Test
    public void shouldChangeSignByMultiplying() throws Exception
    {
        final double result = -200;
        final String text = "text";
        final String resultText = "resultText";

        final Operation operation = mock(Operation.class);

        when(calculator.push(-1D)).thenReturn(result);
        when(calculator.getOperation()).thenReturn(operation);
        when(operation.isEmpty()).thenReturn(false);
        when(operation.canPushRight()).thenReturn(false);
        when(numberConverter.convert(result)).thenReturn(resultText);
        when(operation.toString(numberConverter)).thenReturn(text);

        graphicCalculator.changeSign();

        verify(calculator).push(any(MultiplyOperator.class));
        verify(calculator).push(-1D);
        verify(numericLabel).clear();
        verify(currentValue).setText(resultText);
        verify(currentOperation).setText(text);
    }

}