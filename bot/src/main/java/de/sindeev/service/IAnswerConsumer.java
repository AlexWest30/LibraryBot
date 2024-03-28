package de.sindeev.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/* Consumes answers from message broker */

public interface IAnswerConsumer {
	
	void consume(SendMessage message);
}
