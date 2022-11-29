package com.programming.meetingplanner.services;

import com.programming.meetingplanner.dtos.ReservationDTO;
import com.programming.meetingplanner.exception.DataFound;
import com.programming.meetingplanner.exception.WrongData;
import com.programming.meetingplanner.models.Equipment;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.repositories.ReservationRepository;
import com.programming.meetingplanner.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    public List<Room> getAllRooms(){
        if(roomRepository.findAll().isEmpty())
            throw new EntityNotFoundException("No room found");
        return roomRepository.findAll();
    }

    public Room getRoom(String name){
        if(roomRepository.findByName(name)==null)
            throw new EntityNotFoundException("No room found with the name {"+name+"}");

        return roomRepository.findByName(name);
    }


    public void checkReservationDTO(ReservationDTO reservationDTO) throws Exception {

        if(reservationDTO.getStartDate().compareTo(LocalDateTime.now()) < 0)
            throw new WrongData("You can only reserve meeting from now");

        if(reservationDTO.getStartDate().getHour()<8
                || reservationDTO.getEndDate().getHour()>20 || reservationDTO.getStartDate().getHour()>20
                || isWeekend(reservationDTO.getStartDate()) || isWeekend(reservationDTO.getEndDate()) )
            throw new WrongData("The meeting reservation can only begin from 8:00 to 20:00 and no week-end");

        if(reservationDTO.getStartDate().getDayOfMonth()!=reservationDTO.getEndDate().getDayOfMonth()
                || reservationDTO.getStartDate().getMonthValue()!=reservationDTO.getEndDate().getMonthValue()
                || reservationDTO.getStartDate().getYear()!=reservationDTO.getEndDate().getYear())
            throw new WrongData("You can only reserve one hour");

        if(reservationDTO.getEndDate().getHour() > reservationDTO.getStartDate().getHour() + 1
                || reservationDTO.getStartDate().getHour() >= reservationDTO.getEndDate().getHour())
            throw new WrongData("You can only reserve one hour");

        if(reservationDTO.getStartDate().getMinute()!=0 || reservationDTO.getEndDate().getMinute()!=0 ||
                reservationDTO.getStartDate().getSecond()!=0 || reservationDTO.getEndDate().getSecond()!=0 )
            throw new WrongData("You can't reserve with this format of time - like 9:00:00");


        if(reservationRepository.existsReservationByStartDateAndEndDateAndMeetingType(reservationDTO.getStartDate(),reservationDTO.getEndDate(),reservationDTO.getMeetingType()))
            throw new DataFound("This room is already reserved in this hour");

    }

    public List<Room> getRoomByMeetingRequirements(List<Equipment> equipments){
        List<Room> rooms = roomRepository.findAll();

        List<Room> meetingRooms = new ArrayList<>();

        rooms.forEach(room -> {
            if(room.getEquipments().size()!=equipments.size())
                return;

            for (int i = 0; i < room.getEquipments().size(); i++) {
                if(!equipments.contains(room.getEquipments().get(i)))
                    return;

            }
            meetingRooms.add(room);

        });

        return meetingRooms;
    }




    private static boolean isWeekend(LocalDateTime localDate) {

        // get Day of week for the passed LocalDate
        return (localDate.get(ChronoField.DAY_OF_WEEK) == 6)
                || (localDate.get(ChronoField.DAY_OF_WEEK) == 7);
    }
}
