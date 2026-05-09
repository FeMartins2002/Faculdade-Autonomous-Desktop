package br.com.view.cards;

import br.com.controllers.ScaleController;
import br.com.enums.ScaleStatus;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;
import br.com.view.utilities.TableFiltering;
import br.com.view.utilities.TableFormatter;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScalesCard extends JPanel implements ActionListener {
    private JLabel title, searchLabel;
    private JTextField searchBar;
    private JPanel buttonsPanel;
    private JButton addButton, editButton, completedButton, cancelledButton, refreshButton;
    private ScaleController controller;
    private TableFiltering filtering;
    private TableFormatter formatter;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JTable table = new JTable();

    public ScalesCard(ScaleController controller) {
        filtering = new TableFiltering();
        formatter = new TableFormatter();
        this.controller = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(21, 32, 43));

        buildComponents();
        loadTable();
        buildScroll(table);
        organizeComponents();
    }

    private void buildComponents() {
        buildTitle();
        buildAddButton();
        buildEditButton();
        buildRefreshButton();
        buildCompletedButton();
        buildcancelledButton();
        buildButtonsPanel();
        buildSearchLabel();
        buildSearchBar();
        buildCompletedButton();
    }

    private void buildTitle() {
        title = new LabelBuilder("Escalas")
                .align(SwingConstants.CENTER)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();
    }

    private void buildAddButton() {
        addButton = new ButtonBuilder("Adicionar")
                .background(new Color(59, 111, 146))
                .tooltip("Adicionar Escala")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        addButton.addActionListener(this);
    }

    private void buildEditButton() {
        editButton = new ButtonBuilder("Editar")
                .background(new Color(59, 111, 146))
                .tooltip("Editar Escala")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        editButton.addActionListener(this);
    }

    private void buildRefreshButton() {
        refreshButton = new ButtonBuilder("Atualizar")
                .background(new Color(59, 111, 146))
                .tooltip("Atualizar Tabela")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        refreshButton.addActionListener(this);
    }

    private void buildCompletedButton() {
        completedButton = new ButtonBuilder("Finalizar")
                .background(new Color(0, 179, 0))
                .tooltip("Finalizar Escala")
                .size(100, 30)
                .textColor(Color.black)
                .fontSize(15)
                .opaque(true)
                .build();
        completedButton.addActionListener(this);
    }

    private void buildcancelledButton() {
        cancelledButton = new ButtonBuilder("Cancelar")
                .background(new Color(179, 0, 0))
                .tooltip("Cancelar Escala")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        cancelledButton.addActionListener(this);
    }

    private void buildSearchLabel() {
        searchLabel = new LabelBuilder("Buscar")
                .background(new Color(59, 111, 146))
                .size(100, 40)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
    }

    private void buildSearchBar() {
        searchBar = new TextFieldBuilder()
                .size(100, 40)
                .fontSize(15)
                .build();
    }

    private void loadTable() {
        setUpModel();
        setUpTable();
        setupSearchBar();
    }

    private void setUpModel() {
        try {
            model = controller.findByStatus(ScaleStatus.CRIADO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUpTable() {
        formatter.formatTable(table, model);
    }

    private void setupSearchBar() {
        filtering.searchBarConfiguration(model, table, searchBar);
    }

    private void buildScroll(JTable table) {
        scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(new Color(21, 32, 43));
    }

    private void buildButtonsPanel() {
        buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(new Color(21, 32, 43));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        buttonsPanel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        buttonsPanel.add(editButton, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        buttonsPanel.add(spacer, gbc);

        gbc.gridx = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        buttonsPanel.add(refreshButton, gbc);

        gbc.gridx = 4;
        buttonsPanel.add(completedButton, gbc);

        gbc.gridx = 5;
        buttonsPanel.add(cancelledButton, gbc);
    }

    private void organizeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(buttonsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(searchBar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(scroll, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() == refreshButton) {
            loadTable();
        }
    }
}
