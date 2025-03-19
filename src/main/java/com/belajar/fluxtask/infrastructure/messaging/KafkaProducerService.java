/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.infrastructure.messaging;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
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
public class KafkaProducerService {
    private final KafkaTemplate<String, Task> kafkaTemplate;

    @Value("${fluxtask.kafka.topic}")
    private String taskTopic;

    public void sendTaskToKafka(Task task){
        String key = task.getId().toString();
        log.info("Sending task to Kafka topic {}: {}", taskTopic, key);

        CompletableFuture<SendResult<String, Task>> future = kafkaTemplate.send(taskTopic, key, task);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Task sent successfully to topic {}: {}, partition: {}, offset: {}", 
                        taskTopic, key, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send task to Kafka: {}", key, ex);
            }
        });
    }
}
