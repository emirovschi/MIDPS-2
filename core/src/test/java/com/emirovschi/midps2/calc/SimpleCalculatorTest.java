package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.operators.Operator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleCalculatorTest
{
    private SimpleCalculator calculator;

    private Operator operator;

    @Before
    public void setUp()
    {
        calculator = new SimpleCalculator();

        operator = mock(Operator.class);
        when(operator.getTemplate()).thenReturn("{0} + {1}");
        when(operator.apply(anyDouble(), anyDouble())).then((iom) -> iom.getArgumentAt(0, Double.class) + iom.getArgumentAt(1, Double.class));
    }

    @Test
    public void shouldBeEmpty()
    {
        assertEquals(new Operation(), calculator.getOperation());
    }

    @Test
    public void shouldClear()
    {
        final double left = 10D;

        calculator.push(left);
        calculator.push(operator);
        calculator.clear();

        assertEquals(new Operation(), calculator.getOperation());
    }

    @Test
    public void shouldReturnLeft()
    {
        final double left = 10D;

        final double result = calculator.push(left);

        assertEquals(left, result, 0.001);
        assertEquals(new Operation(left), calculator.getOperation());
    }

    @Test
    public void shouldReturnLeftAndOperator()
    {
        final double left = 10D;

        final double result = calculator.push(left);
        calculator.push(operator);

        assertEquals(left, result, 0.001);
        assertEquals(new Operation(left, operator), calculator.getOperation());
    }

    @Test
    public void shouldReturnLeftAndOperatorAndRight()
    {
        final double left = 10D;
        final double right = 5D;

        calculator.push(left);
        calculator.push(operator);
        final double result = calculator.push(right);

        assertEquals(left + right, result, 0.001);
        assertEquals(new Operation(left, operator, right), calculator.getOperation());
    }

    @Test
    public void shouldAddSecondTime()
    {
        final double left = 10D;
        final double right = 5D;
        final double second = 3D;

        calculator.push(left);
        calculator.push(operator);
        calculator.push(right);
        calculator.push(operator);
        final double result = calculator.push(second);

        assertEquals(left + right + second, result, 0.001);
        assertEquals(new Operation(left + right, operator, second), calculator.getOperation());
    }

    @Test
    public void shouldResetWhenNewNumber()
    {
        final double left = 10D;
        final double right = 5D;
        final double second = 3D;

        calculator.push(left);
        calculator.push(operator);
        calculator.push(right);
        final double result = calculator.push(second);

        assertEquals(second, result, 0.001);
        assertEquals(new Operation(second), calculator.getOperation());
    }
}