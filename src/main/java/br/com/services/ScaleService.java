package br.com.services;

import br.com.clients.ScaleClient;
import br.com.configurations.Session;
import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.dtos.responses.FreelancerOption;
import br.com.dtos.responses.StoreOption;
import br.com.entities.Scale;
import br.com.enums.ScaleStatus;

import br.com.services.utilities.TableModelConverter;

import javax.swing.table.DefaultTableModel;
import java.util.List;

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

    public DefaultTableModel findByStatus(ScaleStatus status) {
        return converter.createScaleModel(client.findByStatus(status));
    }

    public List<FreelancerOption> getFreelancersOptions() {
        return client.findOptionsFreelancers();
    }

    public  List<StoreOption> getStoresOptions() {
        return client.findOptionsStores();
    }

    private void validateCreateScale(CreateScaleDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Escala não pode ser nulo");
        }

        if(dto.getScaleDate() == null) {
            throw new IllegalArgumentException("Data da escala não pode ser nulo");
        }

        if(dto.getStartTime() == null) {
            throw new IllegalArgumentException("Entrada da escala não pode ser nulo");
        }

        if(dto.getEndTime() == null) {
            throw new IllegalArgumentException("Saída da escala não pode ser nulo");
        }

        if(dto.getFreelancerId() == null) {
            throw new IllegalArgumentException("Freelancer não pode ser nulo");
        }

        if(dto.getStoreId() == null) {
            throw new IllegalArgumentException("Loja não pode ser nulo");
        }

        if(dto.getScaleValue() == 0) {
            throw new IllegalArgumentException("Valor da escala não pode ser nulo");
        }
    }
}
