package com.suguiura.Agendatask.business.mapper;

import com.suguiura.Agendatask.business.dto.TaskDTO;
import com.suguiura.Agendatask.infrastructure.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskUpdateConverter {

    void taskUpdate(TaskDTO dto, @MappingTarget TaskEntity entity);
}
