/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.infrastructure.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.belajar.fluxtask.application.service.TaskService;
import com.belajar.fluxtask.domain.model.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author firman
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final TaskService taskService;
    
    @KafkaListener(topics = "${fluxtask.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeTaskFromKafka(Task task) {
        log.info("Received task from Kafka: {}", task.getId());
        
        try {
            taskService.processTask(task);
        } catch (Exception e) {
            log.error("Error processing task from Kafka: {}", task.getId(), e);
            taskService.handleTaskFailure(task);
        }
    }
}
