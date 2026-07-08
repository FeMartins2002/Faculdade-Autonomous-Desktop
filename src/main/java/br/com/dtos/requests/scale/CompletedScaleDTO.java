package br.com.dtos.requests.scale;

public class CompletedScaleDTO {
    private Long managerId;
    private Long scaleId;

    public CompletedScaleDTO() {

    }

    public CompletedScaleDTO(Long managerId, Long scaleId) {
        this.managerId = managerId;
        this.scaleId = scaleId;
    }

    public Long getManagerId()
    {
        return managerId;
    }

    public void setManagerId(Long managerId)
    {
        this.managerId = managerId;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }
}
