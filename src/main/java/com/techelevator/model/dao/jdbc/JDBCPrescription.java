package com.techelevator.model.dao.jdbc;

import com.techelevator.model.dao.PrescriptionDAO;
import com.techelevator.model.dto.Appointment;
import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.Patient;
import com.techelevator.model.dto.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class JDBCPrescription implements PrescriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCPrescription(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void createNewPrescription(String prescriptionName, BigDecimal cost, int patientId, int doctorId) {
        jdbcTemplate.update("INSERT INTO prescription(prescription_name, cost, patient_id, doctor_id) " +
                "VALUES (?, ?, ?, ?)", prescriptionName, cost, patientId, doctorId);
    }


    @Override
    public Map<Prescription, Doctor> getPrescriptionByPatientId(int patientId) {
        Map<Prescription, Doctor> prescriptionByPatient = new HashMap<>();

        String query = "SELECT prescription_id, p.patient_id, p.doctor_id, p.prescription_name, p.cost, d.first_name, d.last_name, d.email, d.phone_number, d.medical_specialty, d.address, d.hour_cost\n" +
        "FROM prescription as p\n" +
                "INNER JOIN doctor d ON p.doctor_id = d.doctor_id\n" +
                "WHERE p.patient_id = ?";

        SqlRowSet row = jdbcTemplate.queryForRowSet(query, patientId);

        while (row.next()) {
            Prescription prescription = new Prescription();
            Doctor doctor = new Doctor();

            int prescriptionId = row.getInt("prescription_id");
            int patientID = row.getInt("patient_id");
            int doctorId = row.getInt("doctor_id");
            String prescriptionName = row.getString("prescription_name");
            BigDecimal cost = row.getBigDecimal("cost");
            prescription.setPrescriptionId(prescriptionId);
            prescription.setPatientId(patientID);
            prescription.setDoctorId(doctorId);
            prescription.setPrescriptionName(prescriptionName);
            prescription.setCost(cost);

            doctor.setFirstName(row.getString("first_name"));
            doctor.setLastName(row.getString("last_name"));
            doctor.setEmail(row.getString("email"));
            doctor.setAddress(row.getString("address"));
            doctor.setPhoneNumber(row.getString("phone_number"));
            doctor.setMedicalSpecialty(row.getString("medical_specialty"));
            doctor.setHourCost(row.getBigDecimal("hour_cost"));

            prescriptionByPatient.put(prescription, doctor);
        }
        return prescriptionByPatient;
    }

    @Override
    public Map<Prescription, Patient> getPrescriptionByDoctorId(int doctorId) {
        Map<Prescription, Patient> prescriptionByDoctor = new HashMap<>();

        String query = "SELECT prescription_id, p.patient_id, p.doctor_id, p.prescription_name, p.cost, pa.first_name, pa.last_name, pa.email, pa.gender, pa.age " +
            "FROM prescription as p " +
            "INNER JOIN patient pa ON p.patient_id = pa.patient_id " +
            "WHERE p.doctor_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(query, doctorId);

        while (row.next()) {
            Prescription prescription = new Prescription();
            Patient patient = new Patient();

            int prescriptionId = row.getInt("prescription_id");
            int patientID = row.getInt("patient_id");
            int doctorID = row.getInt("doctor_id");
            String prescriptionName = row.getString("prescription_name");
            BigDecimal cost = row.getBigDecimal("cost");
            prescription.setPrescriptionId(prescriptionId);
            prescription.setPatientId(patientID);
            prescription.setDoctorId(doctorID);
            prescription.setPrescriptionName(prescriptionName);
            prescription.setCost(cost);

            patient.setFirstName(row.getString("first_name"));
            patient.setLastName(row.getString("last_name"));
            patient.setEmail(row.getString("email"));
            patient.setAge(row.getInt("age"));
            patient.setGender(row.getString("gender"));

            prescriptionByDoctor.put(prescription, patient);
        }
        return prescriptionByDoctor;
    }


    private Prescription mapRowToPrescription (SqlRowSet rowSet) {

        Prescription prescription = new Prescription();

        prescription.setPrescriptionId(rowSet.getInt("prescription_id"));
        prescription.setPatientId(rowSet.getInt("patient_id"));
        prescription.setDoctorId(rowSet.getInt("doctor_id"));
        prescription.setPrescriptionName(rowSet.getString("prescription_name"));
        prescription.setCost(rowSet.getBigDecimal("cost"));

        return prescription;
    }

}

