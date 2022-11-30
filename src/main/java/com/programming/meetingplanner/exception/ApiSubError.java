package com.programming.meetingplanner.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSubError {

    private String object;

    private String field;

    private String rejectedValue;

    private String message;

    public ApiSubError(String object, String field, String message) {
        this.object = object;
        this.field = field;
        this.message = message;
    }
}
