package com.programming.meetingplanner;

import com.programming.meetingplanner.models.Equipment;
import com.programming.meetingplanner.models.Meeting;
import com.programming.meetingplanner.models.Room;
import com.programming.meetingplanner.repositories.EquipmentRepository;
import com.programming.meetingplanner.repositories.MeetingRepository;
import com.programming.meetingplanner.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MeetingPlannerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MeetingPlannerApplication.class, args);
    }


    private final RoomRepository roomRepository;
    private final EquipmentRepository equipmentRepository;

    private final MeetingRepository meetingRepository;



    public MeetingPlannerApplication(RoomRepository roomRepository, EquipmentRepository equipmentRepository, MeetingRepository meetingRepository) {
        this.roomRepository = roomRepository;
        this.equipmentRepository = equipmentRepository;
        this.meetingRepository = meetingRepository;
    }

    @Override
    @Transactional
    public void run(String... args){

        /*
        Equipment neant = new Equipment("Neant");
        Equipment ecran = new Equipment("Ecran");
        Equipment pieuvre = new Equipment("Pieuvre");
        Equipment tableau = new Equipment("Tableau");
        Equipment webcam = new Equipment("Webcam");

        equipmentRepository.saveAll(List.of(neant,ecran,pieuvre,tableau,webcam));

        Room r1 = new Room("E1001",23);
        r1.getEquipments().add(neant);

        Room r2 = new Room("E1002",10);
        r2.getEquipments().add(ecran);

        Room r3 = new Room("E1003",8);
        r3.getEquipments().add(pieuvre);

        Room r4 = new Room("E1004",4);
        r4.getEquipments().add(tableau);

        Room r5 = new Room("E2001",4);
        r5.getEquipments().add(neant);

        Room r6 = new Room("E2002",15);
        r6.getEquipments().add(ecran);
        r6.getEquipments().add(webcam);

        Room r7 = new Room("E2003",7);
        r7.getEquipments().add(neant);

        Room r8 = new Room("E2004",9);
        r8.getEquipments().add(tableau);

        Room r9 = new Room("E3001",13);
        r9.getEquipments().add(ecran);
        r9.getEquipments().add(webcam);
        r9.getEquipments().add(pieuvre);

        Room r10 = new Room("E3002",8);
        r10.getEquipments().add(neant);

        Room r11 = new Room("E3003",9);
        r11.getEquipments().add(ecran);
        r11.getEquipments().add(pieuvre);


        Room r12 = new Room("E3004",4);
        r12.getEquipments().add(neant);

        roomRepository.saveAll(List.of(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12));




        Meeting m1 = new Meeting("VC");
        m1.getEquipments().add(ecran);
        m1.getEquipments().add(pieuvre);
        m1.getEquipments().add(webcam);

        Meeting m2 = new Meeting("SPEC");
        m2.getEquipments().add(tableau);

        Meeting m3 = new Meeting("RS");
        Meeting m4 = new Meeting("RC");
        m4.getEquipments().add(ecran);
        m4.getEquipments().add(pieuvre);
        m4.getEquipments().add(tableau);


        meetingRepository.saveAll(List.of(m1,m2,m3,m4));

         */





    }
}
