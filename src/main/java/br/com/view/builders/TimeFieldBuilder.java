package br.com.view.builders;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class TimeFieldBuilder {
    private JFormattedTextField field;

    public TimeFieldBuilder() {
        this.field = new JFormattedTextField(maskFormatter());

        field.setBackground(Color.WHITE);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setPreferredSize(new Dimension(80, 38));
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setOpaque(true);
    }

    public TimeFieldBuilder background(Color color) {
        field.setBackground(color);
        return this;
    }

    public TimeFieldBuilder align(int alignment) {
        field.setHorizontalAlignment(alignment);
        return this;
    }

    public TimeFieldBuilder size(int width, int height) {
        field.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public TimeFieldBuilder textFont(Font font) {
        field.setFont(font);
        return this;
    }

    public TimeFieldBuilder fontType(String type) {
        Font current = field.getFont();
        field.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public TimeFieldBuilder fontSize(int size) {
        Font current = field.getFont();
        field.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public TimeFieldBuilder border(Border border) {
        field.setBorder(border);
        return this;
    }

    public TimeFieldBuilder opaque(boolean opaque) {
        field.setOpaque(opaque);
        return this;
    }

    public TimeFieldBuilder required(boolean required, java.util.function.Predicate<String> validator) {
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
            mask = new MaskFormatter("##:##");
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