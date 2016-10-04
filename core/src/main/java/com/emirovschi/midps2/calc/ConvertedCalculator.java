package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.converters.ConvertException;
import com.emirovschi.midps2.calc.operators.Operator;

public interface ConvertedCalculator
{
    void clear();

    String push(String number) throws ConvertException;

    void push(Operator operator);

    String getOperation();
}
