package com.example.app.Controller;


import com.example.app.DTO.ReservationDTO;
import com.example.app.Entity.Reservation;
import com.example.app.Entity.Room;
import com.example.app.Repositry.RoomReposetry;
import com.example.app.Sevice.Impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationConroller {

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private RoomReposetry roomReposetry;

    @PostMapping(path = "/addReservation")
    public ResponseEntity<Object> AddResrvation(@RequestBody ReservationDTO reservation){
        return reservationService.AddReservation(reservation);
    }

    @GetMapping(path = "/Reservation")
    public List<ReservationDTO> showAllResrvation(){
        return reservationService.showAll();
    }

    @DeleteMapping("/resrvation/{id}")
    public String deleteResevationById(@PathVariable("id") Long resrvationId) {
        reservationService.deletResrvation(resrvationId);
        return "Deleted Successfully";
    }

    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<Object> cancelReservation(@PathVariable Long reservationId) {
        boolean isCanceled = reservationService.cancelReservation(reservationId);
        if(isCanceled) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateReservation/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable Long id,
                                                  @RequestParam(value = "startDate", required = true)
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkin,
                                                  @RequestParam(value = "endDate",required = true)
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkout) {
        reservationService.updateReservation(id, checkin, checkout);
        return new ResponseEntity<>(HttpStatus.OK);
    }

        @GetMapping("/my-reservations")
        public List<ReservationDTO> getMyReservations() {
            return reservationService.getMyReservations();
    }
}
