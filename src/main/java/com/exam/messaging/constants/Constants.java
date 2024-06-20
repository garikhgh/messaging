package com.exam.messaging.constants;

import com.exam.messaging.exception.NotAllowedInstanceCreationException;

public class Constants {

    private Constants() {
        throw new NotAllowedInstanceCreationException("Not allowed to create an instance!");
    }

    public static final String NOTIFICATION_TOPIC = "notification";
    public static final int NOTIFICATION_TOPIC_PARTITIONS = 1;
    public static final String NOTIFICATION_TOPIC_GROUP_ID = "notificationGroupId";
}
