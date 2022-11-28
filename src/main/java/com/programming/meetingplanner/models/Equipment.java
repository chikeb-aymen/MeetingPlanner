package com.programming.meetingplanner.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Matricule => Car chaque équipement a un matricule pour distinguer que cette outils est de cette salle
     * value => la meme valeur 111
     */
    private String reg_number;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "equipments")
    @JsonIgnore
    private Set<Room> room = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "equipments")
    @JsonIgnore
    private Set<Meeting> meeting = new HashSet<>();

    public Equipment(String name) {
        this.name = name;
        this.reg_number = "111";
    }

}
