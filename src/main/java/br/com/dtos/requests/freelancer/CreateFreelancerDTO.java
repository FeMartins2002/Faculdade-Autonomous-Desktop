package br.com.dtos.requests.freelancer;

public class CreateFreelancerDTO {
    private Long managerId;
    private String cpf;
    private String name;
    private String email;
    private String phone;
    private String address;

    public CreateFreelancerDTO() {

    }

    public CreateFreelancerDTO(Long managerId, String cpf, String name, String email, String phone, String address) {
        this.managerId = managerId;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
