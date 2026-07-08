package br.com.view.forms.update;

import br.com.controllers.FreelancerController;
import br.com.dtos.requests.freelancer.UpdateFreelancerDTO;
import br.com.exceptions.ApiException;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FreelancerUpdateForm extends JFrame implements ActionListener {
    private JLabel labelName, labelPhone, labelEmail;
    private JTextField txtName, txtPhone, txtEmail;
    private String[] row;
    private JButton submit, refresh;
    private FreelancerController controller;

    public FreelancerUpdateForm(FreelancerController controller, String[] row, JButton refresh) {
        this.controller = controller;
        this.refresh = refresh;
        this.row = row;

        setTitle("Editar Freelancer");
        setSize(550, 420);
        setResizable(false);
        setLocationRelativeTo(null);

        buildComponents();
        fillFields();
        organizeComponents();
        setVisible(true);
    }

    private void buildComponents() {
        buildNameComponents();
        buildPhoneComponents();
        buildEmailComponents();
        buildSubmitButton();
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

    private void buildSubmitButton() {
        submit = new ButtonBuilder("Salvar")
                .tooltip("Salvar Freelancer")
                .background(new Color(46, 204, 113))
                .size(250, 40)
                .opaque(true)
                .fontSize(15)
                .build();
        submit.addActionListener(this);
    }

    private void fillFields() {
        txtName.setText(row[1]);
        txtPhone.setText(row[2]);
        txtEmail.setText(row[3]);
    }

    private void organizeComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(21, 32, 43));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        mainPanel.add(labelName, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(labelPhone, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        mainPanel.add(labelEmail, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtEmail, gbc);

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

    @Override
    public void actionPerformed(ActionEvent click) {
        if (click.getSource() == submit) {
            if (validateInputs()) {
                invalidInputs();
                return;
            }

            try {
                if (controller.updateFreelancer(updateFreelancerDTO())) {
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
        return txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || txtEmail.getText().isEmpty();
    }

    private UpdateFreelancerDTO updateFreelancerDTO() {
        return new UpdateFreelancerDTO(null, Long.parseLong(row[0]), txtName.getText(), txtPhone.getText(), txtEmail.getText());
    }

    private void clickRefresh() {
        if (refresh != null) refresh.doClick();
    }

    private void successMessage() {
        JOptionPane.showMessageDialog(this, "Freelancer editado com sucesso");
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void invalidInputs() {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}
