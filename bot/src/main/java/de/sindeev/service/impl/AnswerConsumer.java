package de.sindeev.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import de.sindeev.service.IAnswerConsumer;

@Service
public class AnswerConsumer implements IAnswerConsumer {

	@Override
	public void consume(SendMessage message) {

	}
}
