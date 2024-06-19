package com.exam.messaging.config;

import com.exam.messaging.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static com.exam.messaging.constants.Constants.NOTIFICATION_TOPIC_GROUP_ID;

@Slf4j
@Configuration
public class ConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, NotificationDto> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, NOTIFICATION_TOPIC_GROUP_ID);

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        Map<String, Class<?>> classMap = new HashMap<>();
        typeMapper.setIdClassMapping(classMap);
        typeMapper.addTrustedPackages("*");

        JsonDeserializer<NotificationDto> jsonDeserializer = new JsonDeserializer<>(NotificationDto.class);
        jsonDeserializer.setTypeMapper(typeMapper);
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, NotificationDto> notificationKafkaListener() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        log.info("Notification concurrent consumer Kafka is created.");
        return factory;
    }
}
