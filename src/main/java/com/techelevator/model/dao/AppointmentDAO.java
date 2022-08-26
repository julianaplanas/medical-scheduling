package com.techelevator.model.dao;

import com.techelevator.model.dto.Appointment;
import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.Patient;

import java.time.LocalTime;
import java.util.Map;

public interface AppointmentDAO {

    void createNewAppointment(LocalTime startingTime, LocalTime endingTime, String dayOfWeek, int patientId, int doctorId);
    Map<Appointment, Patient> getAppointmentByDoctorId(int doctorId);
    Map<Appointment, Doctor> getAppointmentByPatientId(int patientId);

    void cancelAppointment(int id);
}
