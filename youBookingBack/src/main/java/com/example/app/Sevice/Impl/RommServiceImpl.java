package com.example.app.Sevice.Impl;


import com.example.app.Entity.Hotel;
import com.example.app.Entity.Room;
import com.example.app.Repositry.HotelReposetry;
import com.example.app.Repositry.RoomReposetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RommServiceImpl {

    @Autowired
    private RoomReposetry roomReposetry;
    @Autowired
    private HotelReposetry hotelReposetry;




    public Room SaveRoom(Room room,String nameHotel){
        room.setHotel(hotelReposetry.findByName(nameHotel));
        return roomReposetry.save(room);
    }

    public List<Room> listRoom() {

        return roomReposetry.findAll();
    }
}
