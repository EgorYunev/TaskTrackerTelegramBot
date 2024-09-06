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
            LocalDateTime current = LocalDateTime.now();
            for (AppUser user : service.getAllUsers()) {
                for (UserNotifications notification : user.getUserNotifications()) {
                    if (current.isAfter(notification.getNotification()) && user.getUserChatId() != null) {
                        TelegramBot bot = new TelegramBot(service);
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(user.getUserChatId());
                        sendMessage.setText(notification.getNotification().toString());
                        bot.execute(sendMessage);
                        notificationsService.deleteNotification(notification.getId());
                    }
                }
            }
            Thread.sleep(500);
        }
    }
}
