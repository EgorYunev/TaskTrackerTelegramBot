package org.sharpbubbels.TaskTrackerBot;

import lombok.Getter;
import lombok.SneakyThrows;
import org.sharpbubbels.TaskTrackerBot.controller.TelegramBot;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Notifications extends Thread {

    private static List<AppUser> appUserList = new ArrayList<>();

    public static List<AppUser> getAppUserList() {
        return appUserList;
    }

    @SneakyThrows
    @Override
    public void run() {
        SendMessage sendMessage = new SendMessage();
        while (true) {
            LocalDateTime current = LocalDateTime.now();
            for (int i = 0; i < appUserList.size(); i++) {
                if (appUserList.get(i).getDateTimeOfTask() != null && current.isAfter(appUserList.get(i).getDateTimeOfTask())) {
                    sendMessage.setChatId(appUserList.get(i).getUserChatId());
                    sendMessage.setText(appUserList.get(i).getDateTimeOfTask().toString());
                    TelegramBot bot = new TelegramBot();
                    bot.execute(sendMessage);
                    appUserList.get(i).setDateTimeOfTask(null);
                }
            }
        }
    }
}
