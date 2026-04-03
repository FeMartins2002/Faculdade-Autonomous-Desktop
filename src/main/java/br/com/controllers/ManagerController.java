package br.com.controllers;

import br.com.dtos.requests.manager.LoginDTO;
import br.com.services.ManagerService;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    public boolean login(LoginDTO dto) throws JsonProcessingException {
        return managerService.login(dto);
    }
}
