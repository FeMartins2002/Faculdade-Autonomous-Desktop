package br.com.services;

import br.com.clients.DashboardClient;
import br.com.dtos.responses.dashboard.*;
import br.com.services.utilities.DatasetConverter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.List;

public class DashboardService {
    private final DashboardClient client;

    public DashboardService(DashboardClient client) {
        this.client = client;
    }

    public JFreeChart findMonthlyScalesByYear(int year) {
        List<MonthlyScaleDTO> result = client.findMonthlyScalesByYear(year);
        DefaultCategoryDataset dataset = DatasetConverter.toMonthlyScalesDataset(result);

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                null,
                "Total",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                true
        );

        return chart;
    }

    public JFreeChart findScaleCompletedsAndCancelledsByYear(int year) {
        List<ScaleStatusCountDTO> result = client.findScaleCompletedsAndCancelledsByYear(year);
        DefaultPieDataset dataset = DatasetConverter.toScaleStatusDataset(result);

        JFreeChart chart = ChartFactory.createRingChart(
                null,
                dataset,
                false,
                true,
                true
        );

        return chart;
    }

    public JFreeChart findTopFreelancersScalesByYear(int year) {
        List<FreelancerRankingDTO> result = client.findTopFreelancersScalesByYear(year);
        DefaultCategoryDataset dataset = DatasetConverter.toFreelancerRankingDataset(result);

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                null,
                null,
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                true,
                false
        );

        return chart;
    }

    public JFreeChart findTopFreelancersPaymentByYear(int year) {
        List<FreelancerCostDTO> result = client.findTopFreelancersPaymentByYear(year);
        DefaultCategoryDataset dataset = DatasetConverter.toFreelancerPaymentDataset(result);

        JFreeChart chart = ChartFactory.createBarChart(
                null,
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        return chart;
    }
}
