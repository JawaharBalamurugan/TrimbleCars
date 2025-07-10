package com.trimblecars.carleaseservice.dto;

public class CarDTO {
    private Long id;
    private String make;
    private String model;
    private int year;
    private String registrationNumber;
    private String status; // AVAILABLE, LEASED, MAINTENANCE

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getStatus() {
        return status;
    }
}
