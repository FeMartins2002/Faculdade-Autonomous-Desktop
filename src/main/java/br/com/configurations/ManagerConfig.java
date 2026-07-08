package br.com.configurations;

import br.com.clients.ManagerClient;
import br.com.controllers.ManagerController;
import br.com.services.ManagerService;

public class ManagerConfig {
    private ManagerClient client;
    private ManagerService service;
    private ManagerController controller;

    public ManagerConfig() {
        client = new ManagerClient();
        service = new ManagerService(client);
        controller = new ManagerController(service);
    }

    public ManagerController getController() {
        return controller;
    }
}
