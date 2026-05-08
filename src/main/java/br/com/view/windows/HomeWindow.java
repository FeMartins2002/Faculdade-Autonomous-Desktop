package br.com.view.windows;

import br.com.view.panels.HeaderPanel;
import br.com.view.panels.MainPanel;
import br.com.view.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class HomeWindow extends JFrame {
    private HeaderPanel headerPanel;
    private MenuPanel menuPanel;
    private MainPanel mainPanel;

    public HomeWindow() {
        setTitle("Home");
        setSize(1500, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(21, 32, 43));
        getContentPane().setLayout(new GridBagLayout());
        setResizable(true);

        buildHeaderPanel();
        buildMainPanel();
        buildMenuPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 20, 10, 20);
        add(headerPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 20, 20, 10);
        add(menuPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 20, 20);
        add(mainPanel, gbc);

        setVisible(true);
    }

    private void buildHeaderPanel() {
        headerPanel = new HeaderPanel();
    }

    private void buildMainPanel() {
        mainPanel = new MainPanel();
    }

    private void buildMenuPanel() {
        menuPanel = new MenuPanel(mainPanel);
    }

    // Remover
    public static void main(String[] args) {
        new HomeWindow();
    }
}