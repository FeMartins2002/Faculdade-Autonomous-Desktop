package br.com.controllers;

import br.com.dtos.requests.freelancer.CreateFreelancerDTO;
import br.com.services.FreelancerService;

import javax.swing.table.DefaultTableModel;

public class FreelancerController {
    private FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    public boolean createFreelancer(CreateFreelancerDTO dto) {
        return freelancerService.createFreelancer(dto);
    }

    public DefaultTableModel findActives() {
        return freelancerService.findActives();
    }

    public DefaultTableModel findInactives() {
        return freelancerService.findInactives();
    }
}
