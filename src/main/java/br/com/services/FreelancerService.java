package br.com.services;

import br.com.clients.FreelancerClient;
import br.com.dtos.requests.freelancer.CreateFreelancerDTO;

import br.com.services.utilities.TableModelConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.table.DefaultTableModel;

public class FreelancerService {
    private FreelancerClient client;
    private TableModelConverter converter;

    public FreelancerService(FreelancerClient client) {
        this.client = client;
        converter = new TableModelConverter();
    }

    public boolean createFreelancer(CreateFreelancerDTO dto) throws JsonProcessingException {
        validateFreelancer(dto);

        var response = client.createFreelancer(dto);
        return response.statusCode() == 201;
    }

    private void validateFreelancer(CreateFreelancerDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Freelancer não pode ser nulo");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        /*
         private Long managerId; <-- Talvez não precise validar / Pendente implementação de buscar gestor logado na aplicação
         private String cpf;
         private String name;
         private String email;
         private String phone;
         private String address;
         */
    }

    public DefaultTableModel findActives() {
        return converter.createFreelancerModel(client.findActives());
    }

    public DefaultTableModel findInactives() {
        return converter.createFreelancerModel(client.findInactives());
    }
}
