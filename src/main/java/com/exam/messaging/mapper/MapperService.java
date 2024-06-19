package com.exam.messaging.mapper;


import com.exam.messaging.domain.NotificationEntity;
import com.exam.messaging.dto.NotificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MapperService {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    NotificationEntity toNotificationEntity(NotificationDto notificationDto);

}
