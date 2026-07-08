package br.com.validators;

import br.com.dtos.requests.freelancer.CreateFreelancerDTO;
import br.com.dtos.requests.freelancer.UpdateFreelancerDTO;

public class FreelancerValidator {

    public void validateFreelancer(CreateFreelancerDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Freelancer não pode ser nulo");
        }

        if (dto.getCpf() == null || dto.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF inválido");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail inválido");
        }

        if (dto.getPhone() == null || dto.getPhone().isBlank()) {
            throw new IllegalArgumentException("Telefone inválido");
        }
    }

    public void validateFreelancer(UpdateFreelancerDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Freelancer não pode ser nulo");
        }

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail inválido");
        }

        if (dto.getPhone() == null || dto.getPhone().isBlank()) {
            throw new IllegalArgumentException("Telefone inválido");
        }
    }
}
