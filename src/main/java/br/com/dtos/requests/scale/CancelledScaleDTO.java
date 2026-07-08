package br.com.dtos.requests.scale;

public class CancelledScaleDTO {
    private Long managerId;
    private Long scaleId;
    private String observation;

    public CancelledScaleDTO() {

    }

    public CancelledScaleDTO(Long managerId, Long scaleId, String observation) {
        this.managerId = managerId;
        this.scaleId = scaleId;
        this.observation = observation;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getScaleId() {
        return scaleId;
    }

    public void setScaleId(Long scaleId) {
        this.scaleId = scaleId;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
