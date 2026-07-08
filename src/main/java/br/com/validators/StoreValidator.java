package br.com.validators;

import br.com.dtos.requests.store.CreateStoreDTO;
import br.com.dtos.requests.store.UpdateStoreDTO;

public class StoreValidator {

    public void validateStore(CreateStoreDTO dto) {

        if(dto == null) {
            throw new IllegalArgumentException("Loja não pode ser nulo");
        }

        if(dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Nome da loja é obrigatório");
        }

        if(dto.getPhone() == null || dto.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Telefone da loja é obrigatório");
        }

        if(dto.getAddress() == null || dto.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Endereço da loja é obrigatório");
        }
    }

    public void validateStore(UpdateStoreDTO dto) {

        if(dto == null) {
            throw new IllegalArgumentException("Loja não pode ser nulo");
        }

        if(dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Nome da loja é obrigatório");
        }

        if(dto.getPhone() == null || dto.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Telefone da loja é obrigatório");
        }

        if(dto.getAddress() == null || dto.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Endereço da loja é obrigatório");
        }
    }
}
