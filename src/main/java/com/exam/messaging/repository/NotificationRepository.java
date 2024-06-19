package com.exam.messaging.repository;

import com.exam.messaging.domain.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static com.exam.messaging.repository.NotificationRepository.notification;

@RepositoryRestResource(collectionResourceRel = notification, path = notification)
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    // PagingAndSortingRepository could be extended instead of JpaRepository
    String notification = "notification";

}
