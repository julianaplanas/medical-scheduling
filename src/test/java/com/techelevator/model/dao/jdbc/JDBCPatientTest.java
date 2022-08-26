package com.techelevator.model.dao.jdbc;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.model.dao.PatientDAO;
import com.techelevator.model.dto.Appointment;
import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.Patient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class JDBCPatientTest extends DAOIntegrationTest {

    private PatientDAO patientDAO;

    @Before
    public void setUp() {
        patientDAO = new JDBCPatient(getDataSource());
    }


    @Test
    public void CreateNewPatient_should_createPatient() {
        // arrange
        int patientId = 5;
        String firstName = "John";
        String lastName = "Doe";
        int age = 50;
        String gender= "Male";
        String email = "johnDoe@gmail.com";
        int expected = 5 ;

        // act

        patientDAO.savePatientUser( patientId, firstName, lastName,  age,  gender,  email);

        // assert
        Patient patient1  = patientDAO.getPatientById(5);
        int actual = patient1.getPatientId();
        String message = "Adding a patient with ID 5 should result in having a doctor with ID 5";
        assertEquals(message, expected, actual);

    }

    @Test
    public void getPatientById_should_returnAPatient() {
        // arrange
        int patientId = 1;
        String expected = "Magali";
        String actual = "";

        // act
        Patient patient = patientDAO.getPatientById(patientId);

        // assert
        actual = patient.getFirstName();

        String message = "The function should return correctly the Patient searched.";
        assertEquals(message, expected, actual);

    }

    @Test
    public void UpdatePatientEmail_should_UpdatePatientsEmail() {
        // arrange
        int patientId = 1;
        Patient patient =  patientDAO.getPatientById(1);
        patient.setEmail("johnDoe2@gmail.com");
        String expected = "johnDoe2@gmail.com";
        // act

        patientDAO.update( patientId, patient );

        // assert
        Patient patient1  = patientDAO.getPatientById(1);
        String actual = patient1.getEmail();
        String message = "Updating patient personal info should result having an email : johnDoe2@gmail.com";
        assertEquals(message, expected, actual);

    }





}