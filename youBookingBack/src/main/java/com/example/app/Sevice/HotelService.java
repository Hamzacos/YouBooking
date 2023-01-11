package com.example.app.Sevice;

import com.example.app.Entity.Hotel;
import com.example.app.Entity.appUser;

import java.security.Principal;
import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    Hotel updateHotel(Hotel hotel, Long hotelId);

    List<Hotel> listHotel();

    Hotel HotelhasProved(Long hotelId);

    void deletHotel(Long id);

    String currentUser();
}
