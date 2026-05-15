package br.com.services;

import br.com.clients.StoreClient;
import br.com.configurations.Session;
import br.com.dtos.requests.store.CreateStoreDTO;

import br.com.entities.Store;
import br.com.services.utilities.TableModelConverter;

import javax.swing.table.DefaultTableModel;

public class StoreService {
    private StoreClient client;
    private TableModelConverter converter;

    public StoreService(StoreClient client, TableModelConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    public boolean createStore(CreateStoreDTO dto) {
        validateStore(dto);

        // Correto
        //dto.setManagerId(Session.getManagerLogged().getId());

        // Remover depois
        dto.setManagerId(1L);

        Store created = client.createStore(dto);
        return created != null && created.getId() != null;
    }

    public DefaultTableModel findAll() {
        return converter.createStoreModel(client.findAll());
    }

    private void validateStore(CreateStoreDTO dto) {
        if(dto == null) {
            throw new IllegalArgumentException("Loja não pode ser nulo");
        }

        if(dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Nome da loja é obrigatório");
        }

        if(dto.getPhone() == null || dto.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Telefone da loja é obrigatório");
        }

        if(dto.getAddress() == null || dto.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Endereço da loja é obrigatório");
        }
    }
}
