package com.exam.messaging.controllers;


import com.exam.messaging.dto.NotificationDto;
import com.exam.messaging.messaging.Producer;
import com.exam.messaging.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NotificationController {

    private final Producer producer;
    private final NotificationService notificationService;

    @PostMapping("/notification")
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        log.info("Storing new notification {}", notificationDto.toString());
        producer.sendNotification(notificationDto);
        log.info("Notification is saved {}", notificationDto.toString());
        return ResponseEntity.accepted().build();
    }
    // for testing purposes
    @GetMapping("/notification")
    public ResponseEntity<String> getAllNotifications() {
        long notificationCount = notificationService.geNotificationQount();
        return ResponseEntity.ok("Total notifications= " + notificationCount);
    }
}
