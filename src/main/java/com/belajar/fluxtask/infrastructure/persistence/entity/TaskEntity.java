/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import com.belajar.fluxtask.domain.model.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author firman
 */

@Entity
@Table(name = "tasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Task.TaskStatus status;

    @Column(name = "retry_count")
    private int retryCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static TaskEntity fromDomain(Task task) {
        return TaskEntity.builder()
            .id(task.getId())
            .payload(task.getPayload())
            .status(task.getStatus())
            .retryCount(task.getRetryCount())
            .createdAt(task.getCreatedAt())
            .updatedAt(task.getUpdatedAt())
            .build();
    }

    public Task toDomain() {
        return Task.builder()
            .id(this.id)
            .payload(this.payload)
            .status(this.status)
            .retryCount(this.retryCount)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }
}
