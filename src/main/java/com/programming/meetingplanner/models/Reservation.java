package com.programming.meetingplanner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer startHour;

    private Integer endHour;

    private LocalDateTime localDate;

    /**
     * Type de reservation => il peut une reunion ou un nettoyage (meme si il n'est pas une reservation)
     */
    private String type;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;


    /**
     * Type de reunion (il existe 4 type)
     */
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="meeting_id")
    private Meeting meeting;


}
