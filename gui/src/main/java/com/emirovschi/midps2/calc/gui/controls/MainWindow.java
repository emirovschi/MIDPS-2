package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.converters.DecimalNumberConverter;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.ButtonRegister;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

import java.net.URL;
import java.util.function.Consumer;

public class MainWindow extends Window implements Bindable, ButtonRegister<InputButton>
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
    public void register(final InputButton button)
    {
        button.getButtonPressListeners().add(listener(b -> numericLabel.append(b.getDigit()), InputButton.class));
    }

    private <T extends Button> ButtonPressListener listener(final Consumer<T> consumer, final Class<T> buttonClass)
    {
        return button -> consumer.accept((T) button);
    }

    public void setNumericLabel(final NumericLabel numericLabel)
    {
        this.numericLabel = numericLabel;
    }
}
