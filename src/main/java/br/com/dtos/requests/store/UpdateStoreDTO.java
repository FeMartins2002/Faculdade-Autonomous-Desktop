package br.com.dtos.requests.store;

public class UpdateStoreDTO {
    private Long managerId;
    private Long storeId;
    private String name;
    private String phone;
    private String address;

    public UpdateStoreDTO() {

    }

    public UpdateStoreDTO(Long managerId, Long storeId, String name, String phone, String address) {
        this.managerId = managerId;
        this.storeId = storeId;
        this.name = name;
        this.phone = phone;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
