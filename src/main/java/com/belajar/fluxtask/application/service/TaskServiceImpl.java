/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.application.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.belajar.fluxtask.application.dto.TaskRequest;
import com.belajar.fluxtask.application.dto.TaskResponse;
import com.belajar.fluxtask.application.mapper.TaskMapper;
import com.belajar.fluxtask.domain.exception.TaskProcessingException;
import com.belajar.fluxtask.domain.model.Task;
import com.belajar.fluxtask.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author firman
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    // private final KafkaProducerService KafkaProducerService;

    @Value("${fluxtask.task.max-retries}")
    private int maxRetries;


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        // TODO: Add send task to Kafka

        Task task = taskMapper.toTask(taskRequest);
        Task savedTask = taskRepository.save(task);

        log.info("Task created and sent to Kafka: {}", savedTask.getId());
        return taskMapper.toTaskResponse(savedTask);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));
        return taskMapper.toTaskResponse(task); 
        
    }

    @Override
    @Transactional
    public void processTask(Task task) {
        try {
            log.info("Processing task: {}", task.getId());
            task.markInProgress();
            taskRepository.update(task);

            // Simulate task processing 
            executeTaskLogic(task);

            task.markCompleted();
            taskRepository.update(task);

            log.info("task processed successfully: ", task.getId());
        } catch(Exception e) {
            log.error("Error processing task: {}", task.getId(), e);
            handleTaskFailure(task);
        }
    }

    @Override
    public void handleTaskFailure(Task task) {
        task.markFailed();
        task.incrementRetryCount();
        taskRepository.update(task);
        
        if(task.canRetry(maxRetries)) {
            log.info("retrying task: {} (current total attempt: {})", task.getId(), task.getRetryCount());
            
            // TODO: add send to kafka
        } else {
            log.error("Task {} failed after attempts: {}", task.getId(), maxRetries);
        }
    }

    private void executeTaskLogic(Task task) {
        // current logic is just for demonstration with simulating delay
        try {
            log.info("Executing task logic for: {}", task.getId());
            Thread.sleep(2000);

            // simulating fail using random to demonstrate retry mechanic
            if (Math.random() < 0.2) {
                throw new TaskProcessingException("Simulating random failure. . .");
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TaskProcessingException("task processing interrupted...");
        }
    }
}
