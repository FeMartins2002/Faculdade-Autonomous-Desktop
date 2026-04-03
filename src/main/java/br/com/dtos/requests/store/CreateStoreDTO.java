package br.com.dtos.requests.store;

public class CreateStoreDTO {
    private Long managerId;
    private String name;
    private String phone;
    private String address;

    public CreateStoreDTO() {

    }

    public CreateStoreDTO(Long managerId, String name, String phone, String address) {
        this.managerId = managerId;
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
