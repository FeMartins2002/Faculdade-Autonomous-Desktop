package br.com.view.builders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ButtonBuilder {
    private JButton button;

    public ButtonBuilder(String text) {
        this.button = new JButton();

        button.setText(text);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(100, 50));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(null);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //button.setContentAreaFilled(false);
        //button.setBorderPainted(false);
        //button.setFocusPainted(false);
        //button.setContentAreaFilled(true);
    }

    public ButtonBuilder textColor(Color color) {
        button.setForeground(color);
        return this;
    }

    public ButtonBuilder textFont(Font font) {
        button.setFont(font);
        return this;
    }

    public ButtonBuilder fontType(String type) {
        Font current = button.getFont();
        button.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public ButtonBuilder fontSize(int size) {
        Font current = button.getFont();
        button.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public ButtonBuilder border(Border border) {
        button.setBorder(border);
        return this;
    }

    public ButtonBuilder size(int width, int height) {
        button.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public ButtonBuilder align(int alignment) {
        button.setHorizontalAlignment(alignment);
        return this;
    }

    public ButtonBuilder background(Color color) {
        button.setBackground(color);
        return this;
    }

    public ButtonBuilder opaque(boolean opaque) {
        button.setOpaque(opaque);
        return this;
    }

    public JButton build() {
        return button;
    }
}
