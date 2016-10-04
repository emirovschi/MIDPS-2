package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.operators.Operator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NumberConvertedCalculatorTest
{
    private Calculator calculator;

    private NumberConverter numberConverter;

    private NumberConvertedCalculator numberConvertedCalculator;

    @Before
    public void setUp()
    {
        calculator = mock(Calculator.class);
        numberConverter = mock(NumberConverter.class);

        numberConvertedCalculator = new NumberConvertedCalculator(calculator, numberConverter);
    }

    @Test
    public void shouldClear() throws Exception
    {
        numberConvertedCalculator.clear();

        verify(calculator).clear();
    }

    @Test
    public void shouldPushTerm() throws Exception
    {
        final String number = "number";
        final double convertedNumber = 10D;
        final double result = 15D;
        final String convertedResult = "result";

        when(numberConverter.convert(number)).thenReturn(convertedNumber);
        when(calculator.push(convertedNumber)).thenReturn(result);
        when(numberConverter.convert(result)).thenReturn(convertedResult);

        final String returnedResult = numberConvertedCalculator.push("number");

        assertEquals(convertedResult, returnedResult);
    }

    @Test
    public void shouldPushOperator() throws Exception
    {
        final Operator operator = mock(Operator.class);

        numberConvertedCalculator.push(operator);

        verify(calculator).push(operator);
    }

    @Test
    public void shouldGetOperator() throws Exception
    {
        final String text = "text";
        final Operation operation = mock(Operation.class);

        when(calculator.getOperation()).thenReturn(operation);
        when(operation.toString(numberConverter)).thenReturn(text);

        final String result = numberConvertedCalculator.getOperation();

        assertEquals(text, result);
    }

}