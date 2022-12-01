package com.programming.meetingplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.meetingplanner.controllers.ReservationController;
import com.programming.meetingplanner.dtos.ReservationDTO;
import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.repositories.EquipmentRepository;
import com.programming.meetingplanner.repositories.MeetingRepository;
import com.programming.meetingplanner.repositories.RoomRepository;
import com.programming.meetingplanner.services.ReservationService;
import com.programming.meetingplanner.services.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private MeetingRepository meetingRepository;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private EquipmentRepository equipmentRepository;


    @Test
    public void testBookingRoom() throws Exception {
        Room room =new Room(1L,"E1001",5);
        Reservation savedReservation = new Reservation(LocalDateTime.of(2022,12,01,8,00,00),
                LocalDateTime.of(2022,12,01,9,00,00),"",room);

        ReservationDTO reservationDTO = new ReservationDTO(LocalDateTime.of(2022,12,01,8,00,00),
                LocalDateTime.of(2022,12,01,9,00,00),"",3);

        Mockito.when(reservationService.bookRoom(reservationDTO)).thenReturn(savedReservation);

        String url = "/api/v1/reservation";


        MvcResult mvcResult = mockMvc.perform(
                post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservationDTO))
                ).andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String exceptedJsonResponse = objectMapper.writeValueAsString(savedReservation);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(exceptedJsonResponse);

    }

}
