package com.suguiura.Agendatask.business;

import com.suguiura.Agendatask.business.dto.TaskDTO;
import com.suguiura.Agendatask.business.mapper.TaskConverter;
import com.suguiura.Agendatask.business.mapper.TaskUpdateConverter;
import com.suguiura.Agendatask.infrastructure.entity.TaskEntity;
import com.suguiura.Agendatask.infrastructure.enums.NotificationStatusEnum;
import com.suguiura.Agendatask.infrastructure.exceptions.ResourceNotFoundException;
import com.suguiura.Agendatask.infrastructure.repository.TaskRepository;
import com.suguiura.Agendatask.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;
    private final TaskUpdateConverter taskUpdateConverter;

    public TaskDTO saveTask(String token, TaskDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setCreateDate(LocalDateTime.now());
        dto.setNotificationStatusEnum(NotificationStatusEnum.PENDING);
        dto.setUserEmail(email);
        TaskEntity entity = taskConverter.toTaskEntity(dto);
        return taskConverter.totaskDTO(taskRepository.save(entity));
    }

    public List<TaskDTO> searchTaskDate(LocalDateTime startDate, LocalDateTime endDate) {
        return taskConverter.toTaskListDTO(taskRepository.findByEventDateBetweenAndNotificationStatusEnum(startDate, endDate,
                NotificationStatusEnum.PENDING));
    }

    public List<TaskDTO> searchTaskEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TaskEntity> taskList = taskRepository.findByUserEmail(email);
        return taskConverter.toTaskListDTO(taskList);
    }

    public void deleteTaskById(String id){
        try{
            taskRepository.deleteById(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id + e.getCause());
        }
    }

    public TaskDTO changeStatus(NotificationStatusEnum status, String id){
        try{
            TaskEntity entity = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setNotificationStatusEnum(status);
            return taskConverter.totaskDTO(taskRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TaskDTO updateTask(TaskDTO dto, String id){
        try{
            TaskEntity entity = taskRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));
            taskUpdateConverter.taskUpdate(dto, entity);
            return taskConverter.totaskDTO(taskRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }
}
