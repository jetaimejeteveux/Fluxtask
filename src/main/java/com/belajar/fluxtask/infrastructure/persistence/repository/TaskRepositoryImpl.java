/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import com.belajar.fluxtask.domain.model.Task;
import com.belajar.fluxtask.domain.repository.TaskRepository;
import com.belajar.fluxtask.infrastructure.persistence.entity.TaskEntity;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author firman
 */

@Component
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskJpaRepository taskJpaRepository;
    
    @Override
    public Task save(Task task) {
         // TODO: implement caching using redis

        TaskEntity taskEntity = TaskEntity.fromDomain(task);
        TaskEntity savedTaskEntity = taskJpaRepository.save(taskEntity);

        return savedTaskEntity.toDomain();
    }

    @Override
    public Optional<Task> findById(UUID id) {
         // TODO: implement caching using redis

       return taskJpaRepository.findById(id)
            .map(TaskEntity::toDomain);
    }

    @Override
    public void update(Task task) {
         // TODO: implement caching using redis

         TaskEntity taskEntity = TaskEntity.fromDomain(task);
         taskJpaRepository.save(taskEntity);
    }

}
