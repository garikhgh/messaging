package com.exam.messaging.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDto implements Serializable {
    private String notification;
}
