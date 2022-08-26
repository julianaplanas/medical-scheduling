package com.techelevator.controller;

import com.techelevator.model.dao.AppointmentDAO;
import com.techelevator.model.dto.Appointment;
import com.techelevator.model.dto.Doctor;
import com.techelevator.model.dto.Patient;
import com.techelevator.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HomeController {

    private AppointmentDAO appointmentDAO;

    @Autowired
    public HomeController(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    // Display the home + the notification of appointments for doctors
    @RequestMapping("/")
    public String home(ModelMap modelMap, HttpSession session) {
        if(session.getAttribute("currentUser") != null) {
            User user = (User)session.getAttribute("currentUser");
            Map<Appointment, Patient> appointments = appointmentDAO.getAppointmentByDoctorId(user.getId());
            Map<Appointment, Doctor> patientApps = appointmentDAO.getAppointmentByPatientId(user.getId());
            modelMap.put("appointments", appointments);
            modelMap.put("patientApps", patientApps);
        }

        return "home";
    }
}
