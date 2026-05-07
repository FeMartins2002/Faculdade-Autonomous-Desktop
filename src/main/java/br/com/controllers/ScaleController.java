package br.com.controllers;

import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.enums.ScaleStatus;
import br.com.services.ScaleService;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.table.DefaultTableModel;

public class ScaleController {
    private ScaleService scaleService;

    public ScaleController(ScaleService scaleService) {
        this.scaleService = scaleService;
    }

    public boolean createScale(CreateScaleDTO dto) {
        return scaleService.createScale(dto);
    }

    public DefaultTableModel findByStatus(ScaleStatus status) throws JsonProcessingException {
        return scaleService.findByStatus(status);
    }
}
