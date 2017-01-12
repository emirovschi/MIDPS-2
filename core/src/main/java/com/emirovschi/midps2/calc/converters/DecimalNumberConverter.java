package com.emirovschi.midps2.calc.converters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class DecimalNumberConverter implements NumberConverter
{
    private static final String DEFAULT_PATTERN = "###,##0.###";
    private static final String DECIMAL_PATTERN = "###,##0.";
    private static final String SCIENTIFIC_PATTERN = "0.0###############E0";
    private static final Double SCIENTIFIC_MIN = 1E16;

    private final DecimalFormat decimalFormat;
    private final DecimalFormat defaultFormat;
    private final DecimalFormat scientificDecimalFormat;

    public DecimalNumberConverter()
    {
        final DecimalFormatSymbols symbols = buildSymbols();

        decimalFormat = new DecimalFormat(DEFAULT_PATTERN, symbols);
        decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);

        defaultFormat = new DecimalFormat(DEFAULT_PATTERN, symbols);
        defaultFormat.setMaximumFractionDigits(Integer.MAX_VALUE);

        scientificDecimalFormat = new DecimalFormat(SCIENTIFIC_PATTERN, symbols);
    }

    @Override
    public double convert(final String number)
    {
        try
        {
            return defaultFormat.parse(number).doubleValue();
        }
        catch (ParseException e)
        {
            return Double.NaN;
        }
    }

    private static DecimalFormatSymbols buildSymbols()
    {
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(',');
        decimalFormatSymbols.setInfinity("Overflow");
        decimalFormatSymbols.setNaN("Invalid");
        return decimalFormatSymbols;
    }

    @Override
    public String convert(final double number)
    {
        return Math.abs(number) < SCIENTIFIC_MIN ? defaultFormat.format(round(number)) : scientificDecimalFormat.format(number);
    }

    private double round(final double number)
    {
        final long integer = Math.round(number);
        return integer + (Math.round((number - integer) * 1E15) / 1E15);
    }

    @Override
    public String convert(final double number, final int decimals)
    {
        if(decimals == 0 || Math.abs(number) >= SCIENTIFIC_MIN)
        {
            return convert(number);
        }

        decimalFormat.applyPattern(DECIMAL_PATTERN + new String(new char[decimals - 1]).replace("\0", "0"));
        return decimalFormat.format(number);
    }
}
