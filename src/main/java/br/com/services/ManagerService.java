package br.com.services;

import br.com.clients.ManagerClient;
import br.com.dtos.requests.manager.LoginDTO;
import br.com.entities.Manager;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerService {
    private final ManagerClient client;
    private static final ObjectMapper mapper = new ObjectMapper();

    public ManagerService(ManagerClient client) {
        this.client = client;
    }

    public boolean login(LoginDTO dto) {
        validateLogin(dto);

        var response = client.login(dto);

        if (response.statusCode() == 200) {
            try {
                Manager manager = mapper.readValue(response.body(), Manager.class);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao converter Manager", e);
            }
        }

        return false;
    }

    private void validateLogin(LoginDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("LoginDTO não pode ser nulo");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }

        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
    }
}
