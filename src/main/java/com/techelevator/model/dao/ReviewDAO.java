package com.techelevator.model.dao;

import com.techelevator.model.dto.Patient;
import com.techelevator.model.dto.Review;

import java.util.List;
import java.util.Map;

public interface ReviewDAO {

    Map<Review, Patient> getReviewsByDoctorId(int doctorId);

    void update(String answer, int reviewId);

    void createReview(int doctorId, int patientId, String title, String description, int rating);

    int getRatingByDoctorId(int doctorId);

}
