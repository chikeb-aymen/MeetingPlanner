package com.programming.meetingplanner.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    /**
     * Matricule => Car chaque équipement a un matricule pour distinguer que cette outils est de cette salle
     * value => la meme valeur 111
     */
    @Getter
    private String reg_number;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "equipments")
    @Getter(onMethod_ = @JsonIgnore)
    private List<Room> room = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "equipments")
    @Getter(onMethod_ = @JsonIgnore)
    private List<Meeting> meeting = new ArrayList<>();

    public Equipment(String name) {
        this.name = name;
        this.reg_number = "111";
    }

}
