package br.com.entities;

import br.com.enums.ScaleStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Scale {
    private Long id;
    private ScaleStatus scaleStatus;
    private double scaleValue;
    private LocalDate scaleDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String scaleObservation;
    private String freelancerName;
    private String managerName;
    private String storeName;
    private List<Point> points;

    public Scale() {

    }

    public Scale(Long id, ScaleStatus scaleStatus, double scaleValue, LocalDate scaleDate, LocalTime startTime, LocalTime endTime, String scaleObservation, String freelancerName, String managerName, String storeName, List<Point> points) {
        this.id = id;
        this.scaleStatus = scaleStatus;
        this.scaleValue = scaleValue;
        this.scaleDate = scaleDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scaleObservation = scaleObservation;
        this.freelancerName = freelancerName;
        this.managerName = managerName;
        this.storeName = storeName;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScaleStatus getScaleStatus() {
        return scaleStatus;
    }

    public void setScaleStatus(ScaleStatus scaleStatus) {
        this.scaleStatus = scaleStatus;
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

    public String getScaleObservation() {
        return scaleObservation;
    }

    public void setScaleObservation(String scaleObservation) {
        this.scaleObservation = scaleObservation;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Scale{" +
                "id=" + id +
                ", scaleStatus=" + scaleStatus +
                ", scaleValue=" + scaleValue +
                ", scaleDate=" + scaleDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", scaleObservation='" + scaleObservation + '\'' +
                ", freelancerName='" + freelancerName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", points=" + points +
                '}';
    }
}
