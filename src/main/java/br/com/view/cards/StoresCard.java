package br.com.view.cards;

import br.com.configurations.Session;
import br.com.controllers.StoreController;
import br.com.enums.Role;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;
import br.com.view.forms.create.StoreForm;
import br.com.view.forms.update.StoreUpdateForm;
import br.com.view.utilities.TableFiltering;
import br.com.view.utilities.TableFormatter;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class StoresCard extends JPanel implements ActionListener {
    private JLabel title, searchLabel;
    private JTextField searchBar;
    private JPanel buttonsPanel;
    private JButton addButton, editButton, removeButton, refreshButton;
    private StoreController controller;
    private TableFiltering filtering;
    private TableFormatter formatter;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JTable table = new JTable();

    public StoresCard(StoreController controller) {
        filtering = new TableFiltering();
        formatter = new TableFormatter();
        this.controller = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(21, 32, 43));

        buildComponents();
        loadTable();
        buildScroll(table);
        organizeComponents();
        hideComponents();
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
        title = new LabelBuilder("Lojas")
                .align(SwingConstants.CENTER)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();
    }

    private void buildAddButton() {
        addButton = new ButtonBuilder("Adicionar")
                .background(new Color(59, 111, 146))
                .tooltip("Adicionar Loja")
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
                .tooltip("Editar Loja")
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
                .tooltip("Remover Loja")
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
        model = controller.findAll();
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
        gbc.gridwidth = 5;
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

    private void hideComponents() {
        if (Session.getManagerLogged().getRole() != Role.ROLE_MANAGER) {
            addButton.setVisible(false);
            editButton.setVisible(false);
            removeButton.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() == addButton) {
            new StoreForm(controller, refreshButton);
        }

        if(click.getSource() == editButton) {
            if(validateSelectedRow()) {
                new StoreUpdateForm(controller, getSelectedRow(), refreshButton);
            }
        }

        if (click.getSource() == removeButton) {
            handleRemove();
        }

        if(click.getSource() == refreshButton) {
            loadTable();
        }
    }

    private void handleRemove() {
        if (!validateSelectedRow()) return;

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente remover esta loja ? está ação não poderá ser desfeita.",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String id = table.getValueAt(table.getSelectedRow(), 0).toString();
        boolean success = controller.deleteStore(id);

        if (success) {
            refreshButton.doClick();
            JOptionPane.showMessageDialog(this, "Loja removida com sucesso");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao remover loja", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] getSelectedRow() {
        String[] row = new String[4];

        row[0] = table.getValueAt(table.getSelectedRow(), 0).toString();
        row[1] = table.getValueAt(table.getSelectedRow(), 1).toString();
        row[2] = table.getValueAt(table.getSelectedRow(), 2).toString();
        row[3] = table.getValueAt(table.getSelectedRow(), 3).toString();
        return row;
    }

    private boolean validateSelectedRow() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
