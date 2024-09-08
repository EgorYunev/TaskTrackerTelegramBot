package org.sharpbubbels.TaskTrackerBot.notifications;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sharpbubbels.TaskTrackerBot.Service.AppUserService;
import org.sharpbubbels.TaskTrackerBot.Service.NotificationsService;
import org.sharpbubbels.TaskTrackerBot.bot.TelegramBot;
import org.sharpbubbels.TaskTrackerBot.model.AppUser;
import org.sharpbubbels.TaskTrackerBot.model.UserNotifications;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Component
@AllArgsConstructor
@EnableScheduling
public class Notifications {

    private final AppUserService service;
    private final NotificationsService notificationsService;

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void processNotifications() throws TelegramApiException {
        LocalDateTime current = LocalDateTime.now();
        List<AppUser> list = service.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            for (Iterator<UserNotifications> iterator = list.get(i).getUserNotifications().iterator(); iterator.hasNext();) {
                UserNotifications notification = iterator.next();
                if (current.isAfter(notification.getNotification()) && list.get(i).getUserChatId() != null) {
                    TelegramBot bot = new TelegramBot(service);
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(list.get(i).getUserChatId());
                    sendMessage.setText(notification.getNotification().toString());
                    bot.execute(sendMessage);
                    iterator.remove();
                    notificationsService.deleteNotification(notification.getId());
                }
            }
        }
    }
}