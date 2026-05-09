package br.com.view.windows;

import br.com.controllers.ManagerController;
import br.com.dtos.requests.manager.LoginDTO;
import br.com.exceptions.ApiException;
import br.com.exceptions.CommunicationException;
import br.com.view.builders.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener {
    private JLabel title, userLabel, passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton login, forgotPassword;
    private JCheckBox showPassword;
    private ManagerController controller;

    public LoginWindow(ManagerController controller) {
        this.controller = controller;
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Login");
        setSize(600, 600);
        setMinimumSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(40, 40, 40));
        getContentPane().setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        JPanel panel = new JPanel();
        panel.setBackground(new Color(80, 80, 80));
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(350, 300));
        panel.setOpaque(true);

        buildTitle();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(title, gbc);

        buildUserLabel();

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 2, 0);
        panel.add(userLabel, gbc);

        buildUserField();

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(userField, gbc);

        buildPasswordLabel();

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 2, 0);
        panel.add(passwordLabel, gbc);

        buildPasswordField();

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(passwordField, gbc);

        buildShowPassword();

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(showPassword, gbc);

        showPassword.addActionListener(click -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        buildForgotPassword();

        forgotPassword.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(forgotPassword, gbc);

        buildLogin();

        login.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(login, gbc);

        GridBagConstraints frameGbc = new GridBagConstraints();
        frameGbc.insets = new Insets(50, 50, 50, 50); // padding externo
        frameGbc.anchor = GridBagConstraints.CENTER;

        add(panel, frameGbc);
    }

    private void buildTitle() {
        title = new LabelBuilder("Login")
                .textColor(Color.WHITE)
                .fontSize(20)
                .size(100, 30)
                .build();
    }

    private void buildUserLabel() {
        userLabel = new LabelBuilder("Usuário")
                .textColor(Color.WHITE)
                .fontSize(15)
                .size(300, 20)
                .align(SwingConstants.LEFT)
                .build();
    }

    private void buildUserField() {
        userField = new TextFieldBuilder()
                .required(true, null)
                .size(300, 30)
                .fontSize(15)
                .build();
    }

    private void buildPasswordLabel() {
        passwordLabel = new LabelBuilder("Senha")
                .textColor(Color.WHITE)
                .fontSize(15)
                .size(300, 20)
                .align(SwingConstants.LEFT)
                .build();
    }

    private void buildPasswordField() {
        passwordField = passwordField = new PasswordFieldBuilder()
                .required(true)
                .size(300, 30)
                .fontSize(15)
                .build();
    }

    private void buildShowPassword() {
        showPassword = new CheckBoxBuilder("Exibir senha")
                .textColor(Color.WHITE)
                .fontSize(13)
                .size(100, 20)
                .build();
    }

    private void buildForgotPassword() {
        forgotPassword = new ButtonBuilder("Esqueci a senha")
                .textColor(Color.WHITE)
                .fontSize(13)
                .size(100, 25)
                .build();
    }

    private void buildLogin() {
        login = new ButtonBuilder("Entrar")
                .textColor(Color.black)
                .fontSize(15)
                .size(300, 30)
                .background(new Color(0, 179, 0))
                .opaque(true)
                .build();
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() == login) {
            if(validateFields(userField.getText(), passwordField.getText())) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
            else {
                LoginDTO dto = new LoginDTO(userField.getText(), passwordField.getText());
                validateCredencials(dto);
            }
        }

        if(click.getSource() == forgotPassword) {
            System.out.println("Clicou em esqueci a senha");
        }
    }

    private boolean validateFields(String user, String password) {
        return user.isEmpty() || password.isEmpty();
    }

    private void validateCredencials(LoginDTO dto) {
        try {
            if (controller.login(dto)) {
                new HomeWindow();
                dispose();
            }
        } catch (ApiException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } catch (CommunicationException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Inválido",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
