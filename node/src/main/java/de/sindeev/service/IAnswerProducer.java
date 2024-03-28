package de.sindeev.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/* Passes answers to message broker */

public interface IAnswerProducer {

	void produceAnswer(SendMessage message);
}
