package com.programming.meetingplanner.controllers;

import com.programming.meetingplanner.dtos.ReservationDTO;
import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@AllArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    @GetMapping("/room/available")
    public List<Room> getAvailableRooms(@RequestBody ReservationDTO reservationDTO) throws Exception{
        return reservationService.availableRooms(reservationDTO);
    }

    @PostMapping
    public Reservation bookRoom(@RequestBody ReservationDTO reservationDTO) throws Exception{
        return reservationService.bookRoom(reservationDTO);
    }

    //Get all reservation by meeting type
    @GetMapping("/meeting/{type}")
    public ResponseEntity<List<Reservation>> getAllReservationByMeeting(@PathVariable("type") String type){
        return new ResponseEntity<>(reservationService.getReservationsByMeetingName(type), HttpStatus.OK);
    }

    @GetMapping("/room/{name}")
    public ResponseEntity<List<Reservation>> getAllReservationByRoom(@PathVariable("name") String name){
        return new ResponseEntity<>(reservationService.getReservationsByRoom(name), HttpStatus.OK);
    }

    //Get All room reservation this day
    @GetMapping("/thisDay")
    public ResponseEntity<List<Reservation>> getAllReservationThisDay(){
        return new ResponseEntity<>(reservationService.getReservationsByThiDay(), HttpStatus.OK);
    }

}
