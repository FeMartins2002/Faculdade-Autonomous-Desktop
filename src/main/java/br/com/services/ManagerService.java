package br.com.services;

import br.com.clients.ManagerClient;
import br.com.dtos.requests.manager.LoginDTO;
import br.com.entities.Manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerService {
    private final ManagerClient client;
    private static ObjectMapper mapper = new ObjectMapper();

    public ManagerService(ManagerClient client) {
        this.client = client;
    }

    public boolean login(LoginDTO dto) throws JsonProcessingException {
        var response = client.login(dto);

        if (response.statusCode() == 200) {
            mapper = new ObjectMapper();
            Manager manager = mapper.readValue(response.body(), Manager.class);
            return true;
        }

        return false;
    }
}
