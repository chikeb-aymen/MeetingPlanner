package com.programming.meetingplanner.repositories;

import com.programming.meetingplanner.models.Reservation;
import com.programming.meetingplanner.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Boolean existsReservationByStartDateAndEndDateAndMeetingType(LocalDateTime s,LocalDateTime e,String meetingType);

}
