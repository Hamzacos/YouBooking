package com.example.app.Entity;


import com.example.app.DTO.ReservationDTO;
import com.example.app.DTO.RoomDTO;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id_reservation;
    private double totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate ;
    private Status status;
    @ManyToOne
    //@JsonBackReference
    //@Fetch(FetchMode.JOIN)
    private Room room;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private appUser client;


    public ReservationDTO toDTO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId_reservation(this.getId_reservation());
        reservationDTO.setStartDate(this.getStartDate());
        reservationDTO.setEndDate(this.getEndDate());
        reservationDTO.setStatus(this.getStatus());
        reservationDTO.setTotalPrice(this.getTotalPrice());
        RoomDTO roomDTO = new RoomDTO();
        reservationDTO.setRoomReservations(Arrays.asList(roomDTO));
        return reservationDTO;
    }
}
