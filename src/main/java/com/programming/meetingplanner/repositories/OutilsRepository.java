package com.programming.meetingplanner.repositories;


import com.programming.meetingplanner.models.Equipment;
import com.programming.meetingplanner.models.Room;

import java.util.List;


public class OutilsRepository {

    /**
     * I Make a query because JPA does not support the SQL UNION, INTERSECT and EXCEPT operations. Some JPA providers may support these.
     */
    public List<Room> findAvailableRoomByMeeting(List<Equipment> equipments){

        String queryStr = "select * from room r where r.id in (";

        for (int i = 0; i < equipments.size(); i++) {
            queryStr += "select room_id from room_equipments rq inner join equipment e on rq.equipment_id = e.id where e.'name' = 'Ecran'";
            if(i+1 != equipments.size())
                queryStr += "intersect";
        }
        queryStr += ")";


        return null;

    }
}
