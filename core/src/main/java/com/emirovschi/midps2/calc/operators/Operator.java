package com.emirovschi.midps2.calc.operators;

public interface Operator
{
    double apply(double left, double right);

    String getTemplate();
}
