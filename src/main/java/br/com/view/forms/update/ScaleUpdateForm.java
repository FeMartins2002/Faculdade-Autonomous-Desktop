package br.com.view.forms.update;

import br.com.controllers.ScaleController;
import br.com.dtos.requests.scale.UpdateScaleDTO;
import br.com.dtos.responses.store.StoreOption;
import br.com.exceptions.ApiException;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.ComboBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TimeFieldBuilder;
import br.com.view.builders.ValueFieldBuilder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScaleUpdateForm extends JFrame implements ActionListener {
    private JLabel labelEntry, labelExit, labelStore, labelValue;
    private JFormattedTextField txtEntry, txtExit, txtValue;
    private JComboBox<StoreOption> comboStores;
    private String[] row;
    private JButton submit, refresh;
    private ScaleController controller;

    public ScaleUpdateForm(ScaleController controller, String[] row, JButton refresh) {
        this.controller = controller;
        this.refresh = refresh;
        this.row = row;

        setTitle("Editar Escala");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        buildComponents();
        fillFields();
        organizeComponents();
        setVisible(true);
    }

    private void buildComponents() {
        buildEntryComponents();
        buildExitComponents();
        buildStoreComponents();
        buildValueComponents();
        buildSubmitButton();
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
                .tooltip("Editar Escala")
                .background(new Color(46, 204, 113))
                .size(250, 40)
                .opaque(true)
                .fontSize(15)
                .build();
        submit.addActionListener(this);
    }

    private void fillFields() {
        txtEntry.setText(row[1]);
        txtExit.setText(row[2]);
        //txtValue.setValue(Double.parseDouble(row[4]));
        txtValue.setValue(Double.parseDouble(row[4].replace(",", ".")));

        for (int i = 0; i < comboStores.getItemCount(); i++) {
            StoreOption option = comboStores.getItemAt(i);

            if (option.getName().equals(row[3])) {
                comboStores.setSelectedIndex(i);
                break;
            }
        }
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
        mainPanel.add(labelEntry, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtEntry, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(labelExit, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtExit, gbc);

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
    public void actionPerformed(ActionEvent click) {
        if (hasInvalidInputs()) {
            return;
        }

        if (click.getSource() == submit) {
            try {
                if (controller.updateScale(UpdateScaleDTO())) {
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
        String entry = txtEntry.getText();
        String exit = txtExit.getText();

        // valida máscara vazia
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
            LocalTime.parse(entry, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime.parse(exit, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    private UpdateScaleDTO UpdateScaleDTO() {
        StoreOption store = (StoreOption) comboStores.getSelectedItem();

        return new UpdateScaleDTO(
                null,
                Long.parseLong(row[0]),
                LocalTime.parse(txtEntry.getText(), DateTimeFormatter.ofPattern("HH:mm")),
                LocalTime.parse(txtExit.getText(), DateTimeFormatter.ofPattern("HH:mm")),
                store.getId(),
                ((Number) txtValue.getValue()).doubleValue());
    }

    private void clickRefresh() {
        if (refresh != null) refresh.doClick();
    }

    private void successMessage() {
        JOptionPane.showMessageDialog(this, "Escala editada com sucesso");
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void invalidInputs(String message) {
        JOptionPane.showMessageDialog(this, message, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}
