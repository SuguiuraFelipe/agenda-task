package com.suguiura.Agendatask.controller;

import com.suguiura.Agendatask.business.TaskService;
import com.suguiura.Agendatask.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(taskService.saveTask(token ,dto));
    }
}
