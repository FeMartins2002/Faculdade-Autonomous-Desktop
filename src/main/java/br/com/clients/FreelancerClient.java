package br.com.clients;

import br.com.dtos.requests.freelancer.CreateFreelancerDTO;
import br.com.dtos.responses.FreelancerOption;
import br.com.entities.Freelancer;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static br.com.clients.routes.FreelancerRoutes.*;

public class FreelancerClient extends BaseClient {

    public Freelancer createFreelancer(CreateFreelancerDTO dto) {
        return post(FREELANCER, dto, new TypeReference<Freelancer>() {});
    }

    public List<Freelancer> findActives() {
        return get(FREELANCER_ACTIVES, new TypeReference<List<Freelancer>>() {});
    }

    public List<Freelancer> findInactives() {
        return get(FREELANCER_INACTIVES, new TypeReference<List<Freelancer>>() {});
    }

    public List<FreelancerOption> findOptions() {
        return get(FREELANCER_OPTIONS, new TypeReference<List<FreelancerOption>>() {});
    }
}