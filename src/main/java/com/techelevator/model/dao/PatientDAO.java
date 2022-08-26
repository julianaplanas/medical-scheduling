package com.techelevator.model.dao;

import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.Patient;

import java.util.List;

public interface PatientDAO {

    Patient getPatientById (int id);
    void update(int id, Patient patient);
    public void savePatientUser(int patientId, String firstName, String lastName, int age, String gender, String email);
}
