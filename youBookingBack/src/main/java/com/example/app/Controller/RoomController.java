package com.example.app.Controller;


import com.example.app.Entity.Room;
import com.example.app.Repositry.RoomReposetry;
import com.example.app.Sevice.Impl.RommServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RommServiceImpl rommService;

    @Autowired
    private RoomReposetry roomReposetry;

    @PostMapping(path = "/addRoom/{name}")
    @PostAuthorize("hasAuthority('Manger')")
    public Room addRoom(@RequestBody Room room, @PathVariable("name")String name){
        //room.addReservation(room.getRoomResarvation().get(0));
        return  rommService.SaveRoom(room,name);
    }

    @GetMapping(path = "/showRoom")
//    @PostAuthorize("hasAnyAuthority('Manger') and hasAnyAuthority('Admin')")
    public List<Room> showRoom(){
        return rommService.listRoom();
    }
}
