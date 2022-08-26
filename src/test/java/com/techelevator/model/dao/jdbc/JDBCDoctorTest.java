package com.techelevator.model.dao.jdbc;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.model.dao.DoctorDAO;
import com.techelevator.model.dto.Appointment;
import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.Patient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Map;

import static org.junit.Assert.*;

public class JDBCDoctorTest extends DAOIntegrationTest {

    private DoctorDAO doctorDAO;

    @Before
    public void setUp() {
        doctorDAO = new JDBCDoctor(getDataSource());
    }


    @Test
    public void CreateNewDoctor_should_createDoctor() {
        // arrange
        int doctorId = 4;
        String firstName = "John";
        String lastName = "Doe";
        String email = "johnDoe@gmail.com";
        BigDecimal hourCost = BigDecimal.valueOf(200);
        String address = "Av Important 566";
        String phoneNumber = "08005554545";
        String medicalSpecialty = "Cardiologyst";
        int expected = 4 ;

        // act
        doctorDAO.saveDoctorUser( doctorId,  firstName,  lastName,  email,  hourCost,  address,  phoneNumber,  medicalSpecialty);

        // assert
        Doctor doctor1  = doctorDAO.getDoctorById(4);
        int actual = doctor1.getDoctorId();
        String message = "Adding a doctor with ID 4 should result in having a doctor with ID 4";
        assertEquals(message, expected, actual);

    }

    @Test
    public void UpdateDoctorAddress_should_updateDoctorAddress() {
        // arrange
        int doctorId = 2;
        String email = "johnDoe@gmail.com";
        BigDecimal hourCost = BigDecimal.valueOf(250);
        String address = "Av Important 566";
        String phoneNumber = "08005554545";
        String medicalSpecialty = "Cardiologyst";
        String expected = "Av Important 566";

        // act
        doctorDAO.update(  doctorId,  email,  address,  phoneNumber,  medicalSpecialty, hourCost);
        //appointmentDAO.createNewAppointment(startingTime, endingTime, dayOfWeek, patientId, doctorId);

        // assert
        Doctor doctor1  = doctorDAO.getDoctorById(2);
        String actual = doctor1.getAddress();
        String message = "Updating doctor with doctorId2 address info should result in new Address been Av Important 566  ";
        assertEquals(message, expected, actual);

    }

    @Test
    public void UpdateDoctorPhoneNumber_should_updateDoctorPhoneNumber() {
        // arrange
        int doctorId = 2;
        String email = "johnDoe@gmail.com";
        BigDecimal hourCost = BigDecimal.valueOf(200);
        String address = "Av Important 566";
        String phoneNumber = "0800555666";
        String medicalSpecialty = "Cardiologyst";
        String expected = "0800555666";

        // act
        doctorDAO.update(  doctorId,  email,  address,  phoneNumber,  medicalSpecialty, hourCost);
        //appointmentDAO.createNewAppointment(startingTime, endingTime, dayOfWeek, patientId, doctorId);

        // assert
        Doctor doctor1  = doctorDAO.getDoctorById(2);
        String actual = doctor1.getPhoneNumber();
        String message = "Updating doctor with doctorId2 personal info should result in new phone number  been 0800555666";
        assertEquals(message, expected, actual);

    }

    @Test
    public void UpdateDoctorMedicalSpecialty_should_updateDoctorMedicalSpecialty() {
        // arrange
        int doctorId = 2;
        String email = "johnDoe@gmail.com";
        BigDecimal hourCost = BigDecimal.valueOf(200);
        String address = "Av Important 566";
        String phoneNumber = "0800555666";
        String medicalSpecialty = "Dermatology";
        String expected = "Dermatology";

        // act
        doctorDAO.update(  doctorId,  email,  address,  phoneNumber,  medicalSpecialty, hourCost);
        //appointmentDAO.createNewAppointment(startingTime, endingTime, dayOfWeek, patientId, doctorId);

        // assert
        Doctor doctor1  = doctorDAO.getDoctorById(2);
        String actual = doctor1.getMedicalSpecialty();
        String message = "Updating doctor with doctorId2 personal info should result in new medical specialty  been Dermatology";
        assertEquals(message, expected, actual);

    }
}