package br.com.view.cards;

import br.com.controllers.FreelancerController;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;
import br.com.view.utilities.TableFiltering;
import br.com.view.utilities.TableFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FreelancersCard extends JPanel implements ActionListener {
    private JLabel searchLabel;
    private JTextField searchBar;
    private JButton addButton, editButton, removeButton;
    private final FreelancerController controller;
    private TableFiltering filtering;
    private TableFormatter formatter;
    private DefaultTableModel model;
    private JTable table = new JTable();

    public FreelancersCard(FreelancerController controller) {
        filtering = new TableFiltering();
        formatter = new TableFormatter();
        this.controller = controller;

        setBackground(new Color(21, 32, 43));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        buildComponents();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(addButton, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(0, 10, 10, 0);
        add(editButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(removeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(searchBar, gbc);

        loadTable();

        JScrollPane scroll = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(scroll, gbc);
    }

    private void buildComponents() {
        buildAddButton();
        buildEditButton();
        buildRemoveButton();
        buildSearchLabel();
        buildSearchBar();
    }

    private void buildAddButton() {
        addButton = new ButtonBuilder("Adicionar Freelancer")
                .size(100, 30)
                .fontSize(15)
                .textColor(Color.WHITE)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .build();
        addButton.addActionListener(this);
    }

    private void buildEditButton() {
        editButton = new ButtonBuilder("Editar Freelancer")
                .size(100, 30)
                .fontSize(15)
                .textColor(Color.WHITE)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .build();
        editButton.addActionListener(this);
    }

    private void buildRemoveButton() {
        removeButton = new ButtonBuilder("Remover Freelancer")
                .size(100, 30)
                .fontSize(15)
                .textColor(Color.WHITE)
                .background(Color.red)
                .opaque(true)
                .build();
        removeButton.addActionListener(this);
    }

    private void buildSearchLabel() {
        searchLabel = new LabelBuilder("Buscar")
                .fontSize(15)
                .size(100, 30)
                .textColor(Color.WHITE)
                .background(new Color(59, 111, 146))
                .opaque(true)
                .build();
    }

    private void buildSearchBar() {
        searchBar = new TextFieldBuilder()
                .fontSize(15)
                .size(100, 30)
                .build();
    }

    private void loadTable() {
        setUpModel();
        setUpTable();
        setupSearchBar();
    }

    private void setUpModel() {
        model = controller.findActives();
    }

    private void setUpTable() {
        formatter.formatTable(table, model);
    }

    private void setupSearchBar() {
        filtering.searchBarConfiguration(model, table, searchBar);
    }

    @Override
    public void actionPerformed(ActionEvent click) {

    }
}
