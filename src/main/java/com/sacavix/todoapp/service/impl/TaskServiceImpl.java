package com.sacavix.todoapp.service.impl;

import com.sacavix.todoapp.model.Task;
import com.sacavix.todoapp.repository.IGenericRepository;
import com.sacavix.todoapp.repository.ITaskRepository;
import com.sacavix.todoapp.service.ITaskService;
import com.sacavix.todoapp.util.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskServiceImpl extends CRUDImpl<Task,Long> implements ITaskService {

    @Autowired
    private ITaskRepository repository;
    @Override
    public IGenericRepository<Task, Long> getRepository() {
        return this.repository;
    }

    @Override
    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return this.repository.findAllByTaskStatus(status);
    }


    @Transactional
    @Override
    public int setTaskAsFinished(Long id) {
        return this.repository.setTaskAsFinished(id);
    }
}
