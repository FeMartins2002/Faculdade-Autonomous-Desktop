package br.com.clients;

import br.com.clients.exceptions.*;
import br.com.dtos.requests.store.CreateStoreDTO;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static br.com.clients.routes.StoreRoutes.*;

public class StoreClient extends BaseClient {

    public HttpResponse<String> createStore(CreateStoreDTO dto) {
        try {
            String json = toJson(dto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(STORE))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);
            return response;

        } catch (HttpClientException e) {
            throw e;
        } catch (Exception e) {
            throw new CommunicationException("Erro ao criar loja", e);
        }
    }
}