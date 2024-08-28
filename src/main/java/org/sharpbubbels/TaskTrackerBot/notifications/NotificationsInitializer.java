package org.sharpbubbels.TaskTrackerBot.notifications;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationsInitializer {

    private final Notifications notifications;

    @Autowired
    public NotificationsInitializer(Notifications notifications) {
        this.notifications = notifications;
    }

    @PostConstruct
    public void startThread() {
        notifications.start();
    }
}