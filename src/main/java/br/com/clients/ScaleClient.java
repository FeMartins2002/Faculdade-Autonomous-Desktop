package br.com.clients;

import br.com.dtos.requests.scale.*;
import br.com.dtos.responses.freelancer.FreelancerOption;
import br.com.dtos.responses.scale.ObservationResponse;
import br.com.dtos.responses.store.StoreOption;
import br.com.entities.Scale;
import br.com.enums.ScaleStatus;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static br.com.clients.routes.ScaleRoutes.*;

public class ScaleClient extends BaseClient {

    public Scale createScale(CreateScaleDTO dto) {
        return post(SCALE, dto, new TypeReference<Scale>() {});
    }

    public Scale updateScale(UpdateScaleDTO dto) {
        return put(SCALE, dto, new TypeReference<Scale>() {});
    }

    public Scale completedScale(CompletedScaleDTO completed) {
        return patch(COMPLETED, completed,  new TypeReference<Scale>() {});
    }

    public Scale canceledScale(CancelledScaleDTO cancelled) {
        return patch(CANCELLED, cancelled, new TypeReference<Scale>() {});
    }

    public List<Scale> findByStatus(ScaleStatus status) {
        return get(FIND_SCALES_STATUS + status, new TypeReference<List<Scale>>() {});
    }

    public List<Scale> findScalesClosed() {
        return get(FIND_SCALES_CLOSED, new TypeReference<List<Scale>>() {});
    }

    public ObservationResponse findScaleObservationById(Long id) {
        return get(FIND_SCALE_OBSERVATION + id, new TypeReference<ObservationResponse>() {});
    }

    public List<FreelancerOption> findOptionsFreelancers() {
        return get(FREELANCER_OPTIONS, new TypeReference<List<FreelancerOption>>() {});
    }

    public List<StoreOption> findOptionsStores() {
        return get(STORE_OPTIONS, new TypeReference<List<StoreOption>>() {});
    }
}
