package com.emirovschi.midps2.calc.gui.controls;

import org.apache.pivot.util.ListenerList;
import org.apache.pivot.wtk.ButtonPressListener;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainWindowTest
{
    private MainWindow mainWindow = new MainWindow();

    @Test
    public void shouldRegisterInputButton() throws Exception
    {
        final int digit = 1;
        final InputButton button = mock(InputButton.class);
        final ListenerList<ButtonPressListener> listeners = mock(ListenerList.class);
        final ArgumentCaptor<ButtonPressListener> listener = ArgumentCaptor.forClass(ButtonPressListener.class);
        final NumericLabel numericLabel = mock(NumericLabel.class);

        mainWindow.setNumericLabel(numericLabel);

        when(button.getDigit()).thenReturn(digit);
        when(button.getButtonPressListeners()).thenReturn(listeners);

        mainWindow.register(button);

        verify(listeners).add(listener.capture());

        listener.getValue().buttonPressed(button);

        verify(numericLabel).append(digit);
    }

}