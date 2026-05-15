package br.com.view.panels;

import br.com.configurations.Session;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.windows.TermsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private MainPanel panel;
    private JLabel logo, version;
    private JButton scales, freelancers, stores, dashboard, terms, logout;

    public MenuPanel(MainPanel panel) {
        this.panel = panel;

        setBackground(new Color(21, 32, 43));
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(180, 0));
        setMinimumSize(new Dimension(180, 0));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.WHITE));

        buildButtons();
        organizeComponents();
    }

    private void buildButtons() {
        buildLogo();
        buildButtonScales();
        buildButtonFreelancers();
        buildButtonStores();
        buildDashboard();
        buildTerms();
        buildLogout();
        buildVersion();
    }

    private void buildLogo() {
        logo = new LabelBuilder("Autonomous")
                .textColor(Color.WHITE)
                .fontSize(25)
                .build();
    }

    private void buildButtonScales() {
        scales = new ButtonBuilder("Escalas")
                .size(100, 30)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .textColor(Color.WHITE)
                .tooltip("Escalas")
                .build();
        scales.addActionListener(this);
    }

    private void buildButtonFreelancers() {
        freelancers = new ButtonBuilder("Freelancers")
                .size(100, 30)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .textColor(Color.WHITE)
                .tooltip("Freelancers")
                .build();
        freelancers.addActionListener(this);
    }

    private void buildButtonStores() {
        stores = new ButtonBuilder("Lojas")
                .size(100, 30)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .textColor(Color.WHITE)
                .tooltip("Lojas")
                .build();
        stores.addActionListener(this);
    }

    private void buildDashboard() {
        dashboard = new ButtonBuilder("Estatisticas")
                .size(100, 30)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .textColor(Color.WHITE)
                .tooltip("Estatisticas")
                .build();
        dashboard.addActionListener(this);
    }

    private void buildTerms() {
        terms = new ButtonBuilder("Termos de Uso")
                .size(100, 30)
                .tooltip("Termos de Uso")
                .textColor(Color.WHITE)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .build();
        terms.addActionListener(this);
    }

    private void buildLogout() {
        logout = new ButtonBuilder("Sair")
                .size(100, 30)
                .textColor(Color.WHITE)
                .tooltip("Encerra Programa")
                .background(new Color(179, 0, 0))
                .opaque(true)
                .build();
        logout.addActionListener(this);
    }

    private void buildVersion() {
        version = new LabelBuilder("v1.0.0")
                .textColor(Color.white)
                .align(SwingConstants.CENTER)
                .build();
    }

    private void organizeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(logo, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(Box.createVerticalStrut(10), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(scales, gbc);

        gbc.gridy++;
        add(freelancers, gbc);

        gbc.gridy++;
        add(stores, gbc);

        gbc.gridy++;
        add(dashboard, gbc);

        gbc.gridy++;
        gbc.weighty = 1;
        add(Box.createVerticalGlue(), gbc);

        gbc.weighty = 0;
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(terms, gbc);

        gbc.gridy++;
        add(logout, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(Box.createVerticalStrut(10), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 20, 10);
        add(version, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if (click.getSource() == scales) {
            panel.showScales();
        }

        if (click.getSource() == freelancers) {
            panel.showFreelancers();
        }

        if (click.getSource() == stores) {
            panel.showStores();
        }

        if (click.getSource() == dashboard) {
            System.out.println("Clicou em dashboard");
        }

        if (click.getSource() == terms) {
            new TermsWindow();
        }

        if (click.getSource() == logout) {
            Session.logout();
            System.exit(0);
        }
    }
}