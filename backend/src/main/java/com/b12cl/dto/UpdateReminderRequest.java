package com.b12cl.dto;

import jakarta.validation.constraints.*;

public class UpdateReminderRequest {

    @NotBlank
    private String title;

    @NotNull
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private Double latitude;

    @NotNull
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private Double longitude;

    @NotNull
    @Min(10)
    private Integer radius;

    public UpdateReminderRequest() {}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public Double getLatitude() {return latitude;}
    public void setLatitude(Double latitude) {this.latitude = latitude;}
    public Double getLongitude() {return longitude;}
    public void setLongitude(Double longitude) {this.longitude = longitude;}
    public Integer getRadius() {return radius;}
    public void setRadius(Integer radius) { this.radius = radius;}

}
