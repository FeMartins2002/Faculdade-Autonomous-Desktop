package br.com.view.panels;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel() {
        setBackground(Color.red);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(0, 80));
        setMinimumSize(new Dimension(0, 80));
    }
}
