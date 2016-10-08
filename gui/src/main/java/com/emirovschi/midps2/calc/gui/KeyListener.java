package com.emirovschi.midps2.calc.gui;

import com.emirovschi.midps2.calc.gui.controls.RegisteredPushButton;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class KeyListener extends ComponentKeyListener.Adapter
{
    private Map<Character, Button> keyBinding = new HashMap<>();

    public void bind(final RegisteredPushButton button)
    {
        bind(button, button.getKeys());
    }

    public void bind(final Button button, final Set<Character> characters)
    {
        characters.forEach(k -> keyBinding.put(k, button));
    }

    @Override
    public boolean keyTyped(final Component component, final char c)
    {
        Optional.ofNullable(keyBinding.get(c)).ifPresent(Button::press);
        return false;
    }
}
