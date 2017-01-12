package com.emirovschi.midps2.calc.operators;

public class MultiplyOperator extends AbstractOperator
{
    @Override
    public double apply(final double left, final double right)
    {
        return left * right;
    }

    @Override
    protected String getSymbol()
    {
        return "*";
    }
}
