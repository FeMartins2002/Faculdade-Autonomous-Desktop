package br.com.services;

import br.com.clients.ScaleClient;
import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.dtos.responses.scale.ScaleResponseDTO;
import br.com.enums.ScaleStatus;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class ScaleService {
    private ScaleClient client;

    public ScaleService(ScaleClient client) {
        this.client = client;
    }

    public boolean createScale(CreateScaleDTO dto) throws JsonProcessingException {
        var response = client.createScale(dto);

        return response.statusCode() == 201;
    }

    public List<ScaleResponseDTO> findByStatus(ScaleStatus status) throws JsonProcessingException {
        return client.findByStatus(status);
    }
}
