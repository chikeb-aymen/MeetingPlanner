package com.programming.meetingplanner.controllers;

import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * ROOM API
 */
@RestController
@RequestMapping("/api/v1/room")
@AllArgsConstructor
public class RoomController {


    private RoomService roomService;


    /**
     * Get all rooms
     * @return List of room
     */
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){

        Link allRoomsLink = linkTo(methodOn(RoomController.class).getAllRooms()).withRel("allRooms");

        List<Room> rooms = roomService.getAllRooms();

        rooms.forEach(room -> {
            Link selfLink = linkTo(methodOn(RoomController.class).getRoomDetail(room.getName())).withSelfRel();
            room.add(selfLink,allRoomsLink);
        });



        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }


    /**
     * Get room details by name
     * @param name
     * @return Room
     */
    @GetMapping("/{name}")
    public ResponseEntity<Room> getRoomDetail(@PathVariable("name") String name){
        Link allRoomsLink = linkTo(methodOn(RoomController.class).getAllRooms()).withRel("allRooms");
        Link selfLink = linkTo(methodOn(RoomController.class).getRoomDetail(name)).withSelfRel();

        return new ResponseEntity<>(roomService.getRoom(name).add(selfLink,allRoomsLink),HttpStatus.OK);
    }


    /**
     * Get available room for booking by meeting type
     * @param type
     * @return List of rooms
     */
    @GetMapping("/available/{type}")
    public ResponseEntity<List<Room>> getAvailableRoomByMeetingType(@PathVariable("type") String type){
        return new ResponseEntity<>(roomService.getAvailableRoomByMeetingType(type),HttpStatus.OK);
    }


    /**
     * Get Available room by nb of people (place)
     * @param nbPlace
     * @return List of room
     */
    @GetMapping("/nb_place/{nbPlace}")
    public ResponseEntity<List<Room>> getAvailableRoomByNbPlace(@PathVariable("nbPlace") Integer nbPlace){
        return new ResponseEntity<>(roomService.findRoomByNbPlace(nbPlace),HttpStatus.OK);
    }




}
