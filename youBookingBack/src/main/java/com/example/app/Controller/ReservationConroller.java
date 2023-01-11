package com.example.app.Controller;


import com.example.app.Entity.Reservation;
import com.example.app.Entity.Room;
import com.example.app.Repositry.RoomReposetry;
import com.example.app.Sevice.Impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReservationConroller {

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private RoomReposetry roomReposetry;

    @PostMapping(path = "/addReseration")
    public ResponseEntity<Object> AddResrvation(@RequestBody Reservation reservation){
        return reservationService.AddReservation(reservation);
    }

    @GetMapping(path = "/Reservation")
    public List<Reservation> showAllResrvation(){
        return reservationService.showAll();
    }

    @DeleteMapping("/resrvation/{id}")
    public String deleteResevationById(@PathVariable("id") Long resrvationId) {
        reservationService.deletResrvation(resrvationId);
        return "Deleted Successfully";
    }

    @GetMapping("/checkAvailability")
    public boolean checkAvailability(@RequestParam Long roomId,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        Room room = roomReposetry.findById(roomId).orElse(null);
        return reservationService.isRoomAvailable(room, startDate, endDate);
    }

    @GetMapping("/calculateTotal")
    public ResponseEntity<Double> calculateTotal(@RequestParam Long roomId,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Room room = roomReposetry.findById(roomId).orElse(null);
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reservationService.calculateReservationTotal(room, startDate, endDate));
    }
}
