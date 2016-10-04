package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.operators.Operator;

import java.text.MessageFormat;

public class SimpleCalculator implements Calculator
{
    private Double left, right;

    private Operator operator;

    @Override
    public void clear()
    {
        left = null;
        right = null;
        operator = null;
    }

    @Override
    public double push(final double number)
    {
        if (this.right != null)
        {
            clear();
        }

        if (this.left == null || this.operator == null)
        {
            return this.left = number;
        }
        else
        {
            this.right = number;
            return operator.apply(this.left, this.right);
        }
    }

    @Override
    public void push(final Operator operator)
    {
        if (this.right != null)
        {
            this.left = this.operator.apply(this.left, this.right);
        }

        this.operator = operator;
        this.right = null;
    }

    @Override
    public String getOperation()
    {
        if(left == null)
        {
            return "";
        }
        else if(operator == null)
        {
            return MessageFormat.format("{0}", left);
        }
        else if(right == null)
        {
            return MessageFormat.format(operator.getTemplate(), left, "");
        }
        else
        {
            return MessageFormat.format(operator.getTemplate(), left, right);
        }
    }
}
