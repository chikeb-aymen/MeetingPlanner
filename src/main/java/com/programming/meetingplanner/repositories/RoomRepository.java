package com.programming.meetingplanner.repositories;

import com.programming.meetingplanner.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findByName(String name);

    List<Room> findAllByNbPlaceGreaterThan(Integer n);


    @Query(nativeQuery =true,value="select * from Room r where r.id NOT IN (select room_id from reservation where start_date=:start_date and end_date=:end_date)")
    List<Room> findRoomNotBooked(@Param("start_date") LocalDateTime start_date,@Param("end_date") LocalDateTime end_date);

    /*
        select * from room r
        where r.id in
        (
            select room_id from room_equipments rq inner join equipment e on
            rq.equipment_id = e.id where e."name" = 'Ecran'
            intersect
            select room_id from room_equipments rq inner join equipment e on
            rq.equipment_id = e.id where e."name" = 'Webcam'
            intersect
            select room_id from room_equipments rq inner join equipment e on
            rq.equipment_id = e.id where e."name" = 'Pieuvre'
        )
     */
}
