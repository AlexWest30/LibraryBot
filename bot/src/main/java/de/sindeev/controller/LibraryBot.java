package de.sindeev.controller;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class LibraryBot extends TelegramLongPollingBot {

	private String botName;
	private String botToken;
	
	private Logger logger = Logger.getLogger(LibraryBot.class);
	
	@Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        setBotUsername(env.getProperty("bot.name"));
        setBotToken(env.getProperty("bot.token"));
    }
	
	@Override
	public void onUpdateReceived(Update update) {
		var message = update.getMessage();
		System.out.println(message.getText());
		logger.debug(message.getText());
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(message.getChatId().toString());	
		sendMessage.setText(message.getText());
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
	
	public void setBotUsername(String botName) {
		this.botName = botName;
	}
	
	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}
}
