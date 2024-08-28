package org.sharpbubbels.TaskTrackerBot.Repository;

import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
