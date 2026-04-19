package br.com.entities;

import br.com.enums.PointType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Point {
    private Long id;
    private PointType pointType;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pointDateTime;

    public Point() {

    }

    public Point(Long id, PointType pointType, LocalDateTime pointDateTime) {
        this.id = id;
        this.pointType = pointType;
        this.pointDateTime = pointDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public LocalDateTime getPointDateTime() {
        return pointDateTime;
    }

    public void setPointDateTime(LocalDateTime pointDateTime) {
        this.pointDateTime = pointDateTime;
    }

    public String getFormattedPoint() {
        if (pointDateTime == null) {
            return "Sem ponto";
        }
        return pointDateTime.toLocalTime().toString();
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", pointType=" + pointType +
                ", pointDateTime=" + pointDateTime +
                '}';
    }
}
