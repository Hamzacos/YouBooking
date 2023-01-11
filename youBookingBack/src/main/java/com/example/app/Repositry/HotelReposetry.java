package com.example.app.Repositry;

import com.example.app.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelReposetry extends JpaRepository<Hotel,Long> {

    Hotel findByName(String Name);

}
