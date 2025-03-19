/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.application.mapper;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Component;
import com.belajar.fluxtask.application.dto.TaskRequest;
import com.belajar.fluxtask.application.dto.TaskResponse;
import com.belajar.fluxtask.domain.model.Task;

/**
 *
 * @author firman
 */

@Component
public class TaskMapper {
    public Task toTask(TaskRequest taskRequest) {
        return Task.builder()
        .id(UUID.randomUUID())
        .payload(taskRequest.getPayload())
        .status(Task.TaskStatus.PENDING)
        .retryCount(0)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
    }

    public TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
        .id(task.getId())
        .payload(task.getPayload())
        .status(task.getStatus())
        .retryCount(task.getRetryCount())
        .createdAt(task.getCreatedAt())
        .updatedAt(task.getUpdatedAt())
        .build();
    }
}


