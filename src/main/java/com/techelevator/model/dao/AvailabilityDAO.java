package com.techelevator.model.dao;

import com.techelevator.model.dto.Availability;

import java.time.LocalTime;

public interface AvailabilityDAO {

    Availability getAvailabilityByDoctorId(int id);
    void updateAvailability(int id, LocalTime startingTime, LocalTime endingTime);
    void saveAvailability(int doctorId, LocalTime startingTime, LocalTime endingTime);
}
