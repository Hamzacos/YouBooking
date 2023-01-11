package com.example.app.Controller;

import com.example.app.Entity.Hotel;
import com.example.app.Sevice.Impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelServiceImpl hotelService;


    @PostMapping(path = "/hotels")
    @PostAuthorize("hasAnyAuthority('Manger')")
    Hotel savehotel(@RequestBody Hotel hotel){
        return hotelService.saveHotel(hotel);
    }

    @PutMapping("/hotels/{id}")
    @PostAuthorize("hasAnyAuthority('Admin')")
    public Hotel hotelHasProved(@PathVariable("id") Long hotelId)
    { return hotelService.HotelhasProved(hotelId); }

    @GetMapping(path = "/hotles")
    @PostAuthorize("hasAnyAuthority('Admin')")
    public List<Hotel> GatAllHotel(){
        return hotelService.listHotel();
    }

    @PutMapping("/hotel/{id}")
    @PostAuthorize("hasAnyAuthority('Manger')")
    public Hotel updateHotel(@RequestBody Hotel hotel,@PathVariable("id") Long hotelId)
    { return hotelService.updateHotel(hotel,hotelId); }

    @DeleteMapping("/hotel/{id}")
    @PostAuthorize("hasAnyAuthority('Admin')")
    public String deleteCommandById(@PathVariable("id") Long hotelId) {
        hotelService.deletHotel(hotelId);
        return "Deleted Successfully";
    }

}
