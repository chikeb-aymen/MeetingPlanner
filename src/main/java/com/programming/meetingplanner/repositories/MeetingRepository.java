package com.programming.meetingplanner.repositories;

import com.programming.meetingplanner.models.Meeting;
import com.programming.meetingplanner.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {
}
