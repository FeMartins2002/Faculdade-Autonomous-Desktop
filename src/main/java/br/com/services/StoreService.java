package br.com.services;

import br.com.clients.StoreClient;
import br.com.dtos.requests.store.CreateStoreDTO;

import br.com.services.utilities.TableModelConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

public class StoreService {
    private StoreClient client;
    private TableModelConverter converter;

    public StoreService(StoreClient client) {
        this.client = client;
        converter = new TableModelConverter();
    }

    public boolean createStore(CreateStoreDTO dto) throws JsonProcessingException {
        validateStore(dto);

        var response = client.createStore(dto);
        return response.statusCode() == 201;
    }

    private void validateStore(CreateStoreDTO dto) {
        if(dto == null) {
            throw new IllegalArgumentException("Loja não pode ser nulo");
        }
    }
}
