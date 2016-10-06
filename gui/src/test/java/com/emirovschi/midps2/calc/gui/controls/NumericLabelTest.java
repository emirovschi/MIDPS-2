package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.converters.NumberConverter;
import org.apache.pivot.wtk.Label;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumericLabelTest
{
    private NumericLabel numericLabel;
    private NumberConverter numberConverter;
    private Label label;

    @Before
    public void setUp() throws Exception
    {
        numberConverter = mock(NumberConverter.class);
        label = mock(Label.class);

        numericLabel = new NumericLabel(numberConverter, label);

        when(numberConverter.convert(0D)).thenReturn("0");
        when(numberConverter.convert(1D)).thenReturn("1");
        when(numberConverter.convert(12D)).thenReturn("12");
        when(numberConverter.convert(12.3D)).thenReturn("12.3");
        when(numberConverter.convert(12.34D)).thenReturn("12.34");
    }

    @Test
    public void shouldAppendInteger() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");

        assertEquals(12, numericLabel.getNumber(), 0.0001);
        assertEquals("12", numericLabel.getNumberText());
    }

    @Test
    public void shouldStartDecimal() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.startDecimal();

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12.");

        assertEquals(12, numericLabel.getNumber(), 0.0001);
        assertEquals("12.", numericLabel.getNumberText());
    }

    @Test
    public void shouldAddDecimal() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.startDecimal();
        numericLabel.append(3);
        numericLabel.append(4);

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12.3D);
        order.verify(label).setText("12.3");
        order.verify(numberConverter).convert(12.34D);
        order.verify(label).setText("12.34");

        assertEquals(12.34, numericLabel.getNumber(), 0.0001);
        assertEquals("12.34", numericLabel.getNumberText());
    }

    @Test
    public void shouldDeleteInteger() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.delete();

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");

        assertEquals(1, numericLabel.getNumber(), 0.0001);
        assertEquals("1", numericLabel.getNumberText());
    }

    @Test
    public void shouldDeleteStartInteger() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.startDecimal();
        numericLabel.delete();

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");

        assertEquals(12, numericLabel.getNumber(), 0.0001);
        assertEquals("12", numericLabel.getNumberText());
    }

    @Test
    public void shouldDeleteDecimal() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.startDecimal();
        numericLabel.append(3);
        numericLabel.append(4);
        numericLabel.delete();

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12.3D);
        order.verify(label).setText("12.3");
        order.verify(numberConverter).convert(12.34D);
        order.verify(label).setText("12.34");
        order.verify(numberConverter).convert(12.3D);
        order.verify(label).setText("12.3");

        assertEquals(12.3, numericLabel.getNumber(), 0.0001);
        assertEquals("12.3", numericLabel.getNumberText());
    }

    @Test
    public void shouldClear() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.startDecimal();
        numericLabel.append(3);
        numericLabel.append(4);
        numericLabel.clear();

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12.3D);
        order.verify(label).setText("12.3");
        order.verify(numberConverter).convert(12.34D);
        order.verify(label).setText("12.34");
        order.verify(numberConverter).convert(0D);
        order.verify(label).setText("0");

        assertEquals(0, numericLabel.getNumber(), 0.0001);
        assertEquals("0", numericLabel.getNumberText());
    }

}