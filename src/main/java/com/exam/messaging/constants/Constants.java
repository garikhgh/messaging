package com.exam.messaging.constants;

public class Constants {

    private Constants() {
        throw new RuntimeException("Not allowed to create instance!");
    }

    public static final String NOTIFICATION_TOPIC = "notification";
    public static final int NOTIFICATION_TOPIC_PARTITIONS = 1;
    public static final String NOTIFICATION_TOPIC_GROUP_ID = "notificationGroupId";
}
