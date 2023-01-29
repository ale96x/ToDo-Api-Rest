package com.sacavix.todoapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse { //Una clase POJO
    private LocalDateTime dateTime;
    private String message;
    private String path;
}
