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
	
    private final UpdateController updateController;

    public LibraryBot(UpdateController updateController) {
    	this.updateController = updateController;
    }

    @PostConstruct
    public void init() {
        setBotUsername(env.getProperty("bot.name"));
        setBotToken(env.getProperty("bot.token"));
        updateController.registerBot(this);
    }
	
	@Override
	public void onUpdateReceived(Update update) {
			updateController.processUpdate(update);
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
	
    public void sendAnswer(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                logger.error(e);
            }
        }
    }
	
	public void setBotUsername(String botName) {
		this.botName = botName;
	}
	
	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}
}
