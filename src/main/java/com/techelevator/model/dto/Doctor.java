package com.techelevator.model.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private int doctorId;
    private String userName;
    @NotBlank(message = "Your First Name is required")
    private String firstName;
    @NotBlank(message = "Your Last Name is required")
    private String lastName;
    @Email
    @NotBlank(message = "Your Email is required")
    private String email;
    @NotBlank(message = "Your Address is required")
    private String address;
    @NotBlank(message = "Your Phone Number is required")
    @Pattern.List({
            @Pattern(regexp=".*\\d.*", message="Must be only numbers")
    })
    private String phoneNumber;
    @NotBlank(message = "Your Medical Specialty is required")
    private String medicalSpecialty;
    @NotNull(message = "Your Hourly Cost is required")
    private BigDecimal hourCost;

    public Doctor() {
    }

    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getMedicalSpecialty() {
        return medicalSpecialty;
    }
    public void setMedicalSpecialty(String medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }
    public BigDecimal getHourCost() {
        return hourCost;
    }
    public void setHourCost(BigDecimal hourCost) {
        this.hourCost = hourCost;
    }
    public static List<String> getSpecialtyList() {
        List<String> medicalSpecialty = new ArrayList<>();
        medicalSpecialty.add("All");
        medicalSpecialty.add("Allergy and immunology");
        medicalSpecialty.add("Anesthesiology");
        medicalSpecialty.add("Cardiology");
        medicalSpecialty.add("Dermatology");
        medicalSpecialty.add("Endocrinology");
        medicalSpecialty.add("Gastroenterology");
        medicalSpecialty.add("Geriatric Medicine");
        medicalSpecialty.add("Gynecology");
        medicalSpecialty.add("Sports Medicine");
        medicalSpecialty.add("Neurology");
        medicalSpecialty.add("Ophthalmology");
        medicalSpecialty.add("Pediatrician");
        medicalSpecialty.add("Psychiatry");
        return medicalSpecialty;
    }

}
