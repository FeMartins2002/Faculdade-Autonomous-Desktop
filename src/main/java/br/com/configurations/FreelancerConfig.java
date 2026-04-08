package br.com.configurations;

import br.com.clients.FreelancerClient;
import br.com.controllers.FreelancerController;
import br.com.services.FreelancerService;

public class FreelancerConfig {
    private FreelancerClient client;
    private FreelancerService service;
    private FreelancerController controller;

    public FreelancerConfig() {
        client = new FreelancerClient();
        service = new FreelancerService(client);
        controller = new FreelancerController(service);
    }

    public FreelancerController getController() {
        return controller;
    }
}
