/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.infrastructure.cache;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.belajar.fluxtask.domain.model.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author firman
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisTaskCache {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String TASK_KEY_PREFIX = "task:";
    private static final Duration CACHE_TTL = Duration.ofHours(24);
    
    public void cacheTask(Task task){
        try {
            String key = TASK_KEY_PREFIX + task.getId();
            redisTemplate.opsForValue().set(key, task, CACHE_TTL);
            log.debug("Task cached in Redis: {}", task.getId());
        } catch(Exception e){
            log.error("Error caching task in redis: {}", e.getMessage());
        }
    }

    public Optional<Task> getTask(UUID taskId){
        try {
            String key = TASK_KEY_PREFIX + taskId;
            Task task = (Task) redisTemplate.opsForValue().get(key);
            if (task != null){
                log.debug("Task retrieved from cache: {}", task);
                return Optional.of(task);
            }
            return Optional.empty();
        } catch(Exception e) {
            log.error("Error retrieving task: {}", taskId, e.getMessage());
            return Optional.empty();
        }
    }

    public void deleteTask(UUID taskId) {
        try {
            String key = TASK_KEY_PREFIX + taskId;
            redisTemplate.delete(key);
            log.debug("Task deleted from redis cache: {}", taskId);
        } catch(Exception e) {
            log.error("Failed to delete task: {}", taskId, e.getMessage());
        }
    }
}
