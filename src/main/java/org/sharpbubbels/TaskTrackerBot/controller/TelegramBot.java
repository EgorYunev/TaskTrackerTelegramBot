package org.sharpbubbels.TaskTrackerBot.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.sharpbubbels.TaskTrackerBot.Notifications;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

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
        for (int i = 0; i < Notifications.getAppUserList().size(); i++) {
            if (Notifications.getAppUserList().get(i).getUsername().equals(user.getUserName())) {
                Notifications.getAppUserList().get(i).setUserChatId(chatId);
            }
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));

        if (message.equals("/start")) {
            sendMessage.setText("Приветствуем, в TaskTracker!\nСюда вам будут приходить уведомления о ваших задачах!\nНе пропускайте важные дела \uD83D\uDE09");
            execute(sendMessage);
            sendMessage.setText("Список команд:\n /all - Показать весь список задач");

        } else if (update.getMessage().getText().equals("/all")) {
            sendMessage.setText("Список ваших задач:");
            execute(sendMessage);
        }
    }
}
