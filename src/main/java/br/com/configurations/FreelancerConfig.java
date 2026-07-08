package br.com.configurations;

import br.com.clients.FreelancerClient;
import br.com.controllers.FreelancerController;
import br.com.services.FreelancerService;
import br.com.services.utilities.TableModelConverter;
import br.com.validators.FreelancerValidator;

public class FreelancerConfig {
    private FreelancerClient client;
    private FreelancerService service;
    private FreelancerController controller;
    private TableModelConverter converter;
    private FreelancerValidator validator;

    public FreelancerConfig() {
        converter =  new TableModelConverter();
        validator = new FreelancerValidator();
        client = new FreelancerClient();
        service = new FreelancerService(client, converter, validator);
        controller = new FreelancerController(service);
    }

    public FreelancerController getController() {
        return controller;
    }
}
