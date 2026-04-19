package br.com.services;

import br.com.clients.ScaleClient;
import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.enums.ScaleStatus;

import br.com.services.utilities.TableModelConverter;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.table.DefaultTableModel;

public class ScaleService {
    private ScaleClient client;
    private TableModelConverter converter;

    public ScaleService(ScaleClient client) {
        this.client = client;
        converter = new TableModelConverter();
    }

    public boolean createScale(CreateScaleDTO dto) {
        validateCreateScale(dto);

        var response = client.createScale(dto);
        return response.statusCode() == 201;
    }

    private void validateCreateScale(CreateScaleDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Escala não pode ser nulo");
        }
    }

    public DefaultTableModel findByStatus(ScaleStatus status) throws JsonProcessingException {
        return converter.createScaleModel(client.findByStatus(status));
    }
}
