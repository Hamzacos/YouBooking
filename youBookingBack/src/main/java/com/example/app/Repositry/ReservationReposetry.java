package com.example.app.Repositry;

import com.example.app.Entity.Reservation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationReposetry extends JpaRepository<Reservation, Long> {

    //@Query("SELECT r FROM Reservation r JOIN FETCH r.room")
    //List<Reservation> findAll();

    @Query("SELECT r from Reservation r where r.room.roomId = :roomId and  " +
            "(:startDate between r.startDate and r.endDate OR :endDate between r.startDate and r.endDate)")
    List<Reservation> findByRoomIdAndStartDateBetween(@Param("roomId") Long roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List <Reservation>findByClientUsername(String username);
}
