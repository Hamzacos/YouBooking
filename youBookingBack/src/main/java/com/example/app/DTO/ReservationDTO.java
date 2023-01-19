package com.example.app.DTO;

import com.example.app.Entity.Reservation;
import com.example.app.Entity.Room;
import com.example.app.Entity.Status;
import com.example.app.Entity.appUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class ReservationDTO {
    private Long id_reservation;
    private double totalPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Status status;
    private appUser client;
    private RoomDTO room;
    private List<RoomDTO> roomReservations;


    public Reservation toEntity(){
        Room room = this.room.toEntity();
        Reservation reservation = new Reservation();
        reservation.setStartDate(this.startDate);
        reservation.setEndDate(this.endDate);
        reservation.setTotalPrice(this.totalPrice);
        reservation.setStatus(this.status);
        reservation.setClient(this.client);
        reservation.setRoom(room);
        return reservation;
    }

    public ReservationDTO(){}

    public ReservationDTO(Reservation reservation) {
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
    }

    public List<RoomDTO> getRoomReservations() {
        return roomReservations;
    }


    public void setRoomReservations(List<RoomDTO> roomReservations) {
        this.roomReservations = roomReservations;
    }


    public Long getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(Long id_reservation) {
        this.id_reservation = id_reservation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public appUser getClient() {
        return client;
    }

    public void setClient(appUser client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id_reservation=" + id_reservation +
                ", totalPrice=" + totalPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", room=" + room +
                ", roomReservations=" + roomReservations +

                '}';
    }
}
