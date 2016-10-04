package com.emirovschi.midps2.calc.converters;

public class DefaultNumberConverter implements NumberConverter
{
    @Override
    public double convert(final String number)
    {
        return Double.parseDouble(number);
    }

    @Override
    public String convert(final double number)
    {
        return Double.toString(number);
    }
}
