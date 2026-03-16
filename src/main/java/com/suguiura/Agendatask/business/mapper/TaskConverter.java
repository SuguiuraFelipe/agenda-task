package com.suguiura.Agendatask.business.mapper;

import com.suguiura.Agendatask.business.dto.TaskDTO;
import com.suguiura.Agendatask.infrastructure.entity.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskConverter {

    TaskEntity toTaskEntity(TaskDTO dto);
    TaskDTO totaskDTO(TaskEntity entity);
    List<TaskEntity> toTaskListEntity(List<TaskDTO> dtos);
    List<TaskDTO> toTaskListDTO(List<TaskEntity> entities);
}
