package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.SimpleCalculator;
import com.emirovschi.midps2.calc.converters.DecimalNumberConverter;
import com.emirovschi.midps2.calc.converters.NumberConverter;
import com.emirovschi.midps2.calc.gui.ButtonRegister;
import com.emirovschi.midps2.calc.gui.DefaultGraphicCalculator;
import com.emirovschi.midps2.calc.gui.DisableButtonErrorHandler;
import com.emirovschi.midps2.calc.gui.GraphicCalculator;
import com.emirovschi.midps2.calc.gui.KeyListener;
import com.emirovschi.midps2.calc.matcher.Matcher;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Window;

import java.net.URL;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MainWindow extends Window implements Bindable, ButtonRegister
{
    private KeyListener keyListener;
    private GraphicCalculator graphicCalculator;

    private DisableButtonErrorHandler disableButtonErrorHandler;

    @BXML
    private Label currentOperation;

    @BXML
    private Label currentValue;

    public MainWindow()
    {
        keyListener = new KeyListener();
        getComponentKeyListeners().add(keyListener);
        disableButtonErrorHandler = new DisableButtonErrorHandler();
    }

    @Override
    public void initialize(final Map<String, Object> map, final URL url, final Resources resources)
    {
        final NumberConverter numberConverter = new DecimalNumberConverter();

        final DefaultGraphicCalculator graphicCalculator = new DefaultGraphicCalculator();
        graphicCalculator.setNumberConverter(numberConverter);
        graphicCalculator.setNumericLabel(new NumericLabel(numberConverter, currentValue));
        graphicCalculator.setCalculator(new SimpleCalculator());
        graphicCalculator.setCurrentOperation(currentOperation);
        graphicCalculator.setCurrentValue(currentValue);
        graphicCalculator.addErrorHandler(disableButtonErrorHandler);

        setGraphicCalculator(graphicCalculator);
    }

    @Override
    public void register(final Button button)
    {
        addListener(button);
        addKeyBinding(button);
        addDisableOnError(button);
    }

    private void addListener(final Button button)
    {
        Matcher.of(button)
                .when(InputButton.class).then(addListener(b -> graphicCalculator.push(b.getDigit())))
                .when(DecimalButton.class).then(addListener(b -> graphicCalculator.startDecimal()))
                .when(ClearButton.class).then(addListener(b -> graphicCalculator.clear()))
                .when(OperatorButton.class).then(addListener(b -> graphicCalculator.push(b.getOperator())))
                .when(EqualsButton.class).then(addListener(b -> graphicCalculator.calculate()))
                .when(ChangeSignButton.class).then(addListener(b -> graphicCalculator.changeSign()))
                .match();
    }

    private <T extends Button> Consumer<T> addListener(final Consumer<T> consumer)
    {
        return button -> button.getButtonPressListeners().add(b -> consumer.accept(button));
    }

    private void addKeyBinding(final Button button)
    {
        getRegisteredButton(button).ifPresent(keyListener::bind);
    }

    private void addDisableOnError(final Button button)
    {
        getRegisteredButton(button)
                .filter(RegisteredPushButton::isDisableOnError)
                .ifPresent(disableButtonErrorHandler::add);
    }

    private Optional<RegisteredPushButton> getRegisteredButton(final Button button)
    {
        return Stream.of(button)
                .filter(b -> b instanceof RegisteredPushButton)
                .map(b -> (RegisteredPushButton) b)
                .findFirst();
    }

    @Override
    public boolean keyTyped(char character)
    {
        return super.keyTyped(character);
    }

    @Override
    public boolean keyPressed(int keyCode, Keyboard.KeyLocation keyLocation)
    {
        return super.keyPressed(keyCode, keyLocation);
    }

    @Override
    public boolean keyReleased(int keyCode, Keyboard.KeyLocation keyLocation)
    {
        return super.keyReleased(keyCode, keyLocation);
    }

    protected void setKeyListener(final KeyListener keyListener)
    {
        this.keyListener = keyListener;
    }

    protected void setGraphicCalculator(final GraphicCalculator graphicCalculator)
    {
        this.graphicCalculator = graphicCalculator;
    }

    protected void setDisableButtonErrorHandler(final DisableButtonErrorHandler disableButtonErrorHandler)
    {
        this.disableButtonErrorHandler = disableButtonErrorHandler;
    }
}
