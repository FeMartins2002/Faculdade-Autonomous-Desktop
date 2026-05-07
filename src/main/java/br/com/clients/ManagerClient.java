package br.com.clients;

import br.com.dtos.requests.manager.LoginDTO;

import br.com.entities.Manager;
import com.fasterxml.jackson.core.type.TypeReference;

import static br.com.clients.routes.ManagerRoutes.AUTH;

public class ManagerClient extends BaseClient {

    public Manager login(LoginDTO dto) {
        return post(AUTH, dto, new TypeReference<Manager>() {});
    }
}