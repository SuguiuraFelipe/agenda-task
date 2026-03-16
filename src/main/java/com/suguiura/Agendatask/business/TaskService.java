package com.suguiura.Agendatask.business;

import com.suguiura.Agendatask.business.dto.TaskDTO;
import com.suguiura.Agendatask.business.mapper.TaskConverter;
import com.suguiura.Agendatask.infrastructure.entity.TaskEntity;
import com.suguiura.Agendatask.infrastructure.enums.NotificationStatusEnum;
import com.suguiura.Agendatask.infrastructure.repository.TaskRepository;
import com.suguiura.Agendatask.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;

    public TaskDTO saveTask(String token, TaskDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setCreateDate(LocalDateTime.now());
        dto.setNotificationStatusEnum(NotificationStatusEnum.PENDING);
        dto.setUserEmail(email);
        TaskEntity entity = taskConverter.toTaskEntity(dto);
        return taskConverter.totaskDTO(taskRepository.save(entity));
    }
}
