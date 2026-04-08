package br.com.view.builders;

import javax.swing.*;
import java.awt.*;

public class LabelBuilder {
    private JLabel label;

    public LabelBuilder(String text) {
        label = new JLabel();

        label.setText(text);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setPreferredSize(new Dimension(100, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(null);
        label.setOpaque(false);
    }

    public LabelBuilder textColor(Color color) {
        label.setForeground(color);
        return this;
    }

    public LabelBuilder textFont(Font font) {
        label.setFont(font);
        return this;
    }

    public LabelBuilder fontType(String type) {
        Font current = label.getFont();
        label.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public LabelBuilder fontSize(int size) {
        Font current = label.getFont();
        label.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public LabelBuilder size(int width, int height) {
        label.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public LabelBuilder align(int alignment) {
        label.setHorizontalAlignment(alignment);
        return this;
    }

    public LabelBuilder background(Color color) {
        label.setBackground(color);
        return this;
    }

    public LabelBuilder opaque(boolean opaque) {
        label.setOpaque(opaque);
        return this;
    }

    public JLabel build() {
        return label;
    }
}
