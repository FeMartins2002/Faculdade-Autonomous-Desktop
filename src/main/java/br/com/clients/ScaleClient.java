package br.com.clients;

import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.entities.Scale;
import br.com.enums.ScaleStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ScaleClient {
    private static final String URL_CREATE = "http://localhost:8080/autonomous/scale";
    private static final String URL_FINDSCALES = "http://localhost:8080/autonomous/scale?status=";


    public HttpResponse<String> createScale(CreateScaleDTO dto) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            String json = mapper.writeValueAsString(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_CREATE))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer requisição POST", e);
        }
    }

    public List<Scale> findByStatus(ScaleStatus status) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String urlWithParam = URL_FINDSCALES + status;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlWithParam))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            return mapper.readValue(
                    response.body(),
                    new TypeReference<List<Scale>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar escalas por status", e);
        }
    }
}
