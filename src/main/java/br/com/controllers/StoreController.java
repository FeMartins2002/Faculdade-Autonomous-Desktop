package br.com.controllers;

import br.com.dtos.requests.store.CreateStoreDTO;
import br.com.services.StoreService;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.table.DefaultTableModel;

public class StoreController {
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public boolean createStore(CreateStoreDTO dto) throws JsonProcessingException {
        return storeService.createStore(dto);
    }

    public DefaultTableModel findAll() {
        return storeService.findAll();
    }
}
