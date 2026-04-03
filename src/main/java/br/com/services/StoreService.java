package br.com.services;

import br.com.clients.StoreClient;
import br.com.dtos.requests.store.CreateStoreDTO;

import com.fasterxml.jackson.core.JsonProcessingException;

public class StoreService {
    private StoreClient client;

    public StoreService(StoreClient client) {
        this.client = client;
    }

    public boolean createStore(CreateStoreDTO dto) throws JsonProcessingException {
        var response = client.createStore(dto);

        return response.statusCode() == 201;
    }
}
