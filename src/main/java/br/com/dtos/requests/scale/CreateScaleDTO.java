package br.com.dtos.requests.scale;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateScaleDTO {
    private Long managerId;
    private double scaleValue;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate scaleDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private Long freelancerId;
    private Long storeId;

    public CreateScaleDTO() {

    }

    public CreateScaleDTO(double scaleValue, LocalDate scaleDate, LocalTime startTime, LocalTime endTime, Long freelancerId, Long storeId) {
        this.scaleValue = scaleValue;
        this.scaleDate = scaleDate;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalDate getScaleDate() {
        return scaleDate;
    }

    public void setScaleDate(LocalDate scaleDate) {
        this.scaleDate = scaleDate;
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

    @Override
    public String toString() {
        return "CreateScaleDTO{" +
                "managerId=" + managerId +
                ", scaleValue=" + scaleValue +
                ", scaleDate=" + scaleDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", freelancerId=" + freelancerId +
                ", storeId=" + storeId +
                '}';
    }
}