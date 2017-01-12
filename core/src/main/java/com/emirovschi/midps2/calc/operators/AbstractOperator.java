package com.emirovschi.midps2.calc.operators;

import java.text.MessageFormat;

public abstract class AbstractOperator implements Operator
{
    @Override
    public String getTemplate()
    {
        return MessageFormat.format("'{0}' {0} '{1}'", getSymbol());
    }

    protected abstract String getSymbol();
}
