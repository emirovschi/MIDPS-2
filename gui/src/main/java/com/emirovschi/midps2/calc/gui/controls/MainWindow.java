package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.Calculator;
import com.emirovschi.midps2.calc.SimpleCalculator;
import com.emirovschi.midps2.calc.converters.DecimalNumberConverter;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.ButtonRegister;
import com.emirovschi.midps2.calc.matcher.Matcher;
import com.emirovschi.midps2.calc.operators.Operator;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

import java.net.URL;
import java.util.function.Consumer;

public class MainWindow extends Window implements Bindable, ButtonRegister
{
    private NumberConverter numberConverter;
    private NumericLabel numericLabel;
    private Calculator calculator;

    @BXML
    private Label currentOperation;

    @BXML
    private Label currentValue;

    @Override
    public void initialize(final Map<String, Object> map, final URL url, final Resources resources)
    {
        numberConverter = new DecimalNumberConverter();
        numericLabel = new NumericLabel(numberConverter, currentValue);
        calculator = new SimpleCalculator();
    }

    @Override
    public void register(final Button button)
    {
        Matcher.of(button)
                .when(InputButton.class).then(addListener(b -> numericLabel.append(b.getDigit())))
                .when(DecimalButton.class).then(addListener(b -> numericLabel.startDecimal()))
                .when(ClearButton.class).then(addListener(b -> clearCalculator()))
                .when(OperatorButton.class).then(addListener(b -> pushOperator(b.getOperator())))
                .match();
    }

    private <T extends Button> Consumer<T> addListener(final Consumer<T> consumer)
    {
        return button -> button.getButtonPressListeners().add(b -> consumer.accept((T) b));
    }

    private void clearCalculator()
    {
        numericLabel.clear();
        calculator.clear();
        currentOperation.setText("");
    }

    private void pushOperator(final Operator operator)
    {
        calculator.push(numericLabel.getNumber());
        calculator.push(operator);
        numericLabel.clear();
        currentOperation.setText(calculator.getOperation().toString(numberConverter));
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
}
