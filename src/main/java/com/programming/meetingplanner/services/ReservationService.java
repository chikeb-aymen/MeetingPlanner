package com.programming.meetingplanner.services;

import com.programming.meetingplanner.dtos.ReservationDTO;
import com.programming.meetingplanner.exception.DataFound;
import com.programming.meetingplanner.exception.WrongData;
import com.programming.meetingplanner.mappers.MeetingMapperImpl;
import com.programming.meetingplanner.models.Equipment;
import com.programming.meetingplanner.models.Meeting;
import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.repositories.MeetingRepository;
import com.programming.meetingplanner.repositories.ReservationRepository;
import com.programming.meetingplanner.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReservationService {

    private ReservationRepository reservationRepository;

    private RoomRepository roomRepository;

    private RoomService roomService;

    private MeetingRepository meetingRepository;

    private MeetingMapperImpl meetingMapper;

    /*
    public List<Room> availableRooms(ReservationDTO reservationDTO) throws Exception {


        Meeting m = meetingRepository.findMeetingByType(reservationDTO.getMeetingType());
        if(m==null)
            throw new EntityNotFoundException("There are no meeting with this name");


        if(reservationRepository.existsReservationByStartDateAndEndDateAndMeetingType(
                reservationDTO.getStartDate(),reservationDTO.getEndDate(),reservationDTO.getMeetingType())){

            if(reservationDTO.getMeetingType().equals("RS")){
                return roomRepository.findAllByNbPlaceGreaterThan(Integer.parseInt("3"));
            }
            else
                return roomService.getRoomByMeetingRequirements(m.getEquipments());

        }else{
            throw new EntityNotFoundException("No room available");
        }

    }*/



    @Transactional
    public Reservation bookRoom(ReservationDTO reservationDTO) throws Exception {
        //check
        roomService.checkReservationDTO(reservationDTO);

        List<Room> rooms = availableRooms(reservationDTO);

        Reservation r = meetingMapper.fromReservationDTO(reservationDTO,rooms.get(0));

        return reservationRepository.save(r);

    }



    public List<Room> availableRooms(ReservationDTO reservationDTO) throws Exception {

        //check
        roomService.checkReservationDTO(reservationDTO);

        Meeting m = meetingRepository.findMeetingByType(reservationDTO.getMeetingType());
        if(m==null)
            throw new EntityNotFoundException("There are no meeting with this name");


        

        if(roomService.getAvailableRoomByEquipment(reservationDTO,m.getEquipments())!=null){

            if(roomService.getAvailableRoomByEquipment(reservationDTO, m.getEquipments()).size() == 0)
                throw new EntityNotFoundException("There are no room in this hour");

            return roomService.getAvailableRoomByEquipment(reservationDTO,m.getEquipments());

        }else{
            throw new EntityNotFoundException("No room available");
        }

    }




}
