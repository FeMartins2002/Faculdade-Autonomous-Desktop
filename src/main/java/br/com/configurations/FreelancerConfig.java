package br.com.configurations;

import br.com.clients.FreelancerClient;
import br.com.controllers.FreelancerController;
import br.com.services.FreelancerService;
import br.com.services.utilities.TableModelConverter;

public class FreelancerConfig {
    private FreelancerClient client;
    private FreelancerService service;
    private FreelancerController controller;
    private TableModelConverter converter;

    public FreelancerConfig() {
        converter =  new TableModelConverter();
        client = new FreelancerClient();
        service = new FreelancerService(client, converter);
        controller = new FreelancerController(service);
    }

    public FreelancerController getController() {
        return controller;
    }
}
