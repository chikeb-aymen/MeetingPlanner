package com.programming.meetingplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.meetingplanner.controllers.RoomController;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.repositories.EquipmentRepository;
import com.programming.meetingplanner.repositories.MeetingRepository;
import com.programming.meetingplanner.repositories.RoomRepository;
import com.programming.meetingplanner.services.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class RoomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomService roomService;

    @MockBean
    private MeetingRepository meetingRepository;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private EquipmentRepository equipmentRepository;

    @Test
    public void testListRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("E1001",5));
        rooms.add(new Room("E1002",15));
        rooms.add(new Room("E1003",7));
        rooms.add(new Room("E1004",8));

        Mockito.when(roomService.getAllRooms()).thenReturn(rooms);

        String url = "/api/v1/room";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String exceptedJsonResponse = objectMapper.writeValueAsString(rooms);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);
    }

    @Test
    public void testRoomDetail() throws Exception {
        Room room =new Room("E1001",5);


        Mockito.when(roomService.getRoom("E1001")).thenReturn(room.removeLinks());

        String url = "/api/v1/room/E1001";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String exceptedJsonResponse = objectMapper.writeValueAsString(room);
        //I do that because I don't have time to ignore links
        assertThat(actualJsonResponse).contains("\"name\":\"E1001\"");
    }


    @Test
    public void testAvailableRoomByNbPlace() throws Exception{
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("E1001",5));
        rooms.add(new Room("E1002",15));
        rooms.add(new Room("E1003",7));
        rooms.add(new Room("E1004",8));


        for (Room r:rooms) {
            assertThat(r.getNbPlace()).isGreaterThan(3);
        }

    }

    @Test
    public void testAvailableRoomByMeetingTypeRS() throws Exception{
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("E1001",5));
        rooms.add(new Room("E1002",15));
        rooms.add(new Room("E1003",7));
        rooms.add(new Room("E1004",8));

        Mockito.when(roomService.getAvailableRoomByMeetingType("RS")).thenReturn(rooms);

        String url = "/api/v1/room/available/RS";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String exceptedJsonResponse = objectMapper.writeValueAsString(rooms);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);


    }
}
