package de.sindeev.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/* Passes updates to message broker */

public interface IUpdateProducer {
	
	void produce(Update update, String queue);
}
