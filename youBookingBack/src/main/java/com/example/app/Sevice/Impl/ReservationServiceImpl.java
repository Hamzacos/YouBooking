package com.example.app.Sevice.Impl;

import com.example.app.DTO.ReservationDTO;
import com.example.app.DTO.RoomDTO;
import com.example.app.Entity.Hotel;
import com.example.app.Entity.Reservation;
import com.example.app.Entity.Room;
import com.example.app.Entity.Status;
import com.example.app.Repositry.ReservationReposetry;
import com.example.app.Repositry.RoomReposetry;
import com.example.app.Repositry.UserRepository;
import com.example.app.Sevice.ResrvationService;
import org.modelmapper.ModelMapper;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReservationServiceImpl implements ResrvationService {

    @Autowired
    private ReservationReposetry reservationRepository;

    @Autowired
    private RoomReposetry roomReposetry;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<Object> AddReservation(ReservationDTO reservation) {
        RoomDTO room = reservation.getRoom();
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }
        reservation.setStatus(Status.Accepter);
        reservation.setStartDate(reservation.getStartDate());
        reservation.setEndDate(reservation.getEndDate());
        reservation.setClient(userRepository.findByUsername(currentUser()));
        reservation.setTotalPrice(calculateReservationTotal(room,reservation.getStartDate(),reservation.getEndDate()));
        boolean confirmBook = isRoomAvailable(room, reservation.getStartDate(), reservation.getEndDate());
        if(confirmBook){
            Reservation reservationLast = reservation.toEntity();
            Reservation savedReservation = reservationRepository.save(reservationLast);
            return ResponseEntity.ok(savedReservation);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



    @Override
    public List<ReservationDTO> showAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> getMyReservations() {
        List<Reservation> reservations = reservationRepository.findByClientUsername(currentUser());
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public void deletResrvation(Long id) {
        Optional<Reservation> hotel = reservationRepository.findById(id);
        if (hotel.isPresent()) {
            reservationRepository.deleteById(id);
        } else {
            System.out.println("not found");
        }
    }

    @Override
    public boolean isRoomAvailable(RoomDTO room, LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationRepository.findByRoomIdAndStartDateBetween(room.getRoomId(),startDate,endDate);
        return reservations.isEmpty();
       /* for (ReservationDTO reservation : roomReservations) {
            if (reservation.getStartDate().isBefore(endDate) && reservation.getEndDate().isAfter(startDate)) {
                return false;
            }
        }
        return true;*/
    }

    @Override
    public double calculateReservationTotal(RoomDTO room, LocalDate startDate, LocalDate endDate) {
        long numberOfNights = ChronoUnit.DAYS.between(startDate, endDate);
        double roomPricePerNight = room.getPrice();
        return numberOfNights * roomPricePerNight;
   }

    @Override
    public String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public boolean cancelReservation(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()) {
            reservation.get().setStatus(Status.Annuler);
            reservationRepository.save(reservation.get());
            return true;
        }
        return false;
    }


   /* @Override
    public Reservation updateReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isPresent()) {
            Reservation currentReservation = reservation.get();
            currentReservation.setStartDate(updatedReservationDTO.getStartDate());
            currentReservation.setEndDate(updatedReservationDTO.getEndDate());
            modelMapper.map(updatedReservationDTO, currentReservation);
            return reservationRepository.save(currentReservation);
        } else {
            throw new RuntimeException("No reservation found with id: " + id);
        }
    }*/

    /*@Override
    public ResponseEntity<Object> updateReservation(Long reservationId, LocalDate startDate, LocalDate endDate) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (!reservation.isPresent()) {
            return null;
        }
        reservation.get().setStartDate(reservationDTO.getStartDate());
        reservation.get().setEndDate(reservationDTO.getEndDate());;
        Reservation savedReservation = reservationRepository.save(reservation.get());
        return ResponseEntity.ok(savedReservation);
        *//*ReservationDTO updatedReservationDTO = new ReservationDTO(updatedReservation);
        return  ResponseEntity.ok(updatedReservationDTO);*//*
    }*/

    @Override
    public void updateReservation(Long id, LocalDate checkin, LocalDate checkout) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setStartDate(checkin);
        reservation.setEndDate(checkout);
        reservationRepository.save(reservation);
    }



}
