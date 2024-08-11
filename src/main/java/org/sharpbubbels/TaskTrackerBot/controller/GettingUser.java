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
        AppUser user = new AppUser();
        user.setUsername(userRequest.getUsername());
        user.setDateTimeOfTask(userRequest.getDateTimeOfTask());
        Notifications.getAppUserList().add(user);
    }

    @GetMapping
    public List<AppUser> getAppUsers() {
        return Notifications.getAppUserList();
    }

}