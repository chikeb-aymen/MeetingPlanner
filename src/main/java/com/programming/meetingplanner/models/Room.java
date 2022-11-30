package com.programming.meetingplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Room extends RepresentationModel<Room> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private Integer nbPlace;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "room_equipments",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    @Getter
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    @Getter(onMethod_ = @JsonIgnore)
    private List<Reservation> reservations = new ArrayList<>();


    public Room(String name, Integer nb_place) {
        this.name = name;
        this.nbPlace = nb_place;
    }

    public Room(Long id, String name, Integer nb_place) {
        this.id = id;
        this.name = name;
        this.nbPlace = nb_place;
    }
}
