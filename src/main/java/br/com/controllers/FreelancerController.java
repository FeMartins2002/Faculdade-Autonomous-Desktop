package br.com.controllers;

import br.com.dtos.requests.freelancer.CreateFreelancerDTO;
import br.com.services.FreelancerService;

import com.fasterxml.jackson.core.JsonProcessingException;

public class FreelancerController {
    private FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    public boolean createFreelancer(CreateFreelancerDTO dto) throws JsonProcessingException {
        return freelancerService.createFreelancer(dto);
    }
}
