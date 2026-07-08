package br.com.controllers;

import br.com.dtos.requests.scale.*;
import br.com.dtos.responses.freelancer.FreelancerOption;
import br.com.dtos.responses.store.StoreOption;
import br.com.enums.ScaleStatus;
import br.com.services.ScaleService;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ScaleController {
    private ScaleService scaleService;

    public ScaleController(ScaleService scaleService) {
        this.scaleService = scaleService;
    }

    public boolean createScale(CreateScaleDTO dto) {
        return scaleService.createScale(dto);
    }

    public boolean updateScale(UpdateScaleDTO dto) {
        return scaleService.updateScale(dto);
    }

    public void confirmedScale(String id) {
        scaleService.confirmedScale(id);
    }

    public void canceledScale(String id, String motivo) {
        scaleService.cancelledScale(id, motivo);
    }

    public DefaultTableModel findByStatus(ScaleStatus status) {
        return scaleService.findByStatus(status);
    }

    public DefaultTableModel findScalesClosed() {
        return scaleService.findScalesClosed();
    }

    public String findScaleObservationById(String id) {
        return scaleService.findObservationById(id);
    }

    public List<FreelancerOption> findOptionsFreelancers() {
        return scaleService.getFreelancersOptions();
    }

    public List<StoreOption>  findOptionsStore() {
        return scaleService.getStoresOptions();
    }
}
