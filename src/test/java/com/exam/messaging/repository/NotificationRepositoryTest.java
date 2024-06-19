package com.exam.messaging.repository;

import com.exam.messaging.domain.NotificationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void notificationRepositoryTest() {
        NotificationEntity notification = new NotificationEntity();
        notification.setNotification("test notification");
        NotificationEntity save = notificationRepository.save(notification);
        assertEquals(save.getNotification(), notification.getNotification());
    }
}