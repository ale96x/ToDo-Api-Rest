package com.sacavix.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sacavix.todoapp.util.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime eta; //Fecha de finalizacion estimada
    private Boolean finished;
    private TaskStatus taskStatus;
}
