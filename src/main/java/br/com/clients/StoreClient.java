package br.com.clients;

import br.com.dtos.requests.store.*;
import br.com.entities.Store;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static br.com.clients.routes.StoreRoutes.*;

public class StoreClient extends BaseClient {

    public Store createStore(CreateStoreDTO dto) {
        return post(STORE, dto, new TypeReference<Store>() {});
    }

    public Store updateStore(UpdateStoreDTO dto) {
        return put(STORE, dto, new TypeReference<Store>() {});
    }

    public Store deleteStore(DeleteStoreDTO dto) {
        return delete(STORE, dto, new TypeReference<Store>() {});
    }

    public List<Store> findAll() {
        return get(STORE, new TypeReference<List<Store>>() {});
    }
}
