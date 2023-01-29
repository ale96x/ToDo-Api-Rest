package com.sacavix.todoapp.service;

import com.sacavix.todoapp.model.Task;
import com.sacavix.todoapp.util.TaskStatus;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITaskService extends ICRUD<Task,Long>{
    List<Task> findAllByTaskStatus(TaskStatus status);

    int setTaskAsFinished(Long id);
}
