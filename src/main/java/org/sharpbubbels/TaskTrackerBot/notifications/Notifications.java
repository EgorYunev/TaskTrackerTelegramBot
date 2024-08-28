package org.sharpbubbels.TaskTrackerBot.notifications;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.sharpbubbels.TaskTrackerBot.Service.AppUserService;
import org.sharpbubbels.TaskTrackerBot.Service.NotificationsService;
import org.sharpbubbels.TaskTrackerBot.bot.TelegramBot;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.sharpbubbels.TaskTrackerBot.model.UserNotifications;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class Notifications extends Thread {

    private AppUserService service;

    private NotificationsService notificationsService;


    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            List<AppUser> users = service.getAllUsers();
            LocalDateTime current = LocalDateTime.now();
            for (int i = 0; i < users.size(); i++) {
                List<UserNotifications> notifications = users.get(i).getUserNotifications();
                for (int j = 0; j < notifications.size(); j++) {
                    if (current.isAfter(notifications.get(j).getNotification())) {
                        TelegramBot bot = new TelegramBot(service);
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(users.get(i).getUserChatId());
                        sendMessage.setText(notifications.get(j).getNotification().toString());
                        bot.execute(sendMessage);
                        notificationsService.deleteNotification(notifications.get(j));
                    }
                }
            }
            Thread.sleep(500);
        }
    }
}
