package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.converters.DefaultNumberConverter;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.operators.Operator;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.stream.Stream;

public class Operation
{
    private final Double left;
    private final Operator operator;
    private final Double right;

    public Operation()
    {
        this.left = null;
        this.operator = null;
        this.right = null;
    }

    public Operation(final Double left)
    {
        this.left = left;
        this.operator = null;
        this.right = null;
    }

    public Operation(final Double left, final Operator operator)
    {
        this.left = left;
        this.operator = operator;
        this.right = null;
    }

    public Operation(final Double left, final Operator operator, final Double right)
    {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public String toString(final NumberConverter numberConverter)
    {
        if (left == null)
        {
            return "";
        }
        else if(operator == null)
        {
            return MessageFormat.format("{0}", numberConverter.convert(left));
        }
        else if(right == null)
        {
            return MessageFormat.format(operator.getTemplate(), numberConverter.convert(left), "");
        }
        else
        {
            return MessageFormat.format(operator.getTemplate(), numberConverter.convert(left), numberConverter.convert(right));
        }
    }

    @Override
    public String toString()
    {
        return toString(new DefaultNumberConverter());
    }

    @Override
    public boolean equals(final Object obj)
    {
        return Stream.of(obj)
                .filter(Objects::nonNull)
                .filter(o -> o instanceof Operation)
                .map(o -> (Operation) o)
                .anyMatch(this::equals);
    }

    public boolean equals(final Operation operation)
    {
        return Objects.equals(operation.left, left)
                && Objects.equals(operation.right, right)
                && Objects.equals(operation.operator, operator);
    }
}
