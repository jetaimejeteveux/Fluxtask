/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 *
 * @author firman
 */

@Configuration
public class KafkaConfig {
    @Value("${fluxtask.kafka.topic}")
    private String taskTopic;

    @Bean
    public NewTopic taskTopic() {
        return TopicBuilder.name(taskTopic)
        .partitions(3)
        .replicas(1)
        .build();

    }
}
