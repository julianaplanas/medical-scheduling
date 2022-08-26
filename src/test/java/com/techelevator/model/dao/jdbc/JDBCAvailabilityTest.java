package com.techelevator.model.dao.jdbc;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.model.dao.AvailabilityDAO;
import com.techelevator.model.dto.Appointment;
import com.techelevator.model.dto.Availability;
import com.techelevator.model.dto.Patient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Map;

import static org.junit.Assert.*;

public class JDBCAvailabilityTest extends DAOIntegrationTest  {

    private AvailabilityDAO availabilityDAO;

    @Before
    public void setUp() {
        availabilityDAO = new JDBCAvailability(getDataSource());
    }

    @Test
    public void getAvailabilityByDoctorId_should_returnAvailability_forDoctor() {
        // arrange
        int doctorId = 2;
        LocalTime expected = LocalTime.parse("17:00");
        LocalTime actual = LocalTime.parse("19:00");

        // act
        Availability availability = availabilityDAO.getAvailabilityByDoctorId(doctorId);

        // assert
        actual = availability.getEndingTime();

        String message = "The function should return correctly the availability of a doctor";
        assertEquals(message, expected, actual);

    }

    @Test
    public void updateAvailability_should_changeAvailability() {
        // arrange
        LocalTime expected = LocalTime.parse("16:00");

        // act
        availabilityDAO.updateAvailability(2, LocalTime.parse("09:00"), LocalTime.parse("16:00"));

        // assert
        Availability availability =  availabilityDAO.getAvailabilityByDoctorId(2);
        LocalTime actual = availability.getEndingTime();
        String message = "Updating an availability should change the availability for that doctor.";
        assertEquals(message, expected, actual);

    }

}