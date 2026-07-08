package br.com.view.builders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

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

    public DateFieldBuilder required(boolean required, java.util.function.Predicate<String> validator) {
        if (required) {
            Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
            Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 2);

            field.setBorder(redBorder);

            field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                private void updateBorder() {
                    String text = field.getText().trim();

                    boolean valid;

                    if (validator != null) {
                        valid = validator.test(text);
                    } else {
                        valid = !text.replaceAll("\\D", "").isEmpty();
                    }

                    field.setBorder(valid ? greenBorder : redBorder);
                }

                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    updateBorder();
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    updateBorder();
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    updateBorder();
                }
            });
        }

        return this;
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

    public JFormattedTextField build() {
        return field;
    }
}
