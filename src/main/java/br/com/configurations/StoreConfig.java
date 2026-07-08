package br.com.configurations;

import br.com.clients.StoreClient;
import br.com.controllers.StoreController;
import br.com.services.StoreService;
import br.com.services.utilities.TableModelConverter;
import br.com.validators.StoreValidator;

public class StoreConfig {
    private StoreClient client;
    private StoreService service;
    private StoreController controller;
    private TableModelConverter converter;
    private StoreValidator validator;

    public StoreConfig() {
        converter =  new TableModelConverter();
        validator = new StoreValidator();
        client = new StoreClient();
        service = new StoreService(client, converter, validator);
        controller = new StoreController(service);
    }

    public StoreController getController() {
        return controller;
    }
}
