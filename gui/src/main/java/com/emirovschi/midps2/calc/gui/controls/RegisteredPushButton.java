package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.gui.ButtonRegister;
import org.apache.pivot.wtk.PushButton;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class RegisteredPushButton extends PushButton
{
    private Set<Character> keys = Collections.emptySet();
    private boolean disableOnError;

    public void setRegister(final ButtonRegister register)
    {
        register.register(this);
    }

    public Set<Character> getKeys()
    {
        return keys;
    }

    public void setKeys(final String keys)
    {
        this.keys = Stream.of(keys.split(","))
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    private char convert(final String key)
    {
        if (key.charAt(0) == '\\')
        {
            final String ascii = key.substring(1, key.length());
            return (char) Integer.parseInt(ascii);
        }

        return key.charAt(0);
    }

    public boolean isDisableOnError()
    {
        return disableOnError;
    }

    public void setDisableOnError(final boolean disableOnError)
    {
        this.disableOnError = disableOnError;
    }
}
