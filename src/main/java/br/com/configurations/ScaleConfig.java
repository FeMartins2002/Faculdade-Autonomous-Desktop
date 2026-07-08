package br.com.configurations;

import br.com.clients.ScaleClient;
import br.com.controllers.ScaleController;
import br.com.services.ScaleService;
import br.com.services.utilities.TableModelConverter;
import br.com.validators.ScaleValidator;

public class ScaleConfig {
    private ScaleClient client;
    private ScaleService service;
    private ScaleController controller;
    private TableModelConverter converter;
    private ScaleValidator validator;

    public ScaleConfig() {
        converter =  new TableModelConverter();
        validator = new ScaleValidator();
        client = new ScaleClient();
        service = new ScaleService(client, converter, validator);
        controller = new ScaleController(service);
    }

    public ScaleController getController() {
        return controller;
    }
}
