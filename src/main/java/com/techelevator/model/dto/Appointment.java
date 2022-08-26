package com.techelevator.model.dto;

import java.time.LocalTime;

public class Appointment {

    private int appointmentId;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private String dayOfWeek;
    private int patientId;
    private int doctorId;

    private boolean confirmed;

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public LocalTime getStartingTime() {
        return startingTime;
    }
    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }
    public LocalTime getEndingTime() {
        return endingTime;
    }
    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public void calculateDefaultEndingTime() {
        this.endingTime = startingTime.plusHours(1);
    }

}
