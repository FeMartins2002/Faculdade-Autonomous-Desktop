package br.com.view.builders;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ValueFieldBuilder {
    private JFormattedTextField field;

    public ValueFieldBuilder() {
        field = new JFormattedTextField();

        field.setPreferredSize(new Dimension(120, 38));
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    /**
     * Configura para moeda brasileira (R$)
     */
    public ValueFieldBuilder real() {
        Locale localeBR = new Locale("pt", "BR");

        NumberFormat format = NumberFormat.getCurrencyInstance(localeBR);
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);

        field.setFormatterFactory(new DefaultFormatterFactory(formatter));
        field.setValue(0.0);

        return this;
    }

    /**
     * Configura para decimal comum
     * Ex: 1234.56
     */
    public ValueFieldBuilder decimal() {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);

        field.setFormatterFactory(new DefaultFormatterFactory(formatter));
        field.setValue(0.0);

        return this;
    }

    /**
     * Configura para moeda dólar
     */
    public ValueFieldBuilder dollar() {
        Locale localeUS = Locale.US;

        NumberFormat format = NumberFormat.getCurrencyInstance(localeUS);
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.0);

        field.setFormatterFactory(new DefaultFormatterFactory(formatter));
        field.setValue(0.0);

        return this;
    }

    public ValueFieldBuilder size(int width, int height) {
        field.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public ValueFieldBuilder font(Font font) {
        field.setFont(font);
        return this;
    }

    public ValueFieldBuilder fontSize(int size) {
        Font current = field.getFont();
        field.setFont(new Font(current.getName(), current.getStyle(), size));

        return this;
    }

    public ValueFieldBuilder background(Color color) {
        field.setBackground(color);
        return this;
    }

    public ValueFieldBuilder foreground(Color color) {
        field.setForeground(color);
        return this;
    }

    public ValueFieldBuilder value(Object value) {
        field.setValue(value);
        return this;
    }

    public JFormattedTextField build() {
        return field;
    }
}
