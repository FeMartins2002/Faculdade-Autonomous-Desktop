package br.com.clients;

import br.com.dtos.requests.store.CreateStoreDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StoreClient {
    private static final String URL = "http://localhost:8080/autonomous/store";

    public HttpResponse<String> createStore(CreateStoreDTO dto) {
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
