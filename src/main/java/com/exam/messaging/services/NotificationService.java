package com.exam.messaging.services;

import com.exam.messaging.domain.NotificationEntity;
import com.exam.messaging.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(NotificationEntity notification) {
        NotificationEntity savedNotification = notificationRepository.save(notification);
        log.info("New Notification is saved with {} id", notification.getId());
    }
    public long geNotificationCount() {
        return notificationRepository.count();
    }

}
