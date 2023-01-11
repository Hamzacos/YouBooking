package com.example.app.Sevice;

import com.example.app.Entity.Reservation;
import com.example.app.Entity.Room;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ResrvationService {

    ResponseEntity<Object> AddReservation(Reservation reservation);
    List<Reservation> showAll();
    void deletResrvation(Long id);
    boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate);
    double calculateReservationTotal(Room room, LocalDate startDate, LocalDate endDate);
    public String currentUser();
}
