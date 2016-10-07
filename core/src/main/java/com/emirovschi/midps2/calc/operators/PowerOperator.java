package com.emirovschi.midps2.calc.operators;

public class PowerOperator extends AbstractOperator
{
    @Override
    public double apply(final double left, final double right)
    {
        return Math.pow(left, right);
    }

    @Override
    protected String getSymbol()
    {
        return "^";
    }
}
