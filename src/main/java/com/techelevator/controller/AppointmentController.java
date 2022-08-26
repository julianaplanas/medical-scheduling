package com.techelevator.controller;

import com.techelevator.model.dao.AppointmentDAO;
import com.techelevator.model.dao.AvailabilityDAO;
import com.techelevator.model.dao.DoctorDAO;
import com.techelevator.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.Map;

@Controller
public class AppointmentController {
    private AppointmentDAO appointmentDAO;
    private AvailabilityDAO availabilityDAO;
    private DoctorDAO doctorDAO;

    @Autowired
    public AppointmentController(AppointmentDAO appointmentDAO, AvailabilityDAO availabilityDAO, DoctorDAO doctorDAO) {
        this.appointmentDAO = appointmentDAO;
        this.availabilityDAO = availabilityDAO;
        this.doctorDAO = doctorDAO;
    }

    // Display form to get new appointment
    @RequestMapping(path="/appointment/doctor", method= RequestMethod.GET)
    public String displayNewAppointment(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        Availability availability = availabilityDAO.getAvailabilityByDoctorId(id);
        Doctor doctor = doctorDAO.getDoctorById(id);

        int startingTime = availability.getStartingTimeAsInt();
        int endingTime = availability.getEndingTimeAsInt();

        request.setAttribute("availability", availability);
        request.setAttribute("doctor", doctor);
        request.setAttribute("startTime", startingTime);
        request.setAttribute("endTime", endingTime);

        return "appointment/newAppointment";
    }

    // Create new appointment
    @RequestMapping(path="/appointment/doctor/{doctorId}", method=RequestMethod.POST)
    public String createAppointment(@RequestParam String appOption,
                               @PathVariable int doctorId,
                               HttpSession session) {
        Appointment appointment = new Appointment();
        // Get value of the radio button and split it to get both the day and the time
        String[] options = appOption.split("-");
        appointment.setDayOfWeek(options[0]);
        String hour = options[1].length() == 1 ? "0" + options[1] : options[1];
        appointment.setStartingTime(LocalTime.parse(hour + ":00"));
        appointment.calculateDefaultEndingTime();

        User user = (User)session.getAttribute("currentUser");
        LocalTime startingTime = appointment.getStartingTime();
        LocalTime endingTime = appointment.getEndingTime();
        String dayOfWeek = appointment.getDayOfWeek();

        appointmentDAO.createNewAppointment(startingTime, endingTime, dayOfWeek, user.getId(), doctorId);

        return  "redirect:/patient/appointments";
    }


    // Get all the appointments for a doctor
    @RequestMapping("/doctor/appointments")
    public String getAllAppointmentsForDoctor(ModelMap modelMap, HttpSession session) {
        User user = (User)session.getAttribute("currentUser");
        Map<Appointment, Patient> appointments = appointmentDAO.getAppointmentByDoctorId(user.getId());
        modelMap.put("appointments", appointments);

        return "doctor/doctorAppointments";
    }

    // Get all the appointments for a patient
    @RequestMapping("/patient/appointments")
    public String getAllAppointmentsForPatient(ModelMap modelMap, HttpSession session) {
        User user = (User)session.getAttribute("currentUser");
        Map<Appointment, Doctor> appointments = appointmentDAO.getAppointmentByPatientId(user.getId());
        modelMap.put("appointments", appointments);

        return "patient/patientAppointments";
    }


    @RequestMapping(path="/doctor/appointments", method=RequestMethod.POST)
    public String cancelAppointment(HttpServletRequest request,
                                    HttpSession session) {
        int id = Integer.parseInt(request.getParameter("id"));

        User user = (User)session.getAttribute("currentUser");
        appointmentDAO.cancelAppointment( id);

        return  "redirect:/doctor/appointments";
    }

    @RequestMapping(path="/patient/appointments", method=RequestMethod.POST)
    public String cancelAppointmentByPatient(HttpServletRequest request,
                                    HttpSession session) {
        int id = Integer.parseInt(request.getParameter("id"));

        User user = (User)session.getAttribute("currentUser");
        appointmentDAO.cancelAppointment( id);

        return  "redirect:/patient/appointments";
    }

}
