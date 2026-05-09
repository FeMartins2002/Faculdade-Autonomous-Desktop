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
        organizeComponents();

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

    private void organizeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.weightx = 0.8;
        gbc.gridheight = 1;
        add(headerPanel, gbc);

        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridheight = 2;
        add(menuPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 20, 20);
        add(mainPanel, gbc);
    }

    // Remover
    public static void main(String[] args) {
        new HomeWindow();
    }
}