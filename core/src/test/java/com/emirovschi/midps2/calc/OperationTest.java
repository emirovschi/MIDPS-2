package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.operators.Operator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperationTest
{
    private Operator operator;

    @Before
    public void setUp()
    {
        operator = mock(Operator.class);
        when(operator.getTemplate()).thenReturn("{0} + {1}");
        when(operator.apply(anyDouble(), anyDouble())).then((iom) -> iom.getArgumentAt(0, Double.class) + iom.getArgumentAt(1, Double.class));
    }

    @Test
    public void shouldReturnEmpty()
    {
        final Operation operation = new Operation();

        assertEquals("", operation.toString());
    }

    @Test
    public void shouldReturnLeft()
    {
        final double left = 10D;

        final Operation operation = new Operation(left);

        assertEquals("10.0", operation.toString());
    }

    @Test
    public void shouldReturnLeftAndOperator()
    {
        final double left = 10D;

        final Operation operation = new Operation(left, operator);

        assertEquals("10.0 + ", operation.toString());
    }

    @Test
    public void shouldReturnLeftAndOperatorAndRight()
    {
        final double left = 10D;
        final double right = 5D;

        final Operation operation = new Operation(left, operator, right);

        assertEquals("10.0 + 5.0", operation.toString());
    }

    @Test
    public void equalEmpty()
    {
        assertEquals(new Operation(), new Operation());
    }

    @Test
    public void equalsLeft()
    {
        final double left = 10D;

        assertEquals(new Operation(left), new Operation(left));

        assertNotEquals(new Operation(left), new Operation());
        assertNotEquals(new Operation(left), new Operation(left + 1));
    }

    @Test
    public void equalsLeftAndOperator()
    {
        final double left = 10D;

        assertEquals(new Operation(left, operator), new Operation(left, operator));

        assertNotEquals(new Operation(left, operator), new Operation());
        assertNotEquals(new Operation(left, operator), new Operation(left));
        assertNotEquals(new Operation(left, operator), new Operation(left, mock(Operator.class)));
    }

    @Test
    public void equalsLeftAndOperatorAndRight()
    {
        final double left = 10D;
        final double right = 5D;

        assertEquals(new Operation(left, operator, right), new Operation(left, operator, right));

        assertNotEquals(new Operation(left, operator, right), new Operation());
        assertNotEquals(new Operation(left, operator, right), new Operation(left));
        assertNotEquals(new Operation(left, operator, right), new Operation(left, operator));
        assertNotEquals(new Operation(left, operator, right), new Operation(left, operator, right + 1));
    }

}