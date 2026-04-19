package br.com.view.windows;

import br.com.view.panels.MainPanel;
import br.com.view.panels.MenuPanel;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;

public class HomeWindow extends JFrame {
    private MenuPanel menuPanel;
    private MainPanel mainPanel;

    public HomeWindow() throws JsonProcessingException {
        setTitle("Home");
        setSize(1500, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(40, 40, 40));
        setResizable(true);

        GridBagConstraints gbc = new GridBagConstraints();
        getContentPane().setLayout(new GridBagLayout());

        buildMainPanel();
        buildMenuPanel();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridheight = 2;
        add(menuPanel, gbc);

        gbc.insets = new Insets(20, 0, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.weightx = 0.8;
        add(mainPanel, gbc);

        setVisible(true);
    }

    private void buildMainPanel() {
        mainPanel = new MainPanel();
    }

    private void buildMenuPanel() {
        menuPanel = new MenuPanel(mainPanel);
        menuPanel.setPreferredSize(new Dimension(180, 0));
        menuPanel.setMinimumSize(new Dimension(180, 0));
    }

    // Remover
    public static void main(String[] args) throws JsonProcessingException {
        new HomeWindow();
    }
}
