package org.sharpbubbels.TaskTrackerBot.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


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

        SendMessage sendMessage = new SendMessage();
        long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        sendMessage.setChatId(String.valueOf(chatId));


        if (message.equals("/start")) {
            sendMessage.setText("Приветствуем, в TaskTracker!\nСюда вам будут приходить уведомления о ваших задачах!\nНе пропускайте важные дела \uD83D\uDE09");
            execute(sendMessage);

        } else {
            sendMessage.setText("Список ваших тасков:");
            execute(sendMessage);
            for (int i = 0; i < GettingUser.getUserBot().size(); i++) {
                sendMessage.setText(GettingUser.getUserBot().get(i).getDateTimeOfTask().toString());
                execute(sendMessage);
            }
        }
    }
}