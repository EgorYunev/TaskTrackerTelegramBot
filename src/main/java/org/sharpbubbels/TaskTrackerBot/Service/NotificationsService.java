package org.sharpbubbels.TaskTrackerBot.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sharpbubbels.TaskTrackerBot.Repository.UserNotificationsRepository;
import org.sharpbubbels.TaskTrackerBot.model.UserNotifications;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationsService {
    private UserNotificationsRepository repository;

    @Transactional
    public void deleteNotification(Long id) {
        repository.deleteById(id);
    }
}
