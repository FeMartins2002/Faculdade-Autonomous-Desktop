package br.com.validators;

import br.com.dtos.requests.scale.CreateScaleDTO;
import br.com.dtos.requests.scale.UpdateScaleDTO;

public class ScaleValidator {

    public void validateCreateScale(CreateScaleDTO dto) {

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

    public void validateUpdateScale(UpdateScaleDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Escala não pode ser nulo");
        }

        if(dto.getStartTime() == null) {
            throw new IllegalArgumentException("Entrada da escala não pode ser nulo");
        }

        if(dto.getEndTime() == null) {
            throw new IllegalArgumentException("Saída da escala não pode ser nulo");
        }

        if(dto.getStoreId() == null) {
            throw new IllegalArgumentException("Loja não pode ser nulo");
        }

        if(dto.getScaleValue() == 0) {
            throw new IllegalArgumentException("Valor da escala não pode ser nulo");
        }
    }
}
