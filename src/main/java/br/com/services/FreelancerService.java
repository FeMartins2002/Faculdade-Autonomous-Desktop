package br.com.services;

import br.com.clients.FreelancerClient;
import br.com.configurations.Session;
import br.com.dtos.requests.freelancer.CreateFreelancerDTO;

import br.com.entities.Freelancer;
import br.com.services.utilities.TableModelConverter;

import javax.swing.table.DefaultTableModel;

public class FreelancerService {
    private FreelancerClient client;
    private TableModelConverter converter;

    public FreelancerService(FreelancerClient client, TableModelConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    public boolean createFreelancer(CreateFreelancerDTO dto) {
        validateFreelancer(dto);

        // Correto
        //dto.setManagerId(Session.getManagerLogged().getId());

        // Remover depois
        dto.setManagerId(1L);

        Freelancer created = client.createFreelancer(dto);
        return created != null && created.getId() != null;
    }

    public DefaultTableModel findActives() {
        return converter.createFreelancerModel(client.findActives());
    }

    public DefaultTableModel findInactives() {
        return converter.createFreelancerModel(client.findInactives());
    }

    private void validateFreelancer(CreateFreelancerDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Freelancer não pode ser nulo");
        }

        if (dto.getCpf() == null || dto.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF inválido");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail inválido");
        }

        if (dto.getPhone() == null || dto.getPhone().isBlank()) {
            throw new IllegalArgumentException("Telefone inválido");
        }
    }
}
