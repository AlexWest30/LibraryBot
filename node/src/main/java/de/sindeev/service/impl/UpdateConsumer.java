package de.sindeev.service.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import de.sindeev.RabbitMQ;
import de.sindeev.service.IUpdateConsumer;
import de.sindeev.service.IUpdateHandler;

@Service
public class UpdateConsumer implements IUpdateConsumer {

	private final IUpdateHandler updateHandler;
	
	private Logger logger = Logger.getLogger(UpdateConsumer.class);
	
	public UpdateConsumer(IUpdateHandler updateHandler) {
		this.updateHandler = updateHandler;
	}
	
	@Override
	@RabbitListener(queues = RabbitMQ.UPDATE_MESSAGE)
	public void consumeUpdate(Update update) {
		logger.debug("Node: Command is received");
		
		updateHandler.handleUpdate(update);
	}
}
