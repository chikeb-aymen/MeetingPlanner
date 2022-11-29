package com.programming.meetingplanner.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ReservationDTO {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String meetingType;

    private Integer numberOfPeople;

}
