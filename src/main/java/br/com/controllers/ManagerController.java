package br.com.controllers;

import br.com.dtos.requests.manager.LoginDTO;
import br.com.services.ManagerService;

public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    public boolean login(LoginDTO dto) {
        return managerService.login(dto);
    }
}
