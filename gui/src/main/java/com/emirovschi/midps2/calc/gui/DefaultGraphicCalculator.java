package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.Calculator;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.controls.NumericLabel;
import com.emirovschi.midps2.calc.operators.MultiplyOperator;
import com.emirovschi.midps2.calc.operators.Operator;
import org.apache.pivot.wtk.Label;

import java.util.ArrayList;
import java.util.List;

public class DefaultGraphicCalculator implements GraphicCalculator
{
    private NumberConverter numberConverter;
    private NumericLabel numericLabel;
    private Calculator calculator;
    private Label currentOperation;
    private Label currentValue;
    private List<CalculatorErrorHandler> errorHandlers = new ArrayList<>();

    @Override
    public void push(final int digit)
    {
        numericLabel.append(digit);
        reportRestore();
    }

    @Override
    public void push(final Operator operator)
    {
        if (Math.abs(numericLabel.getNumber()) >= 1E-16)
        {
            final double result = calculator.push(numericLabel.getNumber());
            if(checkError(result))
            {
                return;
            }
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
            if(checkError(result))
            {
                return;
            }
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
        reportRestore();
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

    @Override
    public void addErrorHandler(final CalculatorErrorHandler handler)
    {
        errorHandlers.add(handler);
    }

    private boolean hasError(final double number)
    {
        return Double.isInfinite(number) || Double.isNaN(number);
    }

    private boolean checkError(final double number)
    {
        if(hasError(number))
        {
            numericLabel.clear();
            currentOperation.setText(calculator.getOperation().toString(numberConverter));
            currentValue.setText(numberConverter.convert(number));
            calculator.clear();
            reportError();
            return true;
        }
        return false;
    }

    private void reportError()
    {
        errorHandlers.forEach(CalculatorErrorHandler::onError);
    }

    private void reportRestore()
    {
        errorHandlers.forEach(CalculatorErrorHandler::onRestore);
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
