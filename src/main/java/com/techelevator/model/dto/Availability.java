package com.techelevator.model.dto;

import java.time.LocalTime;

public class Availability {

    private int availabilityId;
    private int doctorId;
    private LocalTime startingTime;
    private LocalTime endingTime;

    public int getAvailabilityId() {
        return availabilityId;
    }
    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public LocalTime getStartingTime() {
        return startingTime;
    }
    public int getStartingTimeAsInt() {
        return startingTime.getHour();
    }
    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }
    public LocalTime getEndingTime() {
        return endingTime;
    }
    public int getEndingTimeAsInt() {
        return endingTime.getHour();
    }
    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }
}
