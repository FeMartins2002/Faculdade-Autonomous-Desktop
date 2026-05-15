package br.com.clients;

import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.dtos.responses.FreelancerOption;
import br.com.dtos.responses.StoreOption;
import br.com.entities.Scale;
import br.com.enums.ScaleStatus;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static br.com.clients.routes.ScaleRoutes.*;

public class ScaleClient extends BaseClient {

    public Scale createScale(CreateScaleDTO dto) {
        return post(SCALE, dto, new TypeReference<Scale>() {});
    }

    public List<Scale> findByStatus(ScaleStatus status) {
        return get(FIND_SCALES + status, new TypeReference<List<Scale>>() {});
    }

    public List<FreelancerOption> findOptionsFreelancers() {
        return get(FREELANCER_OPTIONS, new TypeReference<List<FreelancerOption>>() {});
    }

    public List<StoreOption> findOptionsStores() {
        return get(STORE_OPTIONS, new TypeReference<List<StoreOption>>() {});
    }
}