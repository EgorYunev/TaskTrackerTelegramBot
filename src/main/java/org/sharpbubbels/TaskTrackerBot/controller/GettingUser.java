package org.sharpbubbels.TaskTrackerBot.controller;

import lombok.AllArgsConstructor;
import org.sharpbubbels.TaskTrackerBot.DTO.UserRequest;
import org.sharpbubbels.TaskTrackerBot.Service.AppUserService;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class GettingUser {

    private AppUserService service;

    @PostMapping
    public void addUser(@RequestBody UserRequest userRequest) {
        service.addAppUser(userRequest);
    }

    @PutMapping
    public void setUserChatId(@RequestBody Long chatId, AppUser user) {
        service.setUserChatId(chatId, user);
    }

    @GetMapping
    public List<AppUser> getAllUsers() {
        return service.getAllUsers();
    }

    @DeleteMapping
    public void deleteUserById(Long id) {
        service.deleteUserById(id);
    }

}