package org.sharpbubbels.TaskTrackerBot.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

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

        User user = update.getMessage().getFrom();

        SendMessage sendMessage = new SendMessage();
        long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        sendMessage.setChatId(String.valueOf(chatId));

        if (message.equals("/start")) {
            sendMessage.setText("Приветствуем, в TaskTracker!\nСюда вам будут приходить уведомления о ваших задачах!\nНе пропускайте важные дела \uD83D\uDE09");
            execute(sendMessage);
            sendMessage.setText("Список команд:\n /all - Показать весь список задач");

        } else if (update.getMessage().getText().equals("/all")) {
            sendMessage.setText("Список ваших задач:");
            execute(sendMessage);
            for (int i = 0; i < GettingUser.getUserBot().size(); i++) {
                if (user.getUserName().equals(GettingUser.getUserBot().get(i).getUsername())) {
                    sendMessage.setText(GettingUser.getUserBot().get(i).getDateTimeOfTask().toString());
                    execute(sendMessage);
                }
            }
        }
        while (true) {
            LocalDateTime current = LocalDateTime.now();
            for (int i = 0; i < GettingUser.getUserBot().size(); i++) {
                if (current.isAfter(GettingUser.getUserBot().get(i).getDateTimeOfTask())) {
                    sendMessage.setText(GettingUser.getUserBot().get(i).getDateTimeOfTask().toString());
                    execute(sendMessage);
                    GettingUser.getUserBot().remove(i);
                }
            }
        }
    }
}