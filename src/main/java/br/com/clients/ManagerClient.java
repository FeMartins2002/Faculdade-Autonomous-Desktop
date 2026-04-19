package br.com.clients;

import br.com.clients.exceptions.CommunicationException;
import br.com.clients.exceptions.HttpClientException;
import br.com.dtos.requests.manager.LoginDTO;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static br.com.clients.routes.ManagerRoutes.*;

public class ManagerClient extends BaseClient {

    public HttpResponse<String> login(LoginDTO dto) {
        try {
            String json = toJson(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AUTH))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);
            return response;

        } catch (HttpClientException e) {
            throw e;
        } catch (Exception e) {
            throw new CommunicationException("Erro ao fazer login", e);
        }
    }
}
