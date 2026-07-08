package br.com.configurations;

import br.com.clients.DashboardClient;
import br.com.controllers.DashboardController;
import br.com.services.DashboardService;

public class DashboardConfig {
    private DashboardClient client;
    private DashboardService service;
    private DashboardController controller;

    public DashboardConfig() {
        client = new DashboardClient();
        service = new DashboardService(client);
        controller = new DashboardController(service);
    }

    public DashboardController getController() {
        return controller;
    }
}
