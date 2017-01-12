package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.converters.NumberConverter;
import org.apache.pivot.wtk.Label;

public class NumericLabel
{
    public static final int MAX_LENGTH = 15;

    private final NumberConverter numberConverter;
    private final Label label;

    private Double number;
    private int decimal;

    public NumericLabel(final NumberConverter numberConverter, final Label label)
    {
        this.numberConverter = numberConverter;
        this.label = label;

        this.number = 0D;
        this.decimal = 0;
    }

    public void clear()
    {
        this.number = 0D;
        this.decimal = 0;

        setText();
    }

    public void append(int digit)
    {
        if(!canAdd())
        {
            return;
        }

        if(number == null)
        {
            this.number = (double) digit;
        }
        else if(decimal > 0)
        {
            number += Math.pow(10, -decimal) * digit;
            decimal++;
        }
        else
        {
            number *= 10;
            number += digit;
        }

        setText();
    }

    public void delete()
    {
        if(number != null)
        {
            if (decimal > 1)
            {
                number -= number * Math.pow(10, decimal - 1) % 10 / Math.pow(10, decimal - 1);
                decimal--;
            }
            else if (decimal == 1)
            {
                decimal --;
            }
            else
            {
                number -= number % 10;
                number /=10;
            }

            setText();
        }
    }

    public String getNumberText()
    {
        if (number == null)
        {
            return "";
        }

        return numberConverter.convert(number, decimal);
    }

    public double getNumber()
    {
        if (decimal > 1)
        {
            final double pow = Math.pow(10, decimal - 1);
            return Math.round(number * pow) / pow;
        }
        return number;
    }

    public void startDecimal()
    {
        if(decimal == 0 && canAdd())
        {
            decimal = 1;
            setText();
        }
    }

    private void setText()
    {
        label.setText(getNumberText());
    }

    private boolean canAdd()
    {
        return getLength() < MAX_LENGTH;
    }

    private int getLength()
    {
        return String.valueOf(Math.round(number)).length() + Math.max(0, decimal - 1);
    }

    public void changeSign()
    {
        number *= -1;
        setText();
    }
}
