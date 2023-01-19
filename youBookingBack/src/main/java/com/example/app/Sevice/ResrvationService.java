package com.example.app.Sevice;

import com.example.app.DTO.ReservationDTO;
import com.example.app.DTO.RoomDTO;
import com.example.app.Entity.Reservation;
import com.example.app.Entity.Room;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ResrvationService {

    ResponseEntity<Object> AddReservation(ReservationDTO reservation);
    //List<Reservation> showAll();
    public List<ReservationDTO> showAll();
    void deletResrvation(Long id);
    public boolean isRoomAvailable(RoomDTO room, LocalDate startDate, LocalDate endDate);
    double calculateReservationTotal(RoomDTO room, LocalDate startDate, LocalDate endDate);
     String currentUser();
    boolean cancelReservation(Long reservationId);
    void updateReservation(Long id, LocalDate checkin, LocalDate checkout);

}
