package org.sharpbubbels.TaskTrackerBot.controller;

import org.sharpbubbels.TaskTrackerBot.DTO.UserRequest;
import org.sharpbubbels.TaskTrackerBot.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping
public class GettingUser {

    private static final ArrayList<User> users = new ArrayList<>();

    @PostMapping
    public void setUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setDateTimeOfTask(userRequest.getDateTimeOfTask());
        users.add(user);
        System.out.println("New user created");
    }

    @GetMapping
    public ArrayList<User> getUser() {
        System.out.println("Get запрос отправлен");
        return users;
    }

    public static ArrayList<User> getUserBot() {
        return users;
    }

}