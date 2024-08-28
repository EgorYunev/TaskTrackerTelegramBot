package org.sharpbubbels.TaskTrackerBot.bot;

import lombok.SneakyThrows;
import org.sharpbubbels.TaskTrackerBot.Service.AppUserService;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private AppUserService service;

    @Autowired
    public TelegramBot(AppUserService service) {
        this.service = service;
    }

    @Override
    public String getBotUsername() {
        return "NotificationsTaskTrackerBot";
    }

    @Override
    public String getBotToken() {
        return "7022417318:AAFcUQsCC0u6T4qAoDnEoPJYKKZOSjisIEI";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        User user = msg.getFrom();
        Long chatId = msg.getChatId();
        String message = msg.getText();

        AppUser appUser = new AppUser();
        appUser.setUserChatId(chatId);
        appUser.setUsername(user.getUserName());

        List<AppUser> appUsers = service.getAllUsers();
        for (AppUser u : appUsers) {
            if (u.getUsername().equals(user.getUserName())) {
                u.setUserChatId(chatId);
                service.setUserChatId(chatId, u);
                break;
            }
        }

        service.setUserChatId(chatId, appUser);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (message.equals("/start")) {
            sendMessage.setText("Hello!");
            execute(sendMessage);
        } else if (message.equals("/all")) {
            for (int i = 0; i < service.getAllUsers().size(); i++) {
                if (service.getAllUsers().get(i).getUsername().equals(user.getUserName())) {
                    for (int j = 0; j < service.getAllUsers().get(i).getUserNotifications().size(); j++) {
                        sendMessage.setText(service.getAllUsers().get(i).getUserNotifications().get(j).getNotification().toString());
                        execute(sendMessage);
                    }
                    return;
                }
            }
        }

    }
}