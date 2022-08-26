package com.techelevator.model.dao.jdbc;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.model.dao.ReviewDAO;
import com.techelevator.model.dto.Patient;
import com.techelevator.model.dto.Review;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class JDBCReviewTest extends DAOIntegrationTest {

    private ReviewDAO reviewDAO;

    @Before
    public void setUp() {
        reviewDAO = new JDBCReview(getDataSource());
    }

    @Test
    public void getReviewsByDoctorId_should_returnMap_ofReviewsForDoctor_andPatientsInfo() {
        // arrange
        int doctorId = 2;
        String expected = "Magali";
        String actual = "";

        // act
        Map<Review, Patient> reviews = reviewDAO.getReviewsByDoctorId(doctorId);

        // assert
        for(Map.Entry<Review, Patient> app : reviews.entrySet()) {
            actual = app.getValue().getFirstName();
        }

        String message = "The function should return correctly the reviews made to a doctor";
        assertEquals(message, expected, actual);

    }

    @Test
    public void createReview_should_createReview() {
        // arrange
        int patientId = 1;
        int doctorId = 3;
        int expected = 1;

        // act
        reviewDAO.createReview(doctorId, patientId, "Yey!", "So good!", 5);

        // assert
        Map<Review, Patient> reviews = reviewDAO.getReviewsByDoctorId(doctorId);
        int actual = reviews.size();
        String message = "Adding one review should result in having one review assign to that doctor.";
        assertEquals(message, expected, actual);

    }

    @Test
    public void getRatingByDoctorId_should_returnRating() {
        // arrange
        int doctorId = 3;
        int expected = 4;
        reviewDAO.createReview(doctorId, 1, "Yey!", "So good!", 5);
        reviewDAO.createReview(doctorId, 1, "Yey!", "So good!", 3);

        // act
        int actual = reviewDAO.getRatingByDoctorId(doctorId);

        // assert
        String message = "The function should return the general rating of the doctor";
        assertEquals(message, expected, actual);

    }

}