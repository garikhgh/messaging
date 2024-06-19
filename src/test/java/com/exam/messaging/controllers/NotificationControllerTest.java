package com.exam.messaging.controllers;

import com.exam.messaging.domain.NotificationEntity;
import com.exam.messaging.messaging.Consumer;
import com.exam.messaging.messaging.Producer;
import com.exam.messaging.services.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private Producer producer;

    @MockBean
    private Consumer consumer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createNotification() throws Exception {
        NotificationEntity notification = new NotificationEntity();
        notification.setNotification("test notification");

        mockMvc.perform(post("/api/v1/notification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(notification)))
                .andExpect(status().is2xxSuccessful());


    }

    @Test
    void getAllNotifications() throws Exception {
        NotificationEntity notification = new NotificationEntity();
        notification.setNotification("test notification");
        long count = 1;
        Mockito.when(notificationService.geNotificationCount()).thenReturn(count);

        mockMvc.perform(get("/api/v1/notification")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}