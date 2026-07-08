package br.com.services;

import br.com.clients.StoreClient;
import br.com.configurations.Session;
import br.com.dtos.requests.store.*;
import br.com.entities.Store;
import br.com.enums.Role;
import br.com.services.utilities.TableModelConverter;
import br.com.validators.StoreValidator;

import javax.swing.table.DefaultTableModel;

public class StoreService {
    private StoreClient client;
    private TableModelConverter converter;
    private StoreValidator validator;

    public StoreService(StoreClient client, TableModelConverter converter, StoreValidator validator) {
        this.client = client;
        this.converter = converter;
        this.validator = validator;
    }

    public boolean createStore(CreateStoreDTO dto) {
        if(validateAuthorization()) return false;
        validator.validateStore(dto);

        dto.setManagerId(Session.getManagerLogged().getId());

        Store created = client.createStore(dto);
        return created != null && created.getId() != null;
    }

    public boolean updateStore(UpdateStoreDTO dto) {
        if(validateAuthorization()) return false;
        validator.validateStore(dto);

        dto.setManagerId(Session.getManagerLogged().getId());

        Store created = client.updateStore(dto);
        return created != null && created.getId() != null;
    }

    public boolean deleteStore(String id) {
        if(validateAuthorization()) return false;
        DeleteStoreDTO dto = new DeleteStoreDTO();

        dto.setManagerId(Session.getManagerLogged().getId());
        dto.setStoreId(Long.parseLong(id));

        Store store = client.deleteStore(dto);
        return !store.isActive();
    }

    public DefaultTableModel findAll() {
        return converter.createStoreModel(client.findAll());
    }

    private boolean validateAuthorization() {
        return Session.getManagerLogged().getRole() != Role.ROLE_MANAGER;
    }
}
