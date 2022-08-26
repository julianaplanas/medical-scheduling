package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.DoctorDAO;
import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.SpecialtyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCDoctor implements DoctorDAO {

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_PRODUCTS_SQL = "SELECT doctor_id, first_name, last_name, email, address, phone_number, medical_specialty, hour_cost FROM doctor WHERE medical_specialty = ?";

    @Autowired
    public JDBCDoctor(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT doctor_id, first_name, last_name, email, address, phone_number, medical_specialty, hour_cost " +
                "FROM doctor ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            doctors.add(mapRowToDoctor(results));
        }

        return doctors;
    }

    @Override
    public List<Doctor> getAll(SpecialtyFilter filter) {
        if (filter.getSpecialty() == null || filter.getSpecialty().equals("All")) {
            return getAll();
        }

        String sql = SELECT_PRODUCTS_SQL;

        List<String> filters = new ArrayList<>();
        List<Object> queryParameters = new ArrayList<>();

        if (filter.getSpecialty() != null) {
            queryParameters.add(filter.getSpecialty());
        }

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, queryParameters.toArray());

        return mapRowSetToDoctors(results);
    }

    @Override
    public Doctor getDoctorById(int id) {
        String query = "SELECT  doctor_id, first_name, last_name, email, address, phone_number, medical_specialty, hour_cost " +
                "FROM doctor\n" +
                "WHERE doctor_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(query, id);

        if (row.next()) {
            return mapRowToDoctor(row);
        }

        return null;

    }

    @Override
    public void update(int id, String email, String address, String phoneNumber, String specialty, BigDecimal hourCost) {
        String sql = "UPDATE doctor\n" +
                "SET email = ?\n" +
                "    , address = ?\n" +
                "    , phone_number = ?\n" +
                "    , medical_specialty = ?\n" +
                "    , hour_cost = ?\n" +
                "WHERE doctor_id = ?;";

        jdbcTemplate.update(sql, email, address, phoneNumber, specialty, hourCost, id);

    }

    @Override
    public void saveDoctorUser(int doctorId, String firstName, String lastName, String email, BigDecimal hourCost, String address, String phoneNumber, String medicalSpecialty) {
        jdbcTemplate.update("INSERT INTO doctor(doctor_id,  first_name, last_name, email, hour_cost, address, phone_number, medical_specialty ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", doctorId, firstName, lastName, email, hourCost, address, phoneNumber, medicalSpecialty);
    }

    @Override
    public List<Doctor> getDoctor(HttpServletRequest request) {
        SpecialtyFilter filter = getFilters(request);
        List<Doctor> doctors = getAll(filter);

        return doctors;
    }

    @Override
    public SpecialtyFilter getFilters(HttpServletRequest request) {
        SpecialtyFilter filter = new SpecialtyFilter();

        if (request.getParameter("specialty") != null) {
            String medicalSpecialty = String.valueOf(request.getParameter("specialty"));
            filter.setSpecialty(medicalSpecialty);
        }

        return filter;
    }

    private Doctor mapRowToDoctor(SqlRowSet row) {
        Doctor doctor = new Doctor();

        int id = row.getInt("doctor_id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String email = row.getString("email");
        String address = row.getString("address");
        String phoneNumber = row.getString("phone_number");
        String medicalSpecialty = row.getString("medical_specialty");
        BigDecimal hourCost = row.getBigDecimal("hour_cost");

        doctor.setDoctorId(id);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setEmail(email);
        doctor.setAddress(address);
        doctor.setPhoneNumber(phoneNumber);
        doctor.setMedicalSpecialty(medicalSpecialty);
        doctor.setHourCost(hourCost);

        return doctor;
    }

    private List<Doctor> mapRowSetToDoctors(SqlRowSet results) {
        List<Doctor> doctors = new ArrayList<>();
        while (results.next()) {
            doctors.add(mapRowToDoctor(results));
        }
        return doctors;
    }

}
