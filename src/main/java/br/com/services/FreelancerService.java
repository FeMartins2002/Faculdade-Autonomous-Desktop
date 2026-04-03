package br.com.services;

import br.com.clients.FreelancerClient;
import br.com.dtos.requests.freelancer.CreateFreelancerDTO;

import com.fasterxml.jackson.core.JsonProcessingException;

public class FreelancerService {
    private FreelancerClient client;

    public FreelancerService(FreelancerClient client) {
        this.client = client;
    }

    public boolean createFreelancer(CreateFreelancerDTO dto) throws JsonProcessingException {
        var response = client.createFreelancer(dto);

        return response.statusCode() == 201;
    }
}
