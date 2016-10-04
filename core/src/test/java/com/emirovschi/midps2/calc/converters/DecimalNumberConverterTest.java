package com.emirovschi.midps2.calc.converters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DecimalNumberConverterTest
{
    private DecimalNumberConverter converter = new DecimalNumberConverter();

    @Test
    public void shouldReturnNaNOnLiterals() throws Exception
    {
        final String number = "a";

        final double result = converter.convert(number);

        assertTrue(Double.isNaN(result));
    }

    @Test
    public void shouldConvert3DigitNaturalNumber() throws Exception
    {
        final String number = "123";

        final double result = converter.convert(number);

        assertEquals(123, result, 0.0000001);
    }

    @Test
    public void shouldConvertNaturalNumber() throws Exception
    {
        final String number = "123,456";

        final double result = converter.convert(number);

        assertEquals(123456, result, 0.0000001);
    }

    @Test
    public void shouldConvertIntegerNumber() throws Exception
    {
        final String number = "-123,456";

        final double result = converter.convert(number);

        assertEquals(-123456, result, 0.0000001);
    }

    @Test
    public void shouldConvertRealNumber() throws Exception
    {
        final String number = "-123,456.789654";

        final double result = converter.convert(number);

        assertEquals(-123456.789654, result, 0.0000001);
    }

    @Test
    public void shouldConvertBigNumberNumber() throws Exception
    {
        final String number = "1.234567890123456E25";

        final double result = converter.convert(number);

        assertEquals(1.234567890123456E25, result, 0.0000001);
    }

    @Test
    public void shouldConvert3DigitNaturalNumberToString() throws Exception
    {
        final double number = 123;

        final String result = converter.convert(number);

        assertEquals("123", result);
    }

    @Test
    public void shouldConvertNaturalNumberToString() throws Exception
    {
        final double number = 123456;

        final String result = converter.convert(number);

        assertEquals("123,456", result);
    }

    @Test
    public void shouldConvertIntegerNumberToString() throws Exception
    {
        final double number = -123456;

        final String result = converter.convert(number);

        assertEquals("-123,456", result);
    }

    @Test
    public void shouldConvertRealNumberToString() throws Exception
    {
        final double number = -123456.789654;

        final String result = converter.convert(number);

        assertEquals("-123,456.789654", result);
    }

    @Test
    public void shouldConvertBigNumberToString1() throws Exception
    {
        final double number = -12345678.90123456;

        final String result = converter.convert(number);

        assertEquals("-12,345,678.90123456", result);
    }

    @Test
    public void shouldConvertBigNumberToString2() throws Exception
    {
        final double number = -1234567890123456.1234567890123456d;

        final String result = converter.convert(number);

        assertEquals("-1,234,567,890,123,456", result);
    }

    @Test
    public void shouldConvertBigNumberToString3() throws Exception
    {
        final double number = -1234567890123456789012D;

        final String result = converter.convert(number);

        assertEquals("-1.2345678901234568E21", result);
    }

    @Test
    public void shouldConvertInfinityToString() throws Exception
    {
        final double number = Double.POSITIVE_INFINITY;

        final String result = converter.convert(number);

        assertEquals("Overflow", result);
    }

    @Test
    public void shouldConvertNegativeInfinityToString() throws Exception
    {
        final double number = Double.NEGATIVE_INFINITY;

        final String result = converter.convert(number);

        assertEquals("-Overflow", result);
    }

    @Test
    public void shouldConvertNanToString() throws Exception
    {
        final double number = Double.NaN;

        final String result = converter.convert(number);

        assertEquals("Invalid", result);
    }
}