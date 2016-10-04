package com.emirovschi.midps2.calc.converters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class DecimalNumberConverter implements NumberConverter
{
    private static final String PATTERN = "###,##0.###";
    private static final String SCIENTIFIC_PATTERN = "0.0###############E0";
    private static final Double SCIENTIFIC_MIN = 1E16;

    private final DecimalFormat decimalFormat;
    private final DecimalFormat scientificDecimalFormat;

    public DecimalNumberConverter()
    {
        final DecimalFormatSymbols symbols = buildSymbols();

        decimalFormat = new DecimalFormat(PATTERN, symbols);
        decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);

        scientificDecimalFormat = new DecimalFormat(SCIENTIFIC_PATTERN, symbols);
    }

    @Override
    public double convert(final String number)
    {
        try
        {
            return decimalFormat.parse(number).doubleValue();
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
        return Math.abs(number) < SCIENTIFIC_MIN ? decimalFormat.format(number) : scientificDecimalFormat.format(number);
    }
}
