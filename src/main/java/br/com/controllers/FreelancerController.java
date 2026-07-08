package br.com.controllers;

import br.com.dtos.requests.freelancer.*;
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

    public boolean updateFreelancer(UpdateFreelancerDTO dto) {
        return freelancerService.updateFreelancer(dto);
    }

    public boolean deleteFreelancer(String id) {
        return freelancerService.deleteFreelancer(id);
    }

    public DefaultTableModel findActives() {
        return freelancerService.findActives();
    }

    public DefaultTableModel findInactives() {
        return freelancerService.findInactives();
    }
}
