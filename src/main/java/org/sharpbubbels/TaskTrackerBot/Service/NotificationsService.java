package org.sharpbubbels.TaskTrackerBot.Service;

import lombok.AllArgsConstructor;
import org.sharpbubbels.TaskTrackerBot.Repository.UserNotificationsRepository;
import org.sharpbubbels.TaskTrackerBot.model.UserNotifications;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationsService {
    private UserNotificationsRepository repository;

    public void deleteNotification(UserNotifications notifications) {
        repository.delete(notifications);
    }
}
