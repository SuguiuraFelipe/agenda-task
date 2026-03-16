package com.suguiura.Agendatask.controller;

import com.suguiura.Agendatask.business.TaskService;
import com.suguiura.Agendatask.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(taskService.saveTask(token ,dto));
    }

    @GetMapping("/events")
    public ResponseEntity<List<TaskDTO>> searchTaskListPerPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate){
        return ResponseEntity.ok(taskService.searchTaskDate(startDate, endDate));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> searchTaskByEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(taskService.searchTaskEmail(token));
    }
}
