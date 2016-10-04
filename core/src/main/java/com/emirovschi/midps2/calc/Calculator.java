package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.operators.Operator;

public interface Calculator
{
    void clear();

    double push(double number);

    void push(Operator operator);

    String getOperation();
}
