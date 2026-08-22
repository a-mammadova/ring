package com.b12cl.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double latitude;
    private Double longitude;
    private Integer radius;
    private Boolean active;

    public Reminder(String title, Double latitude, Double longitude, Integer radius) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public Reminder() {}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public Double getLatitude() {return latitude;}
    public void setLatitude(Double latitude) {this.latitude = latitude;}
    public Double getLongitude() {return longitude;}
    public void setLongitude(Double longitude) {this.longitude = longitude;}
    public Integer getRadius() {return radius;}
    public void setRadius(Integer radius) { this.radius = radius;}
    public Boolean getActive() {return active;}
    public void setActive(Boolean active) {this.active = active;}
 }
