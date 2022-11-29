package com.programming.meetingplanner.controllers;

import com.programming.meetingplanner.dtos.ReservationDTO;
import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.services.ReservationService;
import lombok.AllArgsConstructor;
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
}
