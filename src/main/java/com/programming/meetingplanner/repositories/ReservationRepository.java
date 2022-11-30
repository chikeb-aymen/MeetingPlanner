package com.programming.meetingplanner.repositories;

import com.programming.meetingplanner.models.Meeting;
import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Boolean existsReservationByStartDateAndEndDateAndMeetingType(LocalDateTime s,LocalDateTime e,String meetingType);

    List<Reservation> getAllByStartDateAndEndDate(LocalDateTime s,LocalDateTime e);

    List<Reservation> findAllByRoomName(String name);

    List<Reservation> findAllByMeeting(Meeting m);

    List<Reservation> findAllByStartDateAfterAndEndDateBefore(LocalDateTime s,LocalDateTime e);
}
