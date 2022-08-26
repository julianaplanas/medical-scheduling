package com.techelevator.model.dao;

import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.SpecialtyFilter;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public interface DoctorDAO {

    List<Doctor> getAll();
    Doctor getDoctorById(int id);
    void update(int id, String email, String address, String phoneNumber, String specialty, BigDecimal hourCost);
    void saveDoctorUser(int doctorId, String firstName, String lastName, String email, BigDecimal hourCost, String address, String phoneNumber, String medicalSpecialty);
    List<Doctor> getAll(SpecialtyFilter filter);
    List<Doctor> getDoctor(HttpServletRequest request);
    SpecialtyFilter getFilters(HttpServletRequest request);


}
