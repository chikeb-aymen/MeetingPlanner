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


    private RoomService roomService;

    private MeetingRepository meetingRepository;

    private MeetingMapperImpl meetingMapper;



    @Transactional
    public Reservation bookRoom(ReservationDTO reservationDTO) throws Exception {
        //check
        roomService.checkReservationDTO(reservationDTO);

        List<Room> rooms = availableRooms(reservationDTO);

        Reservation r = meetingMapper.fromReservationDTO(reservationDTO,rooms.get(0));


        /**
         * Booking room 1Hour for cleaning
         */
        Reservation bookCleaning = meetingMapper.fromReservationDTO(reservationDTO,rooms.get(0));
        LocalDateTime nStartDate = bookCleaning.getStartDate().plusHours(1);
        LocalDateTime nEndDate = bookCleaning.getEndDate().plusHours(1);
        bookCleaning.setStartDate(nStartDate);
        bookCleaning.setEndDate(nEndDate);
        bookCleaning.setType("Cleaning");

        reservationRepository.save(bookCleaning);

        return reservationRepository.save(r);

    }




    public List<Room> availableRooms(ReservationDTO reservationDTO) throws Exception {

        //check
        roomService.checkReservationDTO(reservationDTO);

        Meeting m = meetingRepository.findMeetingByType(reservationDTO.getMeetingType());
        if(m==null)
            throw new EntityNotFoundException("There are no meeting with this name");


        if(roomService.getAvailableRoomByBookingDate(reservationDTO,m.getEquipments())!=null){

            if(roomService.getAvailableRoomByBookingDate(reservationDTO, m.getEquipments()).size() == 0)
                throw new EntityNotFoundException("There are no room in this hour");

            System.out.println("Size ...."+roomService.getAvailableRoomByBookingDate(reservationDTO,m.getEquipments()).size());
            return roomService.getAvailableRoomByBookingDate(reservationDTO,m.getEquipments());

        }else{
            throw new EntityNotFoundException("No room available");
        }

    }



    public List<Reservation> getReservationsByRoom(String name){
        if(reservationRepository.findAllByRoomName(name).size()==0)
            throw new EntityNotFoundException("No reservation in this room");

        return reservationRepository.findAllByRoomName(name);
    }


    public List<Reservation> getReservationsByMeetingName(String type){
        Meeting m = meetingRepository.findMeetingByType(type);
        if(reservationRepository.findAllByMeeting(m).size()==0)
            throw new EntityNotFoundException("No reservation in this type of meeting");

        return reservationRepository.findAllByMeeting(m);
    }

    public List<Reservation> getReservationsByThiDay(){
        LocalDateTime startDate = LocalDateTime.now().withHour(7).withMinute(0).withSecond(0);
        LocalDateTime endDate = LocalDateTime.now().withHour(21).withMinute(0).withSecond(0);

        if(reservationRepository.findAllByStartDateAfterAndEndDateBefore(startDate,endDate).size()==0)
            throw new EntityNotFoundException("No reservation in this day");

        return reservationRepository.findAllByStartDateAfterAndEndDateBefore(startDate,endDate);
    }


}
