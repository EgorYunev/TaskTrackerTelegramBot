package org.sharpbubbels.TaskTrackerBot;

import org.sharpbubbels.TaskTrackerBot.controller.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TaskTrackerBotApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(TaskTrackerBotApplication.class);
		TelegramBotsApi telegramBotsApi= new TelegramBotsApi(DefaultBotSession.class);
		telegramBotsApi.registerBot(new TelegramBot());
	}
}