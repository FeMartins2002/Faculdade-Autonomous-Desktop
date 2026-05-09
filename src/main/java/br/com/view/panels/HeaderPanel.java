package br.com.view.panels;

import br.com.configurations.Session;
import br.com.view.builders.LabelBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HeaderPanel extends JPanel {
    private JPanel leftPanel, rightPanel;
    private JLabel welcomeLabel, date;

    public HeaderPanel() {
        setLayout(new GridLayout(1, 2));
        setBackground(new Color(21, 32, 43));
        setPreferredSize(new Dimension(0, 60));
        setMinimumSize(new Dimension(0, 60));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        buildLeftPanel();
        buildWelcomeLabel();
        leftPanel.add(welcomeLabel);

        buildRightPanel();
        rightPanel.add(dateNow());

        add(leftPanel);
        add(rightPanel);
    }

    private void buildLeftPanel() {
        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 25));
        leftPanel.setOpaque(false);
    }

    private void buildWelcomeLabel() {
        welcomeLabel = new LabelBuilder("Olá, " + managerLogged())
                .align(SwingConstants.LEFT)
                .size(300, 30)
                .textColor(Color.white)
                .fontSize(20)
                .build();
    }

    private void buildRightPanel() {
        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 25));
        rightPanel.setOpaque(false);
    }

    private JLabel dateNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");

        date = new LabelBuilder(now.format(formatter))
                .align(SwingConstants.RIGHT)
                .size(300, 30)
                .textColor(Color.white)
                .fontSize(20)
                .build();

        return date;
    }

    private String managerLogged() {
        if (Session.getManagerLogged() != null) {
            return Session.getManagerLogged().getName();
        }
        return "Gestor";
    }
}