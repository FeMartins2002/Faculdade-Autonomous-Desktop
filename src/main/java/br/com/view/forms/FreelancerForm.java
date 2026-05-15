package br.com.view.forms;

import br.com.controllers.FreelancerController;
import br.com.dtos.requests.freelancer.CreateFreelancerDTO;
import br.com.exceptions.ApiException;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FreelancerForm extends JFrame implements ActionListener {
    private JLabel labelCpf, labelName, labelEmail, labelPhone;
    private JTextField txtCpf, txtName, txtEmail, txtPhone;
    private JButton submit, refresh;
    private FreelancerController controller;

    public FreelancerForm(FreelancerController controller, JButton refresh) {
        this.controller = controller;
        this.refresh = refresh;

        setTitle("Cadastro de Freelancer");
        setSize(550, 420);
        setResizable(false);
        setLocationRelativeTo(null);

        buildComponents();
        setVisible(true);
    }

    private void buildComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(21, 32, 43));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        buildCpfComponents();
        buildNameComponents();
        buildEmailComponents();
        buildPhoneComponents();
        buildSubmitButton();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        mainPanel.add(labelCpf, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(labelName, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        mainPanel.add(labelEmail, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        mainPanel.add(labelPhone, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtPhone, gbc);

        submit.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(21, 32, 43));
        buttonPanel.add(submit);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 10, 10, 10);

        mainPanel.add(buttonPanel, gbc);
        add(mainPanel);
    }

    private void buildCpfComponents() {
        labelCpf = new LabelBuilder("CPF")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtCpf = new TextFieldBuilder()
                .required(true, text -> text.matches("\\d{11}"))
                .size(200, 38)
                .fontSize(15)
                .build();
    }

    private void buildNameComponents() {
        labelName = new LabelBuilder("Nome")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtName = new TextFieldBuilder()
                .required(true, null)
                .size(200, 38)
                .fontSize(15)
                .build();
    }

    private void buildEmailComponents() {
        labelEmail = new LabelBuilder("E-mail")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtEmail = new TextFieldBuilder()
                .required(true, null)
                .size(200, 38)
                .fontSize(15)
                .build();
    }

    private void buildPhoneComponents() {
        labelPhone = new LabelBuilder("Telefone")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtPhone = new TextFieldBuilder()
                .required(true, text -> text.matches("\\d{11}"))
                .size(200, 38)
                .fontSize(15)
                .build();
    }

    private void buildSubmitButton() {
        submit = new ButtonBuilder("Salvar")
                .tooltip("Salvar Freelancer")
                .background(new Color(46, 204, 113))
                .size(250, 40)
                .opaque(true)
                .fontSize(15)
                .build();
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if (click.getSource() == submit) {
            if (validateInputs()) {
                invalidInputs();
                return;
            }

            try {
                if (controller.createFreelancer(createFreelancerDTO())) {
                    clickRefresh();
                    successMessage();
                    dispose();
                }
            } catch (ApiException e) {
                errorMessage(e.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        return txtCpf.getText().isEmpty() || txtName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhone.getText().isEmpty();
    }

    private CreateFreelancerDTO createFreelancerDTO() {
        return new CreateFreelancerDTO(txtCpf.getText(), txtName.getText(), txtEmail.getText(), txtPhone.getText());
    }

    private void clickRefresh() {
        if (refresh != null) refresh.doClick();
    }

    private void successMessage() {
        JOptionPane.showMessageDialog(this, "Freelancer cadastrado com sucesso");
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void invalidInputs() {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}