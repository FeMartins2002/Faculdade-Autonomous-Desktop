package br.com.view.forms;

import br.com.controllers.ScaleController;
import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.dtos.responses.FreelancerOption;
import br.com.dtos.responses.StoreOption;
import br.com.exceptions.ApiException;
import br.com.view.builders.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScaleForm extends JFrame implements ActionListener {
    private JLabel labelDate, labelFreelancer, labelEntry, labelExit, labelStore, labelValue;
    private JFormattedTextField txtDate, txtEntry, txtExit, txtValue;
    private JComboBox<FreelancerOption> comboFreelancers;
    private JComboBox<StoreOption> comboStores;
    private JButton submit, refresh;
    private ScaleController controller;

    public ScaleForm(ScaleController controller, JButton refresh) {
        this.controller = controller;
        this.refresh = refresh;

        setTitle("Criar Escala");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        buildComponents();
        organizeComponents();
        setVisible(true);
    }

    private void buildComponents() {
        buildDateComponents();
        buildEntryComponents();
        buildExitComponents();
        buildFreelancerComponents();
        buildStoreComponents();
        buildValueComponents();
        buildSubmitButton();
    }

    private void buildDateComponents() {
        labelDate = new LabelBuilder("Data")
                .size(100, 38)
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtDate = new DateFieldBuilder()
                .required(true, text -> text.matches("\\d{2}/\\d{2}/\\d{4}"))
                .build();
    }

    private void buildEntryComponents() {
        labelEntry = new LabelBuilder("Entrada")
                .size(100, 38)
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtEntry = new TimeFieldBuilder()
                .required(true, text -> text.matches("\\d{2}:\\d{2}"))
                .build();
    }

    private void buildExitComponents() {
        labelExit = new LabelBuilder("Saída")
                .size(100, 38)
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtExit = new TimeFieldBuilder()
                .required(true, text -> text.matches("\\d{2}:\\d{2}"))
                .build();
    }

    private void buildFreelancerComponents() {
        labelFreelancer = new LabelBuilder("Freelancer")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        comboFreelancers = new ComboBuilder<>(controller.findOptionsFreelancers())
                        .build();
    }

    private void buildStoreComponents() {
        labelStore = new LabelBuilder("Loja")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        comboStores = new ComboBuilder<>(controller.findOptionsStore())
                .build();
    }

    private void buildValueComponents() {
        labelValue = new LabelBuilder("Valor")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtValue = new ValueFieldBuilder()
                .size(200, 38)
                .real()
                .build();
    }

    private void buildSubmitButton() {
        submit = new ButtonBuilder("Salvar")
                .tooltip("Salvar Escala")
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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        mainPanel.add(labelDate, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(labelEntry, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtEntry, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        mainPanel.add(labelExit, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtExit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        mainPanel.add(labelFreelancer, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(comboFreelancers, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        mainPanel.add(labelStore, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(comboStores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        mainPanel.add(labelValue, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtValue, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(21, 32, 43));
        buttonPanel.add(submit);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 10, 10, 10);
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if (hasInvalidInputs()) {
            return;
        }

        if (click.getSource() == submit) {
            try {
                if (controller.createScale(createScaleDTO())) {
                    clickRefresh();
                    successMessage();
                    dispose();
                }
            } catch (ApiException e) {
                errorMessage(e.getMessage());
            }
        }
    }

    private boolean hasInvalidInputs() {
        String date = txtDate.getText().trim();
        String entry = txtEntry.getText();
        String exit = txtExit.getText();

        // valida máscara vazia
        if (date.equals("  /  /  ") || date.contains(" ")) {
            invalidInputs("Informe uma data!");
            return true;
        }

        if (entry.equals("  :  ") || entry.contains(" ")) {
            invalidInputs("Informe um horário de entrada!");
            return true;
        }

        if (exit.equals("  :  ") || exit.contains(" ")) {
            invalidInputs("Informe um horário de saída!");
            return true;
        }

        // valida valor
        if (txtValue.getValue() == null || ((Number) txtValue.getValue()).doubleValue() <= 0) {
            invalidInputs("Valor da escala não pode ser 0!");
            return true;
        }

        // valida formatos reais
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime.parse(entry, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime.parse(exit, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    private CreateScaleDTO createScaleDTO() {
        FreelancerOption freelancer = (FreelancerOption) comboFreelancers.getSelectedItem();
        StoreOption store = (StoreOption) comboStores.getSelectedItem();

        return new CreateScaleDTO(
                ((Number) txtValue.getValue()).doubleValue(),
                LocalDate.parse(txtDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(txtEntry.getText(), DateTimeFormatter.ofPattern("HH:mm")),
                LocalTime.parse(txtExit.getText(), DateTimeFormatter.ofPattern("HH:mm")),
                freelancer.getId(),
                store.getId());
    }

    private void clickRefresh() {
        if (refresh != null) refresh.doClick();
    }

    private void successMessage() {
        JOptionPane.showMessageDialog(this, "Escala criada com sucesso");
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void invalidInputs(String message) {
        JOptionPane.showMessageDialog(this, message, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}