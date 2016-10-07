package com.emirovschi.midps2.calc.operators;

public class RootOperator extends AbstractOperator
{
    @Override
    public double apply(final double left, final double right)
    {
        return Math.pow(left, 1D/right);
    }

    @Override
    protected String getSymbol()
    {
        return "√";
    }
}
