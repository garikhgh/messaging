package com.exam.messaging.services;

import com.exam.messaging.domain.NotificationEntity;
import com.exam.messaging.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;


    @Test
    void saveNotification() {
        NotificationEntity notification = new NotificationEntity();
        notification.setNotification("test notification");
        // testing notificationService saveNotification method
        notificationService.saveNotification(notification);
        // testing notificationService the getNotificationCount
        long count = notificationService.geNotificationCount();
        assertEquals(1, count);
    }

}