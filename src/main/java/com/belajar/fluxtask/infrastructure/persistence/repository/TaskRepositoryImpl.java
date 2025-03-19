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
import com.belajar.fluxtask.infrastructure.cache.RedisTaskCache;
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
    private final RedisTaskCache redisTaskCache;
    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = TaskEntity.fromDomain(task);
        TaskEntity savedTaskEntity = taskJpaRepository.save(taskEntity);
        Task savedTaskDomain = savedTaskEntity.toDomain();
        redisTaskCache.cacheTask(savedTaskDomain);

        return savedTaskDomain;
    }

    @Override
    public Optional<Task> findById(UUID id) {
     Optional<Task> cachedTask = redisTaskCache.getTask(id);
     if (cachedTask.isPresent()) {
          return cachedTask;
     }
     
     return taskJpaRepository.findById(id)
            .map(TaskEntity::toDomain)
            .map(task -> {
               redisTaskCache.cacheTask(task);
               return task;
            });
    }

    @Override
    public void update(Task task) {
         TaskEntity taskEntity = TaskEntity.fromDomain(task);
         taskJpaRepository.save(taskEntity);

         redisTaskCache.cacheTask(task);
    }

}
