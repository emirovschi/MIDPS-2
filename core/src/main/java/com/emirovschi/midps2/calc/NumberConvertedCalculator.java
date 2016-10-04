package com.emirovschi.midps2.calc;

import com.emirovschi.midps2.calc.converters.ConvertException;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.operators.Operator;

import java.util.stream.Stream;

public class NumberConvertedCalculator implements ConvertedCalculator
{
    private final Calculator calculator;

    private final NumberConverter numberConverter;

    public NumberConvertedCalculator(final Calculator calculator, final NumberConverter numberConverter)
    {
        this.calculator = calculator;
        this.numberConverter = numberConverter;
    }

    @Override
    public void clear()
    {
        this.calculator.clear();
    }

    @Override
    public String push(final String number) throws ConvertException
    {
        return Stream.of(number)
                .map(numberConverter::convert)
                .map(calculator::push)
                .map(numberConverter::convert)
                .findFirst()
                .orElse("");
    }

    @Override
    public void push(final Operator operator)
    {
        calculator.push(operator);
    }

    @Override
    public String getOperation()
    {
        return calculator.getOperation().toString(numberConverter);
    }
}
