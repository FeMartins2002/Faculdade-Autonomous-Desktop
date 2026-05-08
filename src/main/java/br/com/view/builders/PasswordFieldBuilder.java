package br.com.view.builders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PasswordFieldBuilder {
    private JPasswordField field;

    public PasswordFieldBuilder() {
        field = new JPasswordField();

        field.setBackground(Color.white);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setPreferredSize(new Dimension(100, 30));
        field.setFont(new Font("Arial", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        field.setOpaque(true);
    }

    public PasswordFieldBuilder background(Color color) {
        field.setBackground(color);
        return this;
    }

    public PasswordFieldBuilder align(int alignment) {
        field.setHorizontalAlignment(alignment);
        return this;
    }

    public PasswordFieldBuilder size(int width, int height) {
        field.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public PasswordFieldBuilder textFont(Font font) {
        field.setFont(font);
        return this;
    }

    public PasswordFieldBuilder fontType(String type) {
        Font current = field.getFont();
        field.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public PasswordFieldBuilder fontSize(int size) {
        Font current = field.getFont();
        field.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public PasswordFieldBuilder border(Border border) {
        field.setBorder(border);
        return this;
    }

    public PasswordFieldBuilder opaque(boolean opaque) {
        field.setOpaque(opaque);
        return this;
    }

    public PasswordFieldBuilder required(boolean required) {
        if (required) {
            Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
            Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 2);

            field.setBorder(redBorder);

            field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                private void updateBorder() {
                    if (field.getText().trim().isEmpty()) {
                        field.setBorder(redBorder);
                    } else {
                        field.setBorder(greenBorder);
                    }
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

    public JPasswordField build() {
        return field;
    }
}
