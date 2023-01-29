package com.sacavix.todoapp.model;

import com.sacavix.todoapp.util.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(unique = true, nullable = false, name = "title")
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "eta", nullable = false)
    private LocalDateTime eta; //Fecha de finalizacion estimada

    @Column(name = "finished", nullable = false)
    private Boolean finished;

    @Column(name = "taskStatus", nullable = false)
    private TaskStatus taskStatus;
}
