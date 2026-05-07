package br.com.configurations;

import br.com.clients.ScaleClient;
import br.com.controllers.ScaleController;
import br.com.services.ScaleService;
import br.com.services.utilities.TableModelConverter;

public class ScaleConfig {
    private ScaleClient client;
    private ScaleService service;
    private ScaleController controller;
    private TableModelConverter converter;

    public ScaleConfig() {
        converter =  new TableModelConverter();
        client = new ScaleClient();
        service = new ScaleService(client, converter);
        controller = new ScaleController(service);
    }

    public ScaleController getController() {
        return controller;
    }
}
