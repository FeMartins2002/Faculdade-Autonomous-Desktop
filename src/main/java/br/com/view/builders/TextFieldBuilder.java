package br.com.view.builders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextFieldBuilder {
    private JTextField field;

    public TextFieldBuilder() {
        field = new JTextField();

        field.setBackground(Color.white);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setPreferredSize(new Dimension(100, 30));
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setOpaque(true);
    }

    public TextFieldBuilder background(Color color) {
        field.setBackground(color);
        return this;
    }

    public TextFieldBuilder align(int alignment) {
        field.setHorizontalAlignment(alignment);
        return this;
    }

    public TextFieldBuilder size(int width, int height) {
        field.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public TextFieldBuilder textFont(Font font) {
        field.setFont(font);
        return this;
    }

    public TextFieldBuilder fontType(String type) {
        Font current = field.getFont();
        field.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public TextFieldBuilder fontSize(int size) {
        Font current = field.getFont();
        field.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public TextFieldBuilder border(Border border) {
        field.setBorder(border);
        return this;
    }

    public TextFieldBuilder opaque(boolean opaque) {
        field.setOpaque(opaque);
        return this;
    }

    public TextFieldBuilder required(boolean required, java.util.function.Predicate<String> validator) {
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
                        valid = !text.isEmpty();
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

    public JTextField build() {
        return field;
    }
}
