package br.com.view.cards;

import br.com.controllers.ManagerController;
import br.com.dtos.requests.manager.LoginDTO;
import br.com.exceptions.ApiException;
import br.com.exceptions.CommunicationException;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.CheckBoxBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.PasswordFieldBuilder;
import br.com.view.builders.TextFieldBuilder;
import br.com.view.windows.HomeWindow;
import br.com.view.windows.LoginWindow;
import br.com.view.windows.PolicyWindow;
import br.com.view.windows.TermsWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginCard extends JPanel {
    private JLabel title, userLabel, passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton login, forgotPassword, terms, policy;
    private JCheckBox showPassword;

    private final ManagerController controller;
    private final LoginWindow window;

    public LoginCard(ManagerController controller, LoginWindow window) {
        this.controller = controller;
        this.window = window;

        setBackground(new Color(21, 32, 43));
        setLayout(new GridBagLayout());

        initializeComponents();
    }

    private void initializeComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 350));
        panel.setBackground(new Color(80, 80, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        title = new LabelBuilder("Login")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();

        userLabel = new LabelBuilder("Usuário")
                .textColor(Color.WHITE)
                .size(300, 20)
                .align(SwingConstants.LEFT)
                .fontSize(15)
                .build();

        userField = new TextFieldBuilder()
                .required(false, null)
                .size(300, 30)
                .fontSize(15)
                .build();

        passwordLabel = new LabelBuilder("Senha")
                .textColor(Color.WHITE)
                .size(300, 20)
                .align(SwingConstants.LEFT)
                .fontSize(15)
                .build();

        passwordField = new PasswordFieldBuilder()
                .required(false)
                .size(300, 30)
                .fontSize(15)
                .build();

        showPassword = new CheckBoxBuilder("Exibir senha")
                .size(100, 20)
                .textColor(Color.WHITE)
                .fontSize(13)
                .build();

        forgotPassword = new ButtonBuilder("Esqueci a senha")
                .textColor(Color.WHITE)
                .size(100, 25)
                .fontSize(13)
                .build();

        login = new ButtonBuilder("Entrar")
                .size(300, 30)
                .background(new Color(0, 179, 0))
                .textColor(Color.black)
                .fontSize(15)
                .opaque(true)
                .build();

        terms = new ButtonBuilder("Termos de Uso")
                .tooltip("Termos de Uso")
                .textColor(Color.WHITE)
                .size(100, 25)
                .fontSize(13)
                .build();


        policy = new ButtonBuilder("Politica de Privacidade")
                .tooltip("Politica de Privacidade")
                .textColor(Color.WHITE)
                .size(150, 25)
                .fontSize(13)
                .build();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(title, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 2, 0);
        panel.add(userLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 2, 0);
        panel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(showPassword, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(forgotPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(login, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(terms, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(policy, gbc);

        GridBagConstraints frameGbc = new GridBagConstraints();
        frameGbc.insets = new Insets(50, 50, 50, 50);
        frameGbc.anchor = GridBagConstraints.CENTER;
        add(panel, frameGbc);

        add(panel);
        actions();
    }

    private void actions() {
        showPassword.addActionListener(click -> {
            if(showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('•');
            }
        });

        forgotPassword.addActionListener(click ->
                window.showCard(LoginWindow.VERIFICATION)
        );

        login.addActionListener(click -> login());

        terms.addActionListener(click -> {
            new TermsWindow();
        });

        policy.addActionListener(click -> {
           new PolicyWindow();
        });
    }

    private void login() {
        if(userField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        try {
            LoginDTO dto = new LoginDTO(userField.getText(), passwordField.getText());

            if(controller.login(dto)) {
                new HomeWindow();
                window.dispose();
            }

        } catch (ApiException | CommunicationException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage());
        }
    }
}
