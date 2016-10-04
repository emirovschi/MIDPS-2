package com.emirovschi.midps2.calc.converters;

public interface NumberConverter
{
    double convert(final String number);

    String convert(final double number);
}
