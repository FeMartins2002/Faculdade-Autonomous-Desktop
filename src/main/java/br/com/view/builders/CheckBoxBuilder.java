package br.com.view.builders;

import javax.swing.*;
import java.awt.*;

public class CheckBoxBuilder {
    private JCheckBox checkBox;

    public CheckBoxBuilder(String text) {
        this.checkBox = new JCheckBox();

        checkBox.setText(text);
        checkBox.setForeground(Color.BLACK);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 12));
        checkBox.setPreferredSize(new Dimension(100, 20));
        checkBox.setBackground(null);
        checkBox.setOpaque(false);
    }

    public CheckBoxBuilder textColor(Color color) {
        checkBox.setForeground(color);
        return this;
    }

    public CheckBoxBuilder textFont(Font font) {
        checkBox.setFont(font);
        return this;
    }

    public CheckBoxBuilder fontType(String type) {
        Font current = checkBox.getFont();
        checkBox.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public CheckBoxBuilder fontSize(int size) {
        Font current = checkBox.getFont();
        checkBox.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public CheckBoxBuilder size(int width, int height) {
        checkBox.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public CheckBoxBuilder background(Color color) {
        checkBox.setBackground(color);
        return this;
    }

    public CheckBoxBuilder opaque(boolean opaque) {
        checkBox.setOpaque(opaque);
        return this;
    }

    public JCheckBox build() {
        return checkBox;
    }
}
