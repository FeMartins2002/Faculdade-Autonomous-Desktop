package br.com.view.forms;

import br.com.controllers.StoreController;
import br.com.dtos.requests.store.CreateStoreDTO;
import br.com.exceptions.ApiException;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreForm extends JFrame implements ActionListener {
    private JLabel labelName, labelPhone, labelAddress;
    private JTextField txtName, txtPhone, txtAddress;
    private JButton submit, refresh;
    private StoreController controller;

    public StoreForm(StoreController controller, JButton refresh) {
        this.controller = controller;
        this.refresh = refresh;

        setTitle("Cadastro de Loja");
        setSize(550, 420);
        setResizable(false);
        setLocationRelativeTo(null);

        buildComponents();
        organizeComponents();
        setVisible(true);
    }

    private void buildComponents() {
        buildNameComponents();
        buildPhoneComponents();
        buildAddressComponents();
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

    private void buildAddressComponents() {
        labelAddress = new LabelBuilder("Endereço")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtAddress = new TextFieldBuilder()
                .required(true, null)
                .build();
    }

    private void buildSubmitButton() {
        submit = new ButtonBuilder("Salvar")
                .background(new Color(46, 204, 113))
                .size(250, 40)
                .opaque(true)
                .fontSize(15)
                .build();
        submit.addActionListener(this);
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
        mainPanel.add(labelAddress, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtAddress, gbc);

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
                if (controller.createStore(createStoreDTO())) {
                    clickRefresh();
                    successMessage();
                    dispose();
                }
            } catch (ApiException | JsonProcessingException e) {
                errorMessage(e.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        return txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty();
    }

    private CreateStoreDTO createStoreDTO() {
        return new CreateStoreDTO(null, txtName.getText(), txtPhone.getText(), txtAddress.getText());
    }

    private void clickRefresh() {
        if (refresh != null) refresh.doClick();
    }

    private void successMessage() {
        JOptionPane.showMessageDialog(this, "Loja cadastrada com sucesso");
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void invalidInputs() {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}
