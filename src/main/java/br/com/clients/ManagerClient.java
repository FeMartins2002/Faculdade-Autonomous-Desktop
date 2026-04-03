package br.com.clients;

import br.com.dtos.requests.manager.LoginDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ManagerClient {
    private static final String URL = "http://localhost:8080/autonomous/authentication/manager";

    public HttpResponse<String> login(LoginDTO dto) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer requisição POST", e);
        }
    }
}
