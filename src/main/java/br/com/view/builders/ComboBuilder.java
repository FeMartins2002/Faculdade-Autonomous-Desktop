package br.com.view.builders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class ComboBuilder<T> {
    private JComboBox<T> comboBox;

    public ComboBuilder(List<T> options) {
        comboBox = new JComboBox<>();

        for (T option : options) {
            comboBox.addItem(option);
        }

        comboBox.setPreferredSize(new Dimension(300, 40));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        comboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    public ComboBuilder<T> size(int width, int height) {
        comboBox.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public ComboBuilder<T> textFont(Font font) {
        comboBox.setFont(font);
        return this;
    }

    public ComboBuilder<T> fontType(String type) {
        Font current = comboBox.getFont();
        comboBox.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public ComboBuilder<T> fontSize(int size) {
        Font current = comboBox.getFont();
        comboBox.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public ComboBuilder<T> border(Border border) {
        comboBox.setBorder(border);
        return this;
    }

    public JComboBox<T> build() {
        return comboBox;
    }
}