package br.com.configurations;

import br.com.clients.StoreClient;
import br.com.controllers.StoreController;
import br.com.services.StoreService;
import br.com.services.utilities.TableModelConverter;

public class StoreConfig {
    private StoreClient client;
    private StoreService service;
    private StoreController controller;
    private TableModelConverter converter;

    public StoreConfig() {
        converter =  new TableModelConverter();
        client = new StoreClient();
        service = new StoreService(client, converter);
        controller = new StoreController(service);
    }

    public StoreController getController() {
        return controller;
    }
}
