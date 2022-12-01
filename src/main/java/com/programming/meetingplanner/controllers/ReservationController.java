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

/**
 * BOOKING API
 */
@RestController
@RequestMapping("/api/v1/reservation")
@AllArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    /**
     * Get All Available Room -> For Booking
     * @param reservationDTO(start_date, end_date, number of place require, type of meeting
     * @return List of Rooms
     * @throws Exception
     */
    @GetMapping("/room/available")
    public List<Room> getAvailableRooms(@RequestBody ReservationDTO reservationDTO) throws Exception{
        return reservationService.availableRooms(reservationDTO);
    }

    /**
     * Booking a room -> But if you want to see available room @Get "/room/available"
     * @param reservationDTO
     * @return Reservation information (booking, room booked, equipments booked)
     * @throws Exception
     */
    @PostMapping
    public Reservation bookRoom(@RequestBody ReservationDTO reservationDTO) throws Exception{
        return reservationService.bookRoom(reservationDTO);
    }

    /**
     * Get All booked room information by meeting type
     * @param type
     * @return List of booking
     */
    @GetMapping("/meeting/{type}")
    public ResponseEntity<List<Reservation>> getAllReservationByMeeting(@PathVariable("type") String type){
        return new ResponseEntity<>(reservationService.getReservationsByMeetingName(type), HttpStatus.OK);
    }

    /**
     * Get all booking by room name
     * @param name
     * @return List of booking room
     */
    @GetMapping("/room/{name}")
    public ResponseEntity<List<Reservation>> getAllReservationByRoom(@PathVariable("name") String name){
        return new ResponseEntity<>(reservationService.getReservationsByRoom(name), HttpStatus.OK);
    }

    /**
     * Get all booking room this day
     * @return
     */
    @GetMapping("/thisDay")
    public ResponseEntity<List<Reservation>> getAllReservationThisDay(){
        return new ResponseEntity<>(reservationService.getReservationsByThiDay(), HttpStatus.OK);
    }

}
