package br.com.controllers;

import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.entities.Scale;
import br.com.enums.ScaleStatus;
import br.com.services.ScaleService;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class ScaleController {
    private ScaleService  scaleService;

    public ScaleController(ScaleService scaleService) {
        this.scaleService = scaleService;
    }

    public boolean createScale(CreateScaleDTO dto) throws JsonProcessingException {
        return scaleService.createScale(dto);
    }

    public List<Scale> findByStatus(ScaleStatus status) throws JsonProcessingException {
        return scaleService.findByStatus(status);
    }
}
