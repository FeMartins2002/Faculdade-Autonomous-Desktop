package br.com.dtos.requests.store;

public class DeleteStoreDTO {
    private Long managerId;
    private Long storeId;

    public DeleteStoreDTO() {

    }

    public DeleteStoreDTO(Long managerId, Long storeId) {
        this.managerId = managerId;
        this.storeId = storeId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
