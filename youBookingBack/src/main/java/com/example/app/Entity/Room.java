package com.example.app.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String name;
    private int capacity;
    private int numberOfRoom;
    private double price;

    @ManyToOne
    @JsonBackReference
    private Hotel hotel;
   @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
   @JsonManagedReference
   List<Reservation> roomResarvation = new ArrayList<>();


    public void addReservation(Reservation reservation) {
        roomResarvation.add(reservation);
        reservation.setRoom(this);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", capacity=" + capacity +
                ", numberOfRoom=" + numberOfRoom +
                ", price=" + price +
                '}';
    }

}
