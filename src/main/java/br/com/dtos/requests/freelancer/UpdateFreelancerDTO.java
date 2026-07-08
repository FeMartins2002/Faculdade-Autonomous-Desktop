package br.com.dtos.requests.freelancer;

public class UpdateFreelancerDTO {
    private Long managerId;
    private Long freelancerId;
    private String name;
    private String email;
    private String phone;

    public UpdateFreelancerDTO() {

    }

    public UpdateFreelancerDTO(Long managerId, Long freelancerId, String name, String phone, String email) {
        this.managerId = managerId;
        this.freelancerId = freelancerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
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
}
