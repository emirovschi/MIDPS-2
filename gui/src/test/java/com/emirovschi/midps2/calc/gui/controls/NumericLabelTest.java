package com.emirovschi.midps2.calc.gui.controls;

import com.emirovschi.midps2.calc.converters.NumberConverter;
import org.apache.pivot.wtk.Label;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static com.emirovschi.midps2.calc.gui.controls.NumericLabel.MAX_LENGTH;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Matchers.eq;
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

        when(numberConverter.convert(0D, 0)).thenReturn("0");
        when(numberConverter.convert(1D, 0)).thenReturn("1");
        when(numberConverter.convert(12D, 0)).thenReturn("12");
        when(numberConverter.convert(12D, 1)).thenReturn("12.");
        when(numberConverter.convert(12.3D, 2)).thenReturn("12.3");
        when(numberConverter.convert(12.34D, 3)).thenReturn("12.34");
        when(numberConverter.convert(12D, 2)).thenReturn("12.0");
        when(numberConverter.convert(12.03D, 3)).thenReturn("12.03");
        when(numberConverter.convert(eq(12.034D, 0.00001), eq(4))).thenReturn("12.034");
    }

    @Test
    public void shouldAppendInteger() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
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
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D, 1);
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
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D, 1);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12.3D, 2);
        order.verify(label).setText("12.3");
        order.verify(numberConverter).convert(12.34D, 3);
        order.verify(label).setText("12.34");

        assertEquals(12.34, numericLabel.getNumber(), 0.0001);
        assertEquals("12.34", numericLabel.getNumberText());
    }

    @Test
    public void shouldAddZeroDecimal() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.startDecimal();
        numericLabel.append(0);
        numericLabel.append(3);
        numericLabel.append(4);

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D, 1);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12D, 2);
        order.verify(label).setText("12.0");
        order.verify(numberConverter).convert(12.03D, 3);
        order.verify(label).setText("12.03");
        order.verify(numberConverter).convert(eq(12.034D, 0.00001), eq(4));
        order.verify(label).setText("12.034");

        assertEquals(12.034, numericLabel.getNumber(), 0.0001);
        assertEquals("12.034", numericLabel.getNumberText());
    }

    @Test
    public void shouldDeleteInteger() throws Exception
    {
        numericLabel.append(1);
        numericLabel.append(2);
        numericLabel.delete();

        final InOrder order = inOrder(numberConverter, label);
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(1D, 0);
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
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D, 1);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12D, 0);
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
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D, 1);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12.3D, 2);
        order.verify(label).setText("12.3");
        order.verify(numberConverter).convert(12.34D, 3);
        order.verify(label).setText("12.34");
        order.verify(numberConverter).convert(12.3D, 2);
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
        order.verify(numberConverter).convert(1D, 0);
        order.verify(label).setText("1");
        order.verify(numberConverter).convert(12D, 0);
        order.verify(label).setText("12");
        order.verify(numberConverter).convert(12D, 1);
        order.verify(label).setText("12.");
        order.verify(numberConverter).convert(12.3D, 2);
        order.verify(label).setText("12.3");
        order.verify(numberConverter).convert(12.34D, 3);
        order.verify(label).setText("12.34");
        order.verify(numberConverter).convert(0D, 0);
        order.verify(label).setText("0");

        assertEquals(0, numericLabel.getNumber(), 0.0001);
        assertEquals("0", numericLabel.getNumberText());
    }

    @Test
    public void shouldAddIntegerMax() throws Exception
    {
        for(int i = 0; i < MAX_LENGTH + 1; i++)
        {
            numericLabel.append(1);
        }
        
        when(numberConverter.convert(1111111111111111D, 0)).thenReturn("1,111,111,111,111,111");

        assertEquals(1111111111111111D, numericLabel.getNumber(), 0.0001);
        assertEquals("1,111,111,111,111,111", numericLabel.getNumberText());
    }

    @Test
    public void shouldNotStartDecimalMax() throws Exception
    {
        for(int i = 0; i < MAX_LENGTH + 1; i++)
        {
            numericLabel.append(1);
        }
        numericLabel.startDecimal();

        when(numberConverter.convert(1111111111111111D, 0)).thenReturn("1,111,111,111,111,111");

        assertEquals(1111111111111111D, numericLabel.getNumber(), 0.0001);
        assertEquals("1,111,111,111,111,111", numericLabel.getNumberText());
    }

    @Test
    public void shouldAddDecimalMax() throws Exception
    {
        numericLabel.startDecimal();
        for(int i = 0; i < MAX_LENGTH + 1; i++)
        {
            numericLabel.append(1);
        }

        when(numberConverter.convert(eq(0.111111111111111D, 0.0000000000000001D), eq(16))).thenReturn("0.111111111111111");

        assertEquals(0.111111111111111D, numericLabel.getNumber(), 0.0000000000000001D);
        assertEquals("0.111111111111111", numericLabel.getNumberText());
    }

    @Test
    public void shouldChangeSign() throws Exception
    {
        numericLabel.append(1);
        numericLabel.changeSign();

        when(numberConverter.convert(-1, 0)).thenReturn("-1");

        assertEquals(-1D, numericLabel.getNumber(), 0.0001);
        assertEquals("-1", numericLabel.getNumberText());
    }
}