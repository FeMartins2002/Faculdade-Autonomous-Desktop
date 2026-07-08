package br.com.view.cards;

import br.com.configurations.Authentication;
import br.com.controllers.ManagerController;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.PasswordFieldBuilder;
import br.com.view.windows.LoginWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class ChangePasswordCard extends JPanel {
    private JLabel title, passwordLabel, repeatLabel;
    private JPasswordField passwordField, repeatPasswordField;
    private JButton change, back;
    private final LoginWindow window;
    private final ManagerController controller;

    public ChangePasswordCard(ManagerController controller, LoginWindow window) {
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

        title = new LabelBuilder("Alterar senha")
                .size(200, 30)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();

        passwordLabel = new LabelBuilder("Nova senha")
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

        repeatLabel = new LabelBuilder("Repita a senha")
                .textColor(Color.WHITE)
                .size(300, 20)
                .align(SwingConstants.LEFT)
                .fontSize(15)
                .build();

        repeatPasswordField = new PasswordFieldBuilder()
                .required(false)
                .size(300, 30)
                .fontSize(15)
                .build();

        change = new ButtonBuilder("Alterar senha")
                .tooltip("Alterar senha")
                .size(300, 30)
                .background(new Color(0, 179, 0))
                .textColor(Color.black)
                .fontSize(15)
                .opaque(true)
                .build();

        back = new ButtonBuilder("Voltar")
                .tooltip("Voltar para tela de login")
                .size(300, 30)
                .background(new Color(59, 111, 146))
                .textColor(Color.white)
                .fontSize(15)
                .opaque(true)
                .build();

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(title, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 2, 0);
        panel.add(passwordLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 2, 0);
        panel.add(repeatLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(repeatPasswordField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(change, gbc);

        gbc.gridy++;
        panel.add(back, gbc);

        add(panel);
        actions();
    }

    private void actions() {
        back.addActionListener(click -> {
            window.showCard(LoginWindow.LOGIN);
            passwordField.setText("");
            repeatPasswordField.setText("");
        });

        change.addActionListener(click -> {

            String password =
                    new String(passwordField.getPassword());

            String repeat =
                    new String(repeatPasswordField.getPassword());

            if(password.isEmpty()
                    || repeat.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Preencha todos os campos");

                return;
            }

            if(!password.equals(repeat)) {

                JOptionPane.showMessageDialog(this,
                        "As senhas não coincidem");

                return;
            }

            Authentication.setPassword(password);
            Authentication.setRepeat(password);

            if(controller.changePassword(Authentication.getChangePasswordDTO())) {
                JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
                passwordField.setText("");
                repeatPasswordField.setText("");
            }
            else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar senha", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            window.showCard(LoginWindow.LOGIN);
        });
    }
}
