package br.com.view.utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TableFormatter {

    public void formatTable(JTable table, DefaultTableModel model) {
        table.setModel(model);

        configureTable(table);
        configureHeader(table);
        configureSelection(table);
        configureRenderer(table, model);
        configureColumns(table);
    }

    private void configureTable(JTable table) {
        table.setRowHeight(50);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        table.setBackground(Color.WHITE);
        table.setForeground(Color.DARK_GRAY);

        table.setFocusable(false);
        table.setDefaultEditor(Object.class, null);
    }

    private void configureHeader(JTable table) {
        JTableHeader header = table.getTableHeader();

        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(45, 52, 54));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
    }

    private void configureSelection(JTable table) {
        table.setSelectionBackground(new Color(9, 132, 227));
        table.setSelectionForeground(Color.WHITE);
    }

    private void configureRenderer(JTable table, DefaultTableModel model) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);

                if (!isSelected) {
                    component.setBackground(row % 2 == 0 ? Color.WHITE : new Color(225, 225, 225));
                }

                return component;
            }
        };

        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void configureColumns(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
    }
}