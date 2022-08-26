package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.PatientDAO;
import com.techelevator.model.dto.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCPatient implements PatientDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JDBCPatient(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Patient getPatientById(int id) {
        String query = "SELECT  patient_id, first_name, last_name, email, age, gender " +
                "FROM patient\n" +
                "WHERE patient_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(query, id);

        if(row.next())
        {
            return mapRowToPatient(row);
        }

        return null;
    }

    @Override
    public void update(int id, Patient patient) {
        String sql = "UPDATE patient\n" +
                "SET email = ?\n" +
                "    , gender = ?\n" +
                "    , age = ?\n" +

                "WHERE patient_id = ?;";

        jdbcTemplate.update(sql, patient.getEmail(), patient.getGender(), patient.getAge(), id);

    }

    @Override
    public void savePatientUser(int patientId, String firstName, String lastName, int age, String gender, String email) {
        jdbcTemplate.update("INSERT INTO patient(patient_id, first_name, last_name, age, gender, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)", patientId, firstName, lastName, age, gender, email);
    }

    private Patient mapRowToPatient (SqlRowSet rowSet) {

        Patient patient = new Patient();

        patient.setPatientId(rowSet.getInt("patient_id"));
        patient.setFirstName(rowSet.getString("first_name"));
        patient.setLastName(rowSet.getString("last_name"));
        patient.setAge(rowSet.getInt("age"));
        patient.setGender(rowSet.getString("gender"));
        patient.setEmail(rowSet.getString("email"));

        return patient;
    }
}
