package com.programming.meetingplanner.controllers;

import com.programming.meetingplanner.MeetingPlannerApplication;
import com.programming.meetingplanner.controllers.ReservationController;
import com.programming.meetingplanner.dtos.ReservationDTO;
import com.programming.meetingplanner.mappers.MeetingMapperImpl;
import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = MeetingPlannerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ReservationControllerITests {


    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private MeetingMapperImpl meetingMapper;
    @BeforeEach
    void setup(){

    }
    @Test
    public void retrieveRoomDetail() throws Exception {
        // given - precondition or setup

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/v1/room/E1001",
                HttpMethod.GET, entity, String.class);


        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"E1001\",\n" +
                "    \"nbPlace\": 23,\n" +
                "    \"equipments\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Neant\",\n" +
                "            \"reg_number\": \"111\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"_links\": {\n" +
                "        \"self\": {\n" +
                "            \"href\": \"http://localhost:8080/api/v1/room/E1001\"\n" +
                "        },\n" +
                "        \"allRooms\": {\n" +
                "            \"href\": \"http://localhost:8080/api/v1/room\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }


}
