package org.sharpbubbels.TaskTrackerBot.controller;

import org.sharpbubbels.TaskTrackerBot.DTO.UserRequest;
import org.sharpbubbels.TaskTrackerBot.Notifications;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GettingUser {

    @PostMapping
    public void getUser(@RequestBody UserRequest userRequest) {
        if (Notifications.getAppUserList().size() < 1) {
            AppUser user = new AppUser();
            user.setUsername(userRequest.getUsername());
            user.setDateTimeOfTask(userRequest.getDateTimeOfTask());
            Notifications.getAppUserList().add(user);
        }
        for (int i = 0; i < Notifications.getAppUserList().size(); i++) {
            if (Notifications.getAppUserList().get(i).getUsername().equals(userRequest.getUsername())) {
                Notifications.getAppUserList().get(i).setDateTimeOfTask(userRequest.getDateTimeOfTask());
                return;
            } else if (i >= Notifications.getAppUserList().size() - 1) {
                AppUser user = new AppUser();
                user.setUsername(userRequest.getUsername());
                user.setDateTimeOfTask(userRequest.getDateTimeOfTask());
                Notifications.getAppUserList().add(user);
            }
        }
    }

    @GetMapping
    public List<AppUser> getAppUsers() {
        return Notifications.getAppUserList();
    }

}