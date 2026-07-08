package br.com.clients;

import br.com.dtos.requests.manager.ChangePasswordDTO;
import br.com.dtos.requests.manager.LoginDTO;

import br.com.dtos.responses.manager.TokenResponse;
import br.com.entities.Manager;
import com.fasterxml.jackson.core.type.TypeReference;

import static br.com.clients.routes.ManagerRoutes.*;

public class ManagerClient extends BaseClient {

    public Manager login(LoginDTO dto) {
        return post(AUTH, dto, new TypeReference<Manager>() {});
    }

    public Manager changePassword(ChangePasswordDTO dto) {
        return post(PASSWORD, dto, new TypeReference<Manager>() {});
    }

    public TokenResponse getToken(String email) {
        return post(TOKEN + email, email, new TypeReference<TokenResponse>() {});
    }
}
