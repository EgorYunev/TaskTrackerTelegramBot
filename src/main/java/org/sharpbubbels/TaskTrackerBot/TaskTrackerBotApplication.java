package org.sharpbubbels.TaskTrackerBot;

import org.sharpbubbels.TaskTrackerBot.bot.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TaskTrackerBotApplication {

	private final TelegramBot telegramBot;

	public TaskTrackerBotApplication(TelegramBot telegramBot) {
		this.telegramBot = telegramBot;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerBotApplication.class, args);
	}

	@Bean
	public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		telegramBotsApi.registerBot(telegramBot);  // Регистрация бота через Spring Bean
		return telegramBotsApi;
	}
}