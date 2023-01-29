package com.sacavix.todoapp.controller;

import com.sacavix.todoapp.dto.CreateTaskDTO;
import com.sacavix.todoapp.dto.TaskDTO;
import com.sacavix.todoapp.exception.ModelNotFoundException;
import com.sacavix.todoapp.model.Task;
import com.sacavix.todoapp.service.ITaskService;
import com.sacavix.todoapp.util.TaskStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> readAll() throws Exception{
        return new ResponseEntity<>(service.findAll().stream()
                .map(task -> mapper.map(task,TaskDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> readById(@PathVariable(name = "id") Long id) throws Exception{
        return new ResponseEntity<>(mapper.map(service.findById(id), TaskDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody CreateTaskDTO createTaskDTO) throws Exception{
        TaskDTO taskDTO = mapper.map(createTaskDTO,TaskDTO.class);
        taskDTO.setCreateDate(LocalDateTime.now());
        taskDTO.setTaskStatus(TaskStatus.ON_TIME);
        taskDTO.setFinished(false);
        //taskDTO.setEta(LocalDateTime.now().plusDays(2).plusHours(12));
        Task taskSaved = service.create(mapper.map(taskDTO, Task.class));
        return new ResponseEntity<>(mapper.map(taskSaved, TaskDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO) throws Exception{ //Primero se verifica si el id existe
        Task task = service.findById(taskDTO.getId());
        if(task == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + taskDTO.getId());
        }
        Task taskActualizado = service.update(mapper.map(taskDTO,Task.class)); //Se actualiza el task que se recibio en el body
        return new ResponseEntity<>(mapper.map(taskActualizado, TaskDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) throws Exception{
        Task task = service.findById(id);
        if(task == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Requerimientos especiales
    @GetMapping("/status/{status}")
    public ResponseEntity<TaskDTO> readByTaskStatus(@PathVariable("status") TaskStatus status) throws Exception{
        return new ResponseEntity<>(mapper.map(service.findAllByTaskStatus(status), TaskDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/markFinished/{id}")
    public ResponseEntity<Void> setTaskAsFinished(@PathVariable("id") Long id)throws Exception{
        if(service.findById(id) == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.setTaskAsFinished(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
