package br.com.dtos.requests.scale;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class UpdateScaleDTO {
    private Long managerId;

    private Long scaleId;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private Long storeId;
    private double scaleValue;

    public UpdateScaleDTO() {

    }

    public UpdateScaleDTO(Long managerId, Long scaleId, LocalTime startTime, LocalTime endTime, Long storeId, double scaleValue) {
        this.managerId = managerId;
        this.scaleId = scaleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.storeId = storeId;
        this.scaleValue = scaleValue;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public double getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(double scaleValue) {
        this.scaleValue = scaleValue;
    }
}
