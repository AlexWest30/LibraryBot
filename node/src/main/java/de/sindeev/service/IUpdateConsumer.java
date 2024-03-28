package de.sindeev.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/* Consumes updates from message broker */

public interface IUpdateConsumer {

	void consumeUpdate(Update update);
}
