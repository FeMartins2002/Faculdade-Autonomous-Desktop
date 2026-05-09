package br.com.services;

import br.com.clients.ManagerClient;
import br.com.configurations.Session;
import br.com.dtos.requests.manager.LoginDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerService {
    private final ManagerClient client;
    private static final ObjectMapper mapper = new ObjectMapper();

    public ManagerService(ManagerClient client) {
        this.client = client;
    }

    public boolean login(LoginDTO dto) {
        validateLogin(dto);

        Session.setManager(client.login(dto));
        return true;
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
