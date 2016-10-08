package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.Calculator;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.controls.NumericLabel;
import com.emirovschi.midps2.calc.operators.MultiplyOperator;
import com.emirovschi.midps2.calc.operators.Operator;
import org.apache.pivot.wtk.Label;

public class DefaultGraphicCalculator implements GraphicCalculator
{
    private NumberConverter numberConverter;
    private NumericLabel numericLabel;
    private Calculator calculator;
    private Label currentOperation;
    private Label currentValue;

    @Override
    public void push(final int digit)
    {
        numericLabel.append(digit);
    }

    @Override
    public void push(final Operator operator)
    {
        if (Math.abs(numericLabel.getNumber()) >= 1E-16)
        {
            calculator.push(numericLabel.getNumber());
        }
        calculator.push(operator);
        numericLabel.clear();
        currentOperation.setText(calculator.getOperation().toString(numberConverter));
    }

    @Override
    public void startDecimal()
    {
        numericLabel.startDecimal();
    }

    @Override
    public void calculate()
    {
        if(calculator.getOperation().canPushRight())
        {
            final double result = calculator.push(numericLabel.getNumber());
            numericLabel.clear();
            currentValue.setText(numberConverter.convert(result));
            currentOperation.setText(calculator.getOperation().toString(numberConverter));
        }
    }

    @Override
    public void clear()
    {
        numericLabel.clear();
        calculator.clear();
        currentOperation.setText("");
    }

    @Override
    public void changeSign()
    {
        if (calculator.getOperation().isEmpty() || calculator.getOperation().canPushRight())
        {
            numericLabel.changeSign();
        }
        else
        {
            calculator.push(new MultiplyOperator());
            final double result = calculator.push(-1);
            numericLabel.clear();
            currentValue.setText(numberConverter.convert(result));
            currentOperation.setText(calculator.getOperation().toString(numberConverter));
        }
    }

    public void setNumberConverter(final NumberConverter numberConverter)
    {
        this.numberConverter = numberConverter;
    }

    public void setNumericLabel(final NumericLabel numericLabel)
    {
        this.numericLabel = numericLabel;
    }

    public void setCalculator(final Calculator calculator)
    {
        this.calculator = calculator;
    }

    public void setCurrentOperation(final Label currentOperation)
    {
        this.currentOperation = currentOperation;
    }

    public void setCurrentValue(final Label currentValue)
    {
        this.currentValue = currentValue;
    }
}
