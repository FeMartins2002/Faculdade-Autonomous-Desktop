package br.com.clients;

import br.com.clients.exceptions.*;
import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.entities.Scale;
import br.com.enums.ScaleStatus;

import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static br.com.clients.routes.ScaleRoutes.*;

public class ScaleClient extends BaseClient {

    public HttpResponse<String> createScale(CreateScaleDTO dto) {
        try {
            String json = toJson(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SCALE))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);
            return response;

        } catch (HttpClientException e) {
            throw e;
        } catch (Exception e) {
            throw new CommunicationException("Erro ao criar escala", e);
        }
    }

    public List<Scale> findByStatus(ScaleStatus status) {
        try {
            String urlWithParam = FIND_SCALES + status;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlWithParam))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return fromJson(
                    response.body(),
                    new TypeReference<List<Scale>>() {}
            );

        } catch (HttpClientException e) {
            throw e;
        } catch (Exception e) {
            throw new CommunicationException("Erro ao buscar escalas por status", e);
        }
    }
}