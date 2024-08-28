package org.sharpbubbels.TaskTrackerBot.Repository;

import org.sharpbubbels.TaskTrackerBot.model.UserNotifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationsRepository extends JpaRepository<UserNotifications, Long> {

}
