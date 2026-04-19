package br.com.clients;

import br.com.clients.exceptions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public abstract class BaseClient {
    protected static final HttpClient client = HttpClient.newHttpClient();

    protected static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    protected String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new SerializationException("Erro ao converter objeto para JSON", e);
        }
    }

    protected <T> T fromJson(String json, com.fasterxml.jackson.core.type.TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            throw new SerializationException("Erro ao converter JSON para objeto", e);
        }
    }

    protected void validateResponse(HttpResponse<String> response) {
        if (response.statusCode() >= 400) {
            throw new HttpClientException(response.statusCode(), response.body());
        }
    }
}