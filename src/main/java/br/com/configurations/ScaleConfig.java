package br.com.configurations;

import br.com.clients.ScaleClient;
import br.com.controllers.ScaleController;
import br.com.services.ScaleService;

public class ScaleConfig {
    private ScaleClient client;
    private ScaleService service;
    private ScaleController controller;

    public ScaleConfig() {
        client = new ScaleClient();
        service = new ScaleService(client);
        controller = new ScaleController(service);
    }

    public ScaleController getController() {
        return controller;
    }
}
