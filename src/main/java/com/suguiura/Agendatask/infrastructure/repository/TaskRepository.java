package com.suguiura.Agendatask.infrastructure.repository;

import com.suguiura.Agendatask.infrastructure.entity.TaskEntity;
import com.suguiura.Agendatask.infrastructure.enums.NotificationStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    List<TaskEntity> findByEventDateBetweenAndNotificationStatusEnum(LocalDateTime startDate, LocalDateTime endDate,
                                                                     NotificationStatusEnum notificationStatusEnum);

    List<TaskEntity> findByUserEmail(String email);
}
