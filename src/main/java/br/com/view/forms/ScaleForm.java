package br.com.view.forms;

import br.com.controllers.FreelancerController;
import br.com.controllers.StoreController;
import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.dtos.responses.FreelancerOption;
import br.com.dtos.responses.StoreOption;
import br.com.view.builders.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScaleForm extends JFrame implements ActionListener {
    private JLabel labelValue, labelDate, labelFreelancer, labelStore;;
    private JFormattedTextField txtDate, txtValue;
    private JComboBox<String> comboFreelancers, comboStores;
    private JButton submit, refresh;
    private StoreController storeController;
    private FreelancerController freelancerController;

    private List<FreelancerOption> freelancerOptions;
    private List<StoreOption> storeOptions;

    public ScaleForm(FreelancerController freelancerController, StoreController storeController, JButton refresh) {
        this.storeController = storeController;
        this.freelancerController = freelancerController;
        this.refresh = refresh;

        setTitle("Criar Escala");
        setSize(550, 420);
        setResizable(false);
        setLocationRelativeTo(null);

        buildComponents();
        organizeComponents();
        setVisible(true);
    }

    private void buildComponents() {
        buildDateComponents();
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
                .build();
    }

    private void buildFreelancerComponents() {
        labelFreelancer = new LabelBuilder("Freelancer")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        comboFreelancers = new ComboBuilder(freelancerController.findOptions())
                .build();
    }

    private void buildStoreComponents() {
        labelStore = new LabelBuilder("Loja")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        comboStores = new ComboBuilder(storeController.findOptions())
                .build();
    }

    private void buildValueComponents() {
        labelValue = new LabelBuilder("Valor")
                .align(SwingConstants.LEFT)
                .textColor(Color.WHITE)
                .fontSize(15)
                .build();

        txtValue = new ValueFieldBuilder()
                .real()
                .size(200, 38)
                .build();
    }

    private void buildSubmitButton() {
        submit = new ButtonBuilder("Salvar")
                .background(new Color(46, 204, 113))
                .size(250, 40)
                .opaque(true)
                .fontSize(15)
                .build();
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
        mainPanel.add(labelDate, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(labelFreelancer, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(comboFreelancers, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        mainPanel.add(labelStore, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(comboStores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        mainPanel.add(labelValue, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtValue, gbc);

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
    public void actionPerformed(ActionEvent e) {

    }


    private boolean validateInputs() {
        return txtDate.getText().isEmpty() || txtValue.getText().isEmpty();
    }

    private CreateScaleDTO createScaleDTO() {
        return new CreateScaleDTO(null, ((Number) txtValue.getValue()).doubleValue(), null, null, null);
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

    private void invalidInputs() {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}