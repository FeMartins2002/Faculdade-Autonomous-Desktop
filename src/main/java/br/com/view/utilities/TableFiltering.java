package br.com.view.utilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TableFiltering {

    public TableFiltering() {

    }

    public void searchBarConfiguration(DefaultTableModel model, JTable table, JTextField searchBar) {
        TableRowSorter<DefaultTableModel> filter;

        filter = new TableRowSorter<>(model);
        table.setRowSorter(filter);

        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            private void updateFilter() {
                String text = searchBar.getText();

                if (text.trim().isEmpty()) {
                    filter.setRowFilter(null);
                } else {
                    filter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }
        });
    }
}
