package br.com.services;

import br.com.clients.ScaleClient;
import br.com.configurations.Session;
import br.com.dtos.requests.scale.*;
import br.com.dtos.responses.freelancer.FreelancerOption;
import br.com.dtos.responses.scale.ObservationResponse;
import br.com.dtos.responses.store.StoreOption;
import br.com.entities.Scale;
import br.com.enums.Role;
import br.com.enums.ScaleStatus;

import br.com.services.utilities.TableModelConverter;
import br.com.validators.ScaleValidator;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ScaleService {
    private ScaleClient client;
    private TableModelConverter converter;
    private ScaleValidator validator;

    public ScaleService(ScaleClient client, TableModelConverter converter, ScaleValidator validator) {
        this.client = client;
        this.converter = converter;
        this.validator = validator;
    }

    public boolean createScale(CreateScaleDTO dto) {
        if(validateAuthorization()) return false;
        validator.validateCreateScale(dto);

        dto.setManagerId(Session.getManagerLogged().getId());

        Scale created = client.createScale(dto);
        return created != null && created.getId() != null;
    }

    public boolean updateScale(UpdateScaleDTO dto) {
        if(validateAuthorization()) return false;
        validator.validateUpdateScale(dto);

        dto.setManagerId(Session.getManagerLogged().getId());

        Scale created = client.updateScale(dto);
        return created != null && created.getId() != null;
    }


    public void confirmedScale(String id) {
        CompletedScaleDTO completed = new CompletedScaleDTO();

        completed.setManagerId(Session.getManagerLogged().getId());
        completed.setScaleId(Long.parseLong(id));

        client.completedScale(completed);
    }

    public void cancelledScale(String id, String observation) {
        CancelledScaleDTO cancelled = new CancelledScaleDTO();

        cancelled.setManagerId(Session.getManagerLogged().getId());
        cancelled.setScaleId(Long.parseLong(id));
        cancelled.setObservation(observation);

        client.canceledScale(cancelled);
    }

    public DefaultTableModel findByStatus(ScaleStatus status) {
        return converter.createScaleModel(client.findByStatus(status));
    }

    public DefaultTableModel findScalesClosed() {
        return converter.createScaleExtendedModel(client.findScalesClosed());
    }

    public String findObservationById(String id) {
        ObservationResponse observation = client.findScaleObservationById(Long.parseLong(id));
        return observation.getObservation();
    }

    public List<FreelancerOption> getFreelancersOptions() {
        return client.findOptionsFreelancers();
    }

    public  List<StoreOption> getStoresOptions() {
        return client.findOptionsStores();
    }

    private boolean validateAuthorization() {
        return Session.getManagerLogged().getRole() != Role.ROLE_MANAGER;
    }
}
