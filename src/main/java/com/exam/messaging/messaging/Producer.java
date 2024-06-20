package com.exam.messaging.messaging;

import com.exam.messaging.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static com.exam.messaging.constants.Constants.NOTIFICATION_TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;


    public void sendNotification(NotificationDto notificationDto) {
        kafkaTemplate.setProducerListener(new ProducerListener<String, Serializable>() {
            @Override
            public void onSuccess(ProducerRecord<String, Serializable> producerRecord, RecordMetadata recordMetadata) {
                // here could be other handling approach to act on success
                log.info("Message is sent with Key {}", producerRecord.value());
            }

            @Override
            public void onError(ProducerRecord<String, Serializable> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                // here could be other handling approach to act on error
                log.error("Message with key {} is not send", producerRecord.value());
            }
        });
        kafkaTemplate.send(NOTIFICATION_TOPIC, notificationDto);
    }


}
