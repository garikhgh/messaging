package com.exam.messaging.messaging;


import com.exam.messaging.domain.NotificationEntity;
import com.exam.messaging.dto.NotificationDto;
import com.exam.messaging.mapper.MapperService;
import com.exam.messaging.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.exam.messaging.constants.Constants.NOTIFICATION_TOPIC;
import static com.exam.messaging.constants.Constants.NOTIFICATION_TOPIC_GROUP_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final NotificationService notificationService;
    private final MapperService mapperService;

    @KafkaListener(topics = NOTIFICATION_TOPIC, containerFactory = "notificationKafkaListener", groupId = NOTIFICATION_TOPIC_GROUP_ID)
    public void notificationListener(NotificationDto notificationDto) {

        log.info("Received new message {}", notificationDto.getNotification());
        NotificationEntity notificationEntity = mapperService.toNotificationEntity(notificationDto);
        notificationService.saveNotification(notificationEntity);

    }
}
