package br.com.services;

import br.com.clients.ScaleClient;
import br.com.configurations.Session;
import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.entities.Scale;
import br.com.enums.ScaleStatus;

import br.com.services.utilities.TableModelConverter;

import javax.swing.table.DefaultTableModel;

public class ScaleService {
    private ScaleClient client;
    private TableModelConverter converter;

    public ScaleService(ScaleClient client, TableModelConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    public boolean createScale(CreateScaleDTO dto) {
        validateCreateScale(dto);

        // Correto
        //dto.setManagerId(Session.getManagerLogged().getId());

        // Remover depois
        dto.setManagerId(1L);

        Scale created = client.createScale(dto);
        return created != null && created.getId() != null;
    }

    private void validateCreateScale(CreateScaleDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Escala não pode ser nulo");
        }
    }

    public DefaultTableModel findByStatus(ScaleStatus status) {
        return converter.createScaleModel(client.findByStatus(status));
    }
}
