package com.programming.meetingplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private LocalDateTime startDate;

    @Getter
    private LocalDateTime endDate;

    /**
     * Type de reservation => il peut une reunion ou un nettoyage (meme si il n'est pas une reservation)
     */
    private String type;

    @ManyToOne
    @Getter
    @JoinColumn(name="room_id")
    private Room room;


    /**
     * Type de reunion (il existe 4 type)
     */
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="meeting_id")
    @Getter(onMethod_ = @JsonIgnore)
    private Meeting meeting;

    public Reservation(LocalDateTime startDate, LocalDateTime endDate, String type, Room room) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.room = room;
    }
}
