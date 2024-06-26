package com.exam.messaging.kafka;

import com.exam.messaging.domain.NotificationEntity;
import com.exam.messaging.dto.NotificationDto;
import com.exam.messaging.messaging.Consumer;
import com.exam.messaging.messaging.Producer;
import com.exam.messaging.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class MessagingTest {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private Producer producer;


    @Test
    void messagingTest() throws InterruptedException {
        NotificationDto notification = new NotificationDto();
        notification.setNotification("test notification");

        sleep(3000); // wait connection to be established
        producer.sendNotification(notification);
        sleep(4000);
        List<NotificationEntity> all = notificationRepository.findAll();

        assertEquals("test notification", all.get(0).getNotification());


    }

}
