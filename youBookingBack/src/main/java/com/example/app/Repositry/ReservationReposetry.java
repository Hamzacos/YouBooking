package com.example.app.Repositry;

import com.example.app.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationReposetry extends JpaRepository<Reservation,Long> {
}
