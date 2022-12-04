package com.programming.meetingplanner.controllers;

import com.programming.meetingplanner.MeetingPlannerApplication;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.repositories.RoomRepository;
import com.programming.meetingplanner.services.RoomService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MeetingPlannerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerIT {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testRoomE1001(){
        Room room = roomService.getRoom("E1001");
        assertEquals("E1001",room.getName());
        assertEquals(23,room.getNbPlace());
        assertEquals(1,room.getEquipments().size());
    }
    @Test
    public void testAllRooms(){
        List<Room> room = roomService.getAllRooms();
        assertEquals(12,room.size());
    }

    @Test
    public void testRoomNotFoundException(){
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,()->roomService.getRoom("1001"));

        assertEquals("No room found with the name {1001}",exception.getMessage());
    }

    @Test
    public void testCreateRoom(){
        Room room = roomRepository.save(new Room("E1005",20));

        assertThat(room).hasFieldOrPropertyWithValue("name", "E1005");
        assertThat(room).hasFieldOrPropertyWithValue("nbPlace", 20);
    }


    @Test
    public void shouldDeleteRoom(){

        Room room = roomService.getRoom("E1005");
        assertEquals(13,roomService.getAllRooms().size());

        roomRepository.deleteById(room.getId());

        assertEquals(12,roomService.getAllRooms().size());
    }

}
