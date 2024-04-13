package de.sindeev.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface IUpdateHandler {
	
	void handleUpdate(Update update);
}
