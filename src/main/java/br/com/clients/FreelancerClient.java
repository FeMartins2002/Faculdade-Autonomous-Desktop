package br.com.clients;

import br.com.dtos.requests.freelancer.*;
import br.com.entities.Freelancer;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static br.com.clients.routes.FreelancerRoutes.*;

public class FreelancerClient extends BaseClient {

    public Freelancer createFreelancer(CreateFreelancerDTO dto) {
        return post(BASE_URL, dto, new TypeReference<Freelancer>() {});
    }

    public Freelancer updateFreelancer(UpdateFreelancerDTO dto) {
        return put(BASE_URL, dto, new TypeReference<Freelancer>() {});
    }

    public Freelancer deleteFreelancer(DeleteFreelancerDTO dto) {
        return delete(BASE_URL, dto, new TypeReference<Freelancer>() {});
    }

    public List<Freelancer> findActives() {
        return get(FREELANCER_ACTIVES, new TypeReference<List<Freelancer>>() {});
    }

    public List<Freelancer> findInactives() {
        return get(FREELANCER_INACTIVES, new TypeReference<List<Freelancer>>() {});
    }
}
