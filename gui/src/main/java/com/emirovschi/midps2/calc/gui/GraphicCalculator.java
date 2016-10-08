package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.operators.Operator;

public interface GraphicCalculator
{
    void push(final int digit);

    void push(final Operator operator);

    void startDecimal();

    void calculate();

    void clear();

    void changeSign();
}
