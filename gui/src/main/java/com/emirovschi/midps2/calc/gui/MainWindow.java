package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.converters.DecimalNumberConverter;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.controls.NumericLabel;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

import java.net.URL;

public class MainWindow extends Window implements Bindable
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
}
