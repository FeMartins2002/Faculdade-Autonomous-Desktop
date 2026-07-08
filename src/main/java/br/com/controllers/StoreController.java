package br.com.controllers;

import br.com.dtos.requests.store.*;
import br.com.services.StoreService;

import javax.swing.table.DefaultTableModel;

public class StoreController {
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public boolean createStore(CreateStoreDTO dto) {
        return storeService.createStore(dto);
    }

    public boolean updateStore(UpdateStoreDTO dto) {
        return storeService.updateStore(dto);
    }

    public boolean deleteStore(String id) {
        return storeService.deleteStore(id);
    }

    public DefaultTableModel findAll() {
        return storeService.findAll();
    }
}
