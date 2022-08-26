package com.techelevator.controller;

import com.techelevator.model.dao.DoctorDAO;
import com.techelevator.model.dao.ReviewDAO;
import com.techelevator.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class ReviewController {
    private ReviewDAO reviewDAO;
    private DoctorDAO doctorDAO;

    @Autowired
    public ReviewController(ReviewDAO reviewDAO, DoctorDAO doctorDAO) {
        this.reviewDAO = reviewDAO;
        this.doctorDAO = doctorDAO;
    }

    // Show all reviews for a doctor
    @RequestMapping("/doctor/reviews")
    public String displayReviewsByDoctorId(HttpServletRequest request, HttpSession session) {
        User user = (User)session.getAttribute("currentUser");
        int id = user.getId();

        Map<Review, Patient> reviews = reviewDAO.getReviewsByDoctorId(id);

        request.setAttribute("reviews", reviews);

        return "review/displayReviews";
    }

    // Show form to send a new review
    @RequestMapping("/doctor-list/public-profile/review")
    public String displayNewReviewForm(HttpServletRequest request, ModelMap modelMap) {
        int id = Integer.parseInt(request.getParameter("id"));

        Review review = new Review();
        modelMap.put("review", review);

        Doctor doctor = doctorDAO.getDoctorById(id);
        request.setAttribute("doctor", doctor);

        return "review/newReview";
    }

    // Send a new review
    @RequestMapping(path="/doctor-list/public-profile/review", method= RequestMethod.POST)
    public String postNewReview(@Valid @ModelAttribute Review review,
                                BindingResult result,
                                HttpServletRequest request,
                                HttpSession session) {
        if(result.hasErrors()) {
            return "review/newReview";
        }
        int doctorId = Integer.parseInt(request.getParameter("id"));
        User user = (User)session.getAttribute("currentUser");

        reviewDAO.createReview(doctorId, user.getId(), review.getTitle(), review.getDescription(), review.getRating());

        return  "redirect:/doctor-list";
    }

}
