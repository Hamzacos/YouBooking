package com.example.app.Sevice.Impl;

import com.example.app.Entity.Hotel;
import com.example.app.Entity.Reservation;
import com.example.app.Entity.Room;
import com.example.app.Entity.Status;
import com.example.app.Repositry.ReservationReposetry;
import com.example.app.Repositry.RoomReposetry;
import com.example.app.Repositry.UserRepository;
import com.example.app.Sevice.ResrvationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ResrvationService {

    @Autowired
    private ReservationReposetry reservationReposetry;

    @Autowired
    private RoomReposetry roomReposetry;
    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<Object> AddReservation(Reservation reservation) {
        Room room = roomReposetry.findById(reservation.getRoom().getRoomId()).orElse(null);
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }
        Reservation reservation1 = new Reservation();
        reservation1.setStatus(Status.Accepter);
        reservation1.setRoom(room);
        reservation1.setStartDate(reservation.getStartDate());
        reservation1.setEndDate(reservation.getEndDate());
        reservation1.setClient(userRepository.findByUsername(currentUser()));
        double Tp = calculateReservationTotal(reservation1.getRoom(),reservation1.getStartDate(),reservation1.getEndDate());
        reservation1.setTotalPrice(Tp);
        boolean confirmBook = isRoomAvailable(room,reservation1.getStartDate(),reservation1.getEndDate());
        if(confirmBook){
            Reservation savedReservation = reservationReposetry.save(reservation1);
            return ResponseEntity.ok(savedReservation);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @Override
    public List<Reservation> showAll() {
        return reservationReposetry.findAll();
    }

    @Override
    public void deletResrvation(Long id) {
        Optional<Reservation> hotel = reservationReposetry.findById(id);
        if (hotel.isPresent()) {
            reservationReposetry.deleteById(id);
        } else {
            System.out.println("not found");
        }
    }

    @Override
    public boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = room.getRoomResarvation();
        for (Reservation reservation : reservations) {
            if (reservation.getStartDate().isBefore(endDate) && reservation.getEndDate().isAfter(startDate)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double calculateReservationTotal(Room room, LocalDate startDate, LocalDate endDate) {
        long numberOfNights = ChronoUnit.DAYS.between(startDate, endDate);
        double roomPricePerNight = room.getPrice();
        return numberOfNights * roomPricePerNight;
   }

    @Override
    public String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
