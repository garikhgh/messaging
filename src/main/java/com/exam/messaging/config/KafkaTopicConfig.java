package com.exam.messaging.config;


import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

import static com.exam.messaging.constants.Constants.NOTIFICATION_TOPIC;
import static com.exam.messaging.constants.Constants.NOTIFICATION_TOPIC_PARTITIONS;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic notificationTopic() {
        return new NewTopic(NOTIFICATION_TOPIC, NOTIFICATION_TOPIC_PARTITIONS, (short) 1);
    }


}
