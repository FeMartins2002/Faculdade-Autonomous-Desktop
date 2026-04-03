package br.com.dtos.requests.scale;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CreateScaleDTO {
    private Long managerId;
    private double scaleValue;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime scaleDateTime;

    private Long freelancerId;
    private Long storeId;

    public CreateScaleDTO() {

    }

    public CreateScaleDTO(Long managerId, double scaleValue, LocalDateTime scaleDateTime, Long freelancerId, Long storeId) {
        this.managerId = managerId;
        this.scaleValue = scaleValue;
        this.scaleDateTime = scaleDateTime;
        this.freelancerId = freelancerId;
        this.storeId = storeId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public double getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(double scaleValue) {
        this.scaleValue = scaleValue;
    }

    public LocalDateTime getScaleDateTime() {
        return scaleDateTime;
    }

    public void setScaleDateTime(LocalDateTime scaleDateTime) {
        this.scaleDateTime = scaleDateTime;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
