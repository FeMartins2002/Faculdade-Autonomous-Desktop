package br.com.controllers;

import br.com.services.DashboardService;
import org.jfree.chart.JFreeChart;

public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    public JFreeChart findMonthlyScalesByYear(int year) {
        return service.findMonthlyScalesByYear(year);
    }

    public JFreeChart findScaleCompletedsAndCancelledsByYear(int year) {
        return service.findScaleCompletedsAndCancelledsByYear(year);
    }

    public JFreeChart findTopFreelancersScalesByYear(int year) {
        return service.findTopFreelancersScalesByYear(year);
    }

    public JFreeChart findTopFreelancersPaymentByYear(int year) {
        return service.findTopFreelancersPaymentByYear(year);
    }
}
