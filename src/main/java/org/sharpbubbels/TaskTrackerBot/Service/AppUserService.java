package org.sharpbubbels.TaskTrackerBot.Service;


import lombok.AllArgsConstructor;
import org.sharpbubbels.TaskTrackerBot.DTO.UserRequest;
import org.sharpbubbels.TaskTrackerBot.Repository.AppUserRepository;
import org.sharpbubbels.TaskTrackerBot.Repository.UserNotificationsRepository;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.sharpbubbels.TaskTrackerBot.model.UserNotifications;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@AllArgsConstructor
public class AppUserService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final AppUserRepository repository;

    private final UserNotificationsRepository notificationsRepository;

    public void addAppUser(UserRequest userRequest) {
        if (repository.findAll().size() < 1) {
            AppUser user = new AppUser();
            user.setUsername(userRequest.getUsername());
            user.setUserNotifications(new ArrayList<>());
            for (int i = 0; i < userRequest.getDataTimeOfTasks().size(); i++) {
                UserNotifications notification = new UserNotifications();
                notification.setUser(user);
                LocalDateTime dateTime = LocalDateTime.parse(userRequest.getDataTimeOfTasks().get(i), formatter);
                notification.setNotification(dateTime);
                user.getUserNotifications().add(notification);
                notificationsRepository.save(notification);
            }
            repository.save(user);
        } else {
            for (int i = 0; i < repository.findAll().size(); i++) {
                if (repository.findAll().get(i).getUsername().equals(userRequest.getUsername())) {
                    for (int j = 0; j < userRequest.getDataTimeOfTasks().size(); j++) {
                        UserNotifications notifications = new UserNotifications();
                        LocalDateTime dateTime = LocalDateTime.parse(userRequest.getDataTimeOfTasks().get(j), formatter);
                        notifications.setUser(repository.findAll().get(i));
                        notifications.setNotification(dateTime);
                        notificationsRepository.save(notifications);
                    }
                    return;
                }
            }
        }
    }

    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    public void setUserChatId(Long chatId, AppUser user) {
        List<AppUser> appUserList = repository.findAll();
        for (int i = 0; i < appUserList.size(); i++) {
            if (appUserList.get(i).getUsername().equals(user.getUsername())) {
                user.setUserChatId(chatId);
                repository.save(user);
            }
        }
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

}