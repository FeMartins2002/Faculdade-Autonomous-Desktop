package br.com.services;

import br.com.clients.FreelancerClient;
import br.com.configurations.Session;
import br.com.dtos.requests.freelancer.*;
import br.com.entities.Freelancer;
import br.com.enums.Role;
import br.com.services.utilities.TableModelConverter;
import br.com.validators.FreelancerValidator;

import javax.swing.table.DefaultTableModel;

public class FreelancerService {
    private FreelancerClient client;
    private TableModelConverter converter;
    private FreelancerValidator validator;

    public FreelancerService(FreelancerClient client, TableModelConverter converter, FreelancerValidator validator) {
        this.client = client;
        this.converter = converter;
        this.validator = validator;
    }

    public boolean createFreelancer(CreateFreelancerDTO dto) {
        if(validateAuthorization()) return false;
        validator.validateFreelancer(dto);

        dto.setManagerId(Session.getManagerLogged().getId());

        Freelancer created = client.createFreelancer(dto);
        return created != null && created.getId() != null;
    }

    public boolean updateFreelancer(UpdateFreelancerDTO dto) {
        if(validateAuthorization()) return false;
        validator.validateFreelancer(dto);

        dto.setManagerId(Session.getManagerLogged().getId());

        Freelancer created = client.updateFreelancer(dto);
        return created != null && created.getId() != null;
    }

    public boolean deleteFreelancer(String id) {
        if(validateAuthorization()) return false;
        DeleteFreelancerDTO dto = new DeleteFreelancerDTO();

        dto.setManagerId(Session.getManagerLogged().getId());
        dto.setFreelancerId(Long.parseLong(id));

        Freelancer freelancer = client.deleteFreelancer(dto);
        return !freelancer.isActive();
    }

    public DefaultTableModel findActives() {
        return converter.createFreelancerModel(client.findActives());
    }

    public DefaultTableModel findInactives() {
        return converter.createFreelancerModel(client.findInactives());
    }

    private boolean validateAuthorization() {
        return Session.getManagerLogged().getRole() != Role.ROLE_MANAGER;
    }
}
