package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.converters.DecimalNumberConverter;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.ButtonRegister;
import com.emirovschi.midps2.calc.matcher.Matcher;
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
    private NumberConverter numberConverter = new DecimalNumberConverter();

    private NumericLabel numericLabel;

    @BXML
    private Label currentValue;

    @Override
    public void initialize(final Map<String, Object> map, final URL url, final Resources resources)
    {
        numericLabel = new NumericLabel(numberConverter, currentValue);
    }

    @Override
    public void register(final Button button)
    {
        Matcher.of(button)
                .when(InputButton.class).then(addListener(b -> numericLabel.append(b.getDigit())))
                .match();
    }

    private <T extends Button> Consumer<T> addListener(final Consumer<T> consumer)
    {
        return button -> button.getButtonPressListeners().add(b -> consumer.accept((T) b));
    }

    public void setNumericLabel(final NumericLabel numericLabel)
    {
        this.numericLabel = numericLabel;
    }
}
