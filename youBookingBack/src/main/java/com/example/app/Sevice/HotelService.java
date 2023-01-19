package com.example.app.Sevice;

import com.example.app.DTO.HotelDTO;
import com.example.app.Entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    Hotel updateHotel(Hotel hotel, Long hotelId);
    HotelDTO getById(Long id);

    List<HotelDTO> listHotel();

    Hotel HotelhasProved(Long hotelId);

    void deletHotel(Long id);

    String currentUser();
}
