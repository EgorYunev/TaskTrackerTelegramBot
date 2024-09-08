package org.sharpbubbels.TaskTrackerBot.Service;


import jakarta.transaction.Transactional;
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

    @Transactional
    public void addAppUser(UserRequest userRequest) {
        List<AppUser> users = repository.findAll();

        if (users.isEmpty()) {
            AppUser user = getAppUser(userRequest);
            repository.save(user);
        } else {
            for (int i = 0; i < users.size(); i++) {
                AppUser existingUser = users.get(i);

                if (existingUser.getUsername().equals(userRequest.getUsername())) {
                    for (int j = 0; j < userRequest.getDataTimeOfTasks().size(); j++) {
                        UserNotifications notification = new UserNotifications();
                        LocalDateTime dateTime = LocalDateTime.parse(userRequest.getDataTimeOfTasks().get(j), formatter);
                        notification.setUser(existingUser);
                        notification.setNotification(dateTime);
                        existingUser.getUserNotifications().add(notification);
                    }
                    repository.save(existingUser);
                    return;
                } else if (i == users.size() - 1) {
                    AppUser user = getAppUser(userRequest);
                    repository.save(user);
                }
            }
        }
    }


    private static AppUser getAppUser(UserRequest userRequest) {
        AppUser user = new AppUser();
        user.setUsername(userRequest.getUsername());
        List<UserNotifications> notifications = new ArrayList<>();
        for (int i = 0; i < userRequest.getDataTimeOfTasks().size(); i++) {
            UserNotifications notification = new UserNotifications();
            notification.setUser(user);
            LocalDateTime dateTime = LocalDateTime.parse(userRequest.getDataTimeOfTasks().get(i), formatter);
            notification.setNotification(dateTime);
            notifications.add(notification);
        }
        user.setUserNotifications(notifications);
        return user;
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