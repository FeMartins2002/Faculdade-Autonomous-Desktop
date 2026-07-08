package br.com.view.cards;

import br.com.controllers.DashboardController;
import br.com.view.builders.ButtonBuilder;
import br.com.view.builders.ComboBuilder;
import br.com.view.builders.LabelBuilder;
import br.com.view.utilities.ChartFormatter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DashboardCard extends JPanel {
    private final DashboardController controller;
    private JComboBox<String> comboYears;
    private JLabel title;
    private JButton refreshButton;
    private JPanel chartContainer;
    private ChartFormatter formatter;

    public DashboardCard(DashboardController controller) {
        formatter = new ChartFormatter();
        this.controller = controller;

        initializeComponents();
        loadGraphics();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 32, 43));
        setBorder(new EmptyBorder(1, 1, 1, 1));

        add(createHeader(), BorderLayout.NORTH);
        add(createChartsContainer(), BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(new Color(21, 32, 43));

        buildTitle();
        buildCombo();
        buildRefreshButton();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        headerPanel.add(title, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 20, 20);
        headerPanel.add(comboYears, gbc);

        gbc.gridx = 1;
        headerPanel.add(refreshButton, gbc);

        return headerPanel;
    }

    private void buildTitle() {
        title = new LabelBuilder("Estatisticas Anuais")
                .align(SwingConstants.CENTER)
                .textColor(Color.WHITE)
                .fontSize(20)
                .build();

    }

    private void buildCombo() {
        List<String> years = new ArrayList<>();
        years.add("2026");
        years.add("2025");
        years.add("2024");
        years.add("2023");
        years.add("2022");
        years.add("2021");
        years.add("2020");

        comboYears = new ComboBuilder<>(years)
                .size(150, 30)
                .build();
    }

    private void buildRefreshButton() {
        refreshButton = new ButtonBuilder("Atualizar")
                .background(new Color(59, 111, 146))
                .tooltip("Atualizar Gráficos")
                .size(100, 30)
                .textColor(Color.WHITE)
                .fontSize(15)
                .opaque(true)
                .build();
        refreshButton.addActionListener(e -> loadGraphics());
    }

    private JPanel createChartsContainer() {
        chartContainer = new JPanel(new GridLayout(2, 2, 20, 20));
        chartContainer.setBackground(new Color(21, 32, 43));
        return chartContainer;
    }

    private void loadGraphics() {
        chartContainer.removeAll();

        chartContainer.add(createChartPanel(initializeChart1(), "Escalas por Mês"));
        chartContainer.add(createChartPanel(initializeChart2(), "Escalas Concluidas vs Canceladas"));
        chartContainer.add(createChartPanel(initializeChart3(), "Top Freelancers por Escalas"));
        chartContainer.add(createChartPanel(initializeChart4(), "Top Freelancers por Recebimento"));

        chartContainer.revalidate();
        chartContainer.repaint();
    }

    private JPanel createChartPanel(ChartPanel chartPanel, String title) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleChart = new LabelBuilder(title)
                .align(SwingConstants.CENTER)
                .size(30, 35)
                .background(Color.RED)
                .textColor(Color.BLACK)
                .fontSize(18)
                .build();

        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panel.add(titleChart, BorderLayout.NORTH);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private ChartPanel initializeChart1() {
        JFreeChart chart = controller.findMonthlyScalesByYear(selectedYear());
        formatter.applyModernVerticalBarChartStyle(chart);

        return new ChartPanel(chart);
    }

    private ChartPanel initializeChart2() {
        JFreeChart chart = controller.findScaleCompletedsAndCancelledsByYear(selectedYear());
        formatter.applyRingChartStyle(chart);

        return new ChartPanel(chart);
    }

    private ChartPanel initializeChart3() {
        JFreeChart chart =  controller.findTopFreelancersScalesByYear(selectedYear());
        formatter.applyModernHorizontalBarChartStyle(chart);

        return new ChartPanel(chart);
    }

    private ChartPanel initializeChart4() {
        JFreeChart chart = controller.findTopFreelancersPaymentByYear(selectedYear());
        formatter.applyModernVerticalBarChartStyle(chart);

        return new ChartPanel(chart);
    }

    private int selectedYear() {
        return Integer.parseInt(comboYears.getSelectedItem().toString());
    }
}
