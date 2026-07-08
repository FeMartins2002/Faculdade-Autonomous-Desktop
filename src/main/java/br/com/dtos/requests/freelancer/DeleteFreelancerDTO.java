package br.com.dtos.requests.freelancer;

public class DeleteFreelancerDTO {
    private Long managerId;
    private Long freelancerId;

    public DeleteFreelancerDTO() {

    }

    public DeleteFreelancerDTO(Long managerId, Long freelancerId) {
        this.managerId = managerId;
        this.freelancerId = freelancerId;
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
}
