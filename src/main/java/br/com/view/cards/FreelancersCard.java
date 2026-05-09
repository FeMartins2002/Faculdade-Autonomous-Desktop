package br.com.view.cards;

import br.com.controllers.FreelancerController;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;
import br.com.view.forms.FreelancerForm;
import br.com.view.utilities.TableFiltering;
import br.com.view.utilities.TableFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FreelancersCard extends JPanel implements ActionListener {
    private JLabel title, searchLabel;
    private JTextField searchBar;
    private JPanel buttonsPanel;
    private JButton addButton, editButton, removeButton, refreshButton;
    private final FreelancerController controller;
    private TableFiltering filtering;
    private TableFormatter formatter;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JTable table = new JTable();

    public FreelancersCard(FreelancerController controller) {
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
        buildRemoveButton();
        buildRefreshButton();
        buildButtonsPanel();
        buildSearchLabel();
        buildSearchBar();
    }

    private void buildTitle() {
        title = new LabelBuilder("Freelancers")
                .align(SwingConstants.CENTER)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();
    }

    private void buildAddButton() {
        addButton = new ButtonBuilder("Adicionar")
                .background(new Color(59, 111, 146))
                .tooltip("Adicionar Freelancer")
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
                .tooltip("Editar Freelancer")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        editButton.addActionListener(this);
    }

    private void buildRemoveButton() {
        removeButton = new ButtonBuilder("Remover")
                .background(new Color(179, 0, 0))
                .tooltip("Remover Freelancer")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        removeButton.addActionListener(this);
    }

    private void buildRefreshButton() {
        refreshButton = new JButton();
        refreshButton.addActionListener(this);
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
        model = controller.findActives();
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
        gbc.gridwidth = 4;
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
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        buttonsPanel.add(spacer, gbc);

        gbc.gridx = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        buttonsPanel.add(removeButton, gbc);
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
        if(click.getSource() == addButton) {
            new FreelancerForm(controller, refreshButton);
        }

        if(click.getSource() == refreshButton) {
            loadTable();
        }
    }
}
