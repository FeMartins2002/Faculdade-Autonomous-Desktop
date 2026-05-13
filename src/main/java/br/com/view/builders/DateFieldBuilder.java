package br.com.view.builders;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class DateFieldBuilder {
    private JFormattedTextField field;

    public DateFieldBuilder() {
        this.field = new JFormattedTextField(maskFormatter());

        field.setBackground(Color.WHITE);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setPreferredSize(new Dimension(100, 38));
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setOpaque(true);
    }

    public DateFieldBuilder background(Color color) {
        field.setBackground(color);
        return this;
    }

    public DateFieldBuilder align(int alignment) {
        field.setHorizontalAlignment(alignment);
        return this;
    }

    public DateFieldBuilder size(int width, int height) {
        field.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public DateFieldBuilder textFont(Font font) {
        field.setFont(font);
        return this;
    }

    public DateFieldBuilder fontType(String type) {
        Font current = field.getFont();
        field.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public DateFieldBuilder fontSize(int size) {
        Font current = field.getFont();
        field.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public DateFieldBuilder border(Border border) {
        field.setBorder(border);
        return this;
    }

    public DateFieldBuilder opaque(boolean opaque) {
        field.setOpaque(opaque);
        return this;
    }

    public JFormattedTextField build() {
        return field;
    }

    private MaskFormatter maskFormatter() {
        MaskFormatter mask = null;

        try {
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter(' ');
        } catch (Exception error) {
            error.printStackTrace();
        }

        return mask;
    }
}
