package br.com.configurations;

import br.com.clients.StoreClient;
import br.com.controllers.StoreController;
import br.com.services.StoreService;

public class StoreConfig {
    private StoreClient client;
    private StoreService service;
    private StoreController controller;

    public StoreConfig() {
        client = new StoreClient();
        service = new StoreService(client);
        controller = new StoreController(service);
    }

    public StoreController getController() {
        return controller;
    }
}
