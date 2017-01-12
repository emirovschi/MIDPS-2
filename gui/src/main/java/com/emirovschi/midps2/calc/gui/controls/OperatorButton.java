package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.operators.Operator;

public class OperatorButton extends RegisteredPushButton
{
    private Operator operator;

    public Operator getOperator()
    {
        return operator;
    }

    public void setOperator(final Operator operator)
    {
        this.operator = operator;
    }

    public void setOperator(final String operator) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        this.operator = (Operator) Class.forName(operator).newInstance();
    }
}
