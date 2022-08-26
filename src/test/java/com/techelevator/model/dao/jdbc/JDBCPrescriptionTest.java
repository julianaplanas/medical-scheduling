package com.techelevator.model.dao.jdbc;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.model.dao.PrescriptionDAO;
import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.Patient;
import com.techelevator.model.dto.Prescription;
import com.techelevator.model.dto.Review;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class JDBCPrescriptionTest extends DAOIntegrationTest  {

    private PrescriptionDAO prescriptionDAO;

    @Before
    public void setUp() {
        prescriptionDAO = new JDBCPrescription(getDataSource());
    }

    @Test
    public void createNewPrescription_should_createPrescription() {
        // arrange
        int patientId = 1;
        int doctorId = 3;
        int expected = 1;

        // act
        prescriptionDAO.createNewPrescription("Ibuprofeno", BigDecimal.valueOf(50), patientId, doctorId);

        // assert
        Map<Prescription, Patient> prescriptions = prescriptionDAO.getPrescriptionByDoctorId(doctorId);
        int actual = prescriptions.size();
        String message = "Adding one prescription should result in having one prescription assign to that doctor.";
        assertEquals(message, expected, actual);

    }

    @Test
    public void getPrescriptionByPatientId_should_returnMap_ofPrescriptionsForPatient_andDoctorInfo() {
        // arrange
        int doctorId = 3;
        int patientId = 1;
        String expected = "Derek";
        String actual = "";

        prescriptionDAO.createNewPrescription("Ibuprofeno", BigDecimal.valueOf(50), patientId, doctorId);

        // act
        Map<Prescription, Doctor> prescriptions = prescriptionDAO.getPrescriptionByPatientId(patientId);

        // assert
        for(Map.Entry<Prescription, Doctor> pr : prescriptions.entrySet()) {
            actual = pr.getValue().getFirstName();
        }

        String message = "The function should return correctly the prescriptions made to a patient.";
        assertEquals(message, expected, actual);

    }

    @Test
    public void getPrescriptionByDoctorId_should_returnMap_ofPrescriptionsOfDoctor_andPatientInfo() {
        // arrange
        int doctorId = 3;
        int patientId = 1;
        String expected = "Magali";
        String actual = "";

        prescriptionDAO.createNewPrescription("Ibuprofeno", BigDecimal.valueOf(50), patientId, doctorId);

        // act
        Map<Prescription, Patient> prescriptions = prescriptionDAO.getPrescriptionByDoctorId(doctorId);

        // assert
        for(Map.Entry<Prescription, Patient> pr : prescriptions.entrySet()) {
            actual = pr.getValue().getFirstName();
        }

        String message = "The function should return correctly the prescriptions made to a doctor.";
        assertEquals(message, expected, actual);

    }

}