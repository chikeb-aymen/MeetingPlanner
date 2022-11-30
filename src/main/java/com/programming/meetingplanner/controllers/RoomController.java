package com.programming.meetingplanner.controllers;

import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/room")
@AllArgsConstructor
public class RoomController {


    /**
     * TODO
     * Exception
     * Logging
     * Constraint
     * Documentation
     */
    private RoomService roomService;

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


    @GetMapping("/{name}")
    public ResponseEntity<Room> getRoomDetail(@PathVariable("name") String name){
        Link allRoomsLink = linkTo(methodOn(RoomController.class).getAllRooms()).withRel("allRooms");
        Link selfLink = linkTo(methodOn(RoomController.class).getRoomDetail(name)).withSelfRel();

        return new ResponseEntity<>(roomService.getRoom(name).add(selfLink,allRoomsLink),HttpStatus.OK);
    }


    @GetMapping("/available/{type}")
    public ResponseEntity<List<Room>> getAvailableRoomByMeetingType(@PathVariable("type") String type){
        return new ResponseEntity<>(roomService.getAvailableRoomByMeetingType(type),HttpStatus.OK);
    }


    @GetMapping("/nb_place/{nbPlace}")
    public ResponseEntity<List<Room>> getAvailableRoomByMeetingType(@PathVariable("nbPlace") Integer nbPlace){
        return new ResponseEntity<>(roomService.findRoomByNbPlace(nbPlace),HttpStatus.OK);
    }


}
