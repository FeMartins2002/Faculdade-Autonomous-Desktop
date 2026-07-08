package br.com.view.windows;

import br.com.controllers.ManagerController;
import br.com.view.cards.AuthenticationCard;
import br.com.view.cards.ChangePasswordCard;
import br.com.view.cards.LoginCard;
import br.com.view.cards.VerificationCard;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginWindow extends JFrame {
    public static final String LOGIN = "LOGIN";
    public static final String VERIFICATION = "VERIFICATION";
    public static final String AUTHENTICATION = "AUTHENTICATION";
    public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";

    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final ManagerController controller;

    public LoginWindow(ManagerController controller) {
        this.controller = controller;

        setTitle("Login");
        setSize(600, 600);
        setMinimumSize(new Dimension(600, 600));
        getContentPane().setBackground(new Color(21, 32, 43));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initializeCards();
        add(mainPanel);
        setVisible(true);
    }

    private void initializeCards() {
        LoginCard loginCard =
                new LoginCard(controller, this);

        VerificationCard verificationCard =
                new VerificationCard(controller, this);

        AuthenticationCard authenticationCard =
                new AuthenticationCard(this);

        ChangePasswordCard changePasswordCard =
                new ChangePasswordCard(controller, this);

        mainPanel.add(loginCard, LOGIN);
        mainPanel.add(verificationCard, VERIFICATION);
        mainPanel.add(authenticationCard, AUTHENTICATION);
        mainPanel.add(changePasswordCard, CHANGE_PASSWORD);

        showCard(LOGIN);
    }

    public void showCard(String card) {
        cardLayout.show(mainPanel, card);
    }
}
