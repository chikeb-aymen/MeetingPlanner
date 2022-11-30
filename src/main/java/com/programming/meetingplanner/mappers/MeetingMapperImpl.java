package com.programming.meetingplanner.mappers;

import com.programming.meetingplanner.dtos.ReservationDTO;
import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.repositories.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MeetingMapperImpl {

    private MeetingRepository meetingRepository;

    public Reservation fromReservationDTO(ReservationDTO reservationDTO, Room room){
        Reservation r = new Reservation();
        r.setStartDate(reservationDTO.getStartDate());
        r.setEndDate(reservationDTO.getEndDate());
        r.setMeeting(meetingRepository.findMeetingByType(reservationDTO.getMeetingType()));
        r.setRoom(room);

        return r;

    }

}
