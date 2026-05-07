package br.com.clients;

import br.com.configurations.ErrorResponse;
import br.com.exceptions.ApiException;
import br.com.exceptions.CommunicationException;
import br.com.exceptions.SerializationException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class BaseClient {
    protected static final HttpClient client = HttpClient.newHttpClient();

    protected static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    protected <T> T get(String url, TypeReference<T> type) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return send(request, type);
    }

    protected <T> T post(String url, Object body, TypeReference<T> type) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(toJson(body)))
                .build();

        return send(request, type);
    }

    private <T> T send(HttpRequest request, TypeReference<T> type) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return fromJson(response.body(), type);

        } catch (ConnectException e) {
            throw new CommunicationException("Servidor indisponível.");

        } catch (IOException | InterruptedException e) {
            throw new CommunicationException("Erro de comunicação com a API.");
        }
    }

    private void validateResponse(HttpResponse<String> response) {
        if (response.statusCode() < 400) {
            return;
        }

        try {
            ErrorResponse error = mapper.readValue(response.body(), ErrorResponse.class);
            throw new ApiException(error.getMessage());

        } catch (IOException e) {
            throw new ApiException("Erro inesperado: HTTP " + response.statusCode());
        }
    }

    protected String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);

        } catch (Exception e) {
            throw new SerializationException("Erro ao converter objeto para JSON.", e);
        }
    }

    protected <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);

        } catch (Exception e) {
            throw new SerializationException("Erro ao converter JSON para objeto.", e
            );
        }
    }
}