package br.com.view.builders;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ComboBuilder {
    private JComboBox comboBox;

    public ComboBuilder(String[] options) {
        comboBox = new JComboBox<>(options);

        comboBox.setPreferredSize(new Dimension(300, 40));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        comboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    public ComboBuilder size(int width, int height) {
        comboBox.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public ComboBuilder textFont(Font font) {
        comboBox.setFont(font);
        return this;
    }

    public ComboBuilder fontType(String type) {
        Font current = comboBox.getFont();
        comboBox.setFont(new Font(type, current.getStyle(), current.getSize()));
        return this;
    }

    public ComboBuilder fontSize(int size) {
        Font current = comboBox.getFont();
        comboBox.setFont(new Font(current.getName(), current.getStyle(), size));
        return this;
    }

    public ComboBuilder border(Border border) {
        comboBox.setBorder(border);
        return this;
    }

    public JComboBox build() {
        return comboBox;
    }
}
