package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.ReviewDAO;
import com.techelevator.model.dto.Patient;
import com.techelevator.model.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class JDBCReview implements ReviewDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCReview(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<Review, Patient> getReviewsByDoctorId(int doctorId) {
        Map<Review, Patient> reviewsByDoctor = new HashMap<>();

        String query = "SELECT review_id, doctor_id, r.patient_id, title, description, rating, answer, p.first_name, p.last_name\n" +
                "FROM review as r\n" +
                "INNER JOIN patient p ON p.patient_id = r.patient_id\n" +
                "WHERE doctor_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(query, doctorId);

        while (row.next()) {
            Review review = new Review();
            Patient patient = new Patient();

            review.setReviewId(row.getInt("review_id"));
            review.setDoctorId(row.getInt("doctor_id"));
            review.setPatientId(row.getInt("patient_id"));
            review.setTitle(row.getString("title"));
            review.setDescription(row.getString("description"));
            review.setRating(row.getInt("rating"));

            patient.setFirstName(row.getString("first_name"));
            patient.setLastName(row.getString("last_name"));

            reviewsByDoctor.put(review, patient);
        }

        return reviewsByDoctor;
    }

    @Override
    public int getRatingByDoctorId(int doctorId) {
        Map<Review, Patient> reviews = getReviewsByDoctorId(doctorId);
        int rating = 0;

        for(Map.Entry<Review, Patient> review : reviews.entrySet()) {
            rating += review.getKey().getRating();
        }

        if (rating > 0) {
            rating = rating / reviews.size();
        }

        return rating;
    }

    @Override
    public void createReview(int doctorId, int patientId, String title, String description, int rating) {
        jdbcTemplate.update("INSERT INTO review(doctor_id, patient_id, title, description, rating) " +
                "VALUES (?, ?, ?, ?, ?)", doctorId, patientId, title, description, rating);
    }

    @Override
    public void update(String answer, int reviewId) {
        String sql = "UPDATE review\n" +
                "SET answer = ?\n" +
                "WHERE review_id = ?;";

        jdbcTemplate.update(sql, answer, reviewId);

    }

    private Review mapRowToReview(SqlRowSet row) {
        Review review = new Review();

        int id = row.getInt("review_id");
        int doctorId = row.getInt("doctor_id");
        int patientId = row.getInt("patient_id");
        String title = row.getString("title");
        String description = row.getString("description");
        int rating = row.getInt("rating");
        String answer = row.getString("answer");

        review.setReviewId(id);
        review.setDoctorId(doctorId);
        review.setPatientId(patientId);
        review.setTitle(title);
        review.setDescription(description);
        review.setRating(rating);
        review.setAnswer(answer);

        return review;
    }

}
