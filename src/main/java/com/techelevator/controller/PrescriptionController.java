package com.techelevator.controller;

import com.techelevator.model.dao.*;
import com.techelevator.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalTime;
import java.util.Map;


@Controller
public class PrescriptionController {

    private PrescriptionDAO prescriptionDAO;
    private DoctorDAO doctorDAO;
    @Autowired
    public PrescriptionController(PrescriptionDAO prescriptionDAO, DoctorDAO doctorDAO, PatientDAO patientDAO) {
        this.prescriptionDAO = prescriptionDAO;
        this.doctorDAO = doctorDAO;
    }

    // Display form to get new prescription
    @RequestMapping(path="/doctor/appointments/prescription", method= RequestMethod.GET)
    public String displayNewPrescription(HttpServletRequest request) {

        return "prescription/newPrescription";
    }

    // Create new prescription
    @RequestMapping(path="/doctor/appointments/prescription/{patientId}", method=RequestMethod.POST)
    public String createAppointment(HttpSession session, @ModelAttribute Prescription prescription, HttpServletRequest request, @PathVariable int patientId) {
        User user = (User)session.getAttribute("currentUser");
        request.setAttribute("prescription", prescription);

        prescriptionDAO.createNewPrescription(prescription.getPrescriptionName(), prescription.getCost(), patientId, user.getId());

        return  "redirect:/doctor/appointments";
    }

    @RequestMapping("/patient/prescriptions")
    public String getAllPrescriptionsForPatient(ModelMap modelMap, HttpSession session) {
        User user = (User)session.getAttribute("currentUser");
        Map<Prescription, Doctor> prescription = prescriptionDAO.getPrescriptionByPatientId(user.getId());
        modelMap.put("prescription", prescription);

        return "patient/patientPrescriptions";
    }

    @RequestMapping("/doctor/prescriptions")
    public String getAllPrescriptionsForDoctor(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
        User user = (User)session.getAttribute("currentUser");
        Map<Prescription, Patient> prescription = prescriptionDAO.getPrescriptionByDoctorId(user.getId());
        modelMap.put("prescription", prescription);

        Doctor doctor = doctorDAO.getDoctorById(user.getId());

        request.setAttribute("doctor", doctor);

        return "doctor/doctorPrescriptions";
    }


}
