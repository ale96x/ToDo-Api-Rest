package com.sacavix.todoapp.repository;

import com.sacavix.todoapp.model.Task;
import com.sacavix.todoapp.util.TaskStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITaskRepository extends IGenericRepository<Task,Long>{
    List<Task> findAllByTaskStatus(TaskStatus status);

    @Modifying
    @Query(nativeQuery = true, value = "update task set finished=true where id=:id")
    int setTaskAsFinished(@Param("id") Long id);
}
