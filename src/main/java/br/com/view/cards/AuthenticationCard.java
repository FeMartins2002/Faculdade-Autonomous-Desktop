package br.com.view.cards;

import br.com.configurations.Authentication;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AuthenticationCard extends JPanel {
    private JLabel title, codeLabel;
    private JTextField codeField;
    private JButton validate, back;
    private final LoginWindow window;

    public AuthenticationCard(LoginWindow window) {
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

        title = new LabelBuilder("Autenticação")
                .size(200, 30)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();

        codeLabel = new LabelBuilder("Digite o código enviado para seu e-mail")
                .textColor(Color.WHITE)
                .size(300, 20)
                .align(SwingConstants.LEFT)
                .fontSize(15)
                .build();

        codeField = new TextFieldBuilder()
                .required(false, null)
                .size(300, 30)
                .fontSize(15)
                .build();

        validate = new ButtonBuilder("Validar")
                .tooltip("Validar código")
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
        gbc.insets = new Insets(0, 0, 50, 0);
        panel.add(title, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 3, 0);
        panel.add(codeLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(codeField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(validate, gbc);

        gbc.gridy++;
        panel.add(back, gbc);

        add(panel);
        actions();
    }

    private void actions() {
        back.addActionListener(click -> {
            window.showCard(LoginWindow.LOGIN);
            codeField.setText("");
        });

        validate.addActionListener(click -> {
            String code = codeField.getText();

            if(code.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o código");
                return;
            }

            if(codeField.getText().equals(Authentication.getToken())) {
                codeField.setText("");
                window.showCard(LoginWindow.CHANGE_PASSWORD);
            }
            else {
                JOptionPane.showMessageDialog(this, "Código inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
