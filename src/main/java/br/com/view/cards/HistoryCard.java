package br.com.view.cards;

import br.com.controllers.ScaleController;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.builders.TextFieldBuilder;
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

public class HistoryCard extends JPanel implements ActionListener {
    private JLabel title, searchLabel;
    private JTextField searchBar;
    private JPanel buttonsPanel;
    private JButton refreshButton, viewObservation;
    private ScaleController scaleController;
    private TableFiltering filtering;
    private TableFormatter formatter;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JTable table = new JTable();

    public HistoryCard(ScaleController controller) {
        filtering = new TableFiltering();
        formatter = new TableFormatter();
        this.scaleController = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(21, 32, 43));

        buildComponents();
        loadTable();
        buildScroll(table);
        organizeComponents();
    }

    private void buildComponents() {
        buildTitle();
        buildRefreshButton();
        buildViewObservation();
        buildButtonsPanel();
        buildSearchLabel();
        buildSearchBar();
    }

    private void buildTitle() {
        title = new LabelBuilder("Histórico de Escalas")
                .align(SwingConstants.CENTER)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();
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

    private void buildViewObservation() {
        viewObservation = new ButtonBuilder("Visualizar Observação")
                .background(new Color(59, 111, 146))
                .tooltip("Visualizar Observação")
                .size(200, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        viewObservation.addActionListener(this);
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
        model = scaleController.findScalesClosed();
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
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        buttonsPanel.add(viewObservation, gbc);

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
        if (click.getSource() == refreshButton) {
            loadTable();
        }

        if(click.getSource() == viewObservation) {
            handleScale();
        }
    }

    private void handleScale() {
        if (!validateSelectedRow()) return;

        String id = table.getValueAt(table.getSelectedRow(), 0).toString();
        String observation = scaleController.findScaleObservationById(id);

        JOptionPane.showMessageDialog(this, observation);
    }

    private boolean validateSelectedRow() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma escala!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
