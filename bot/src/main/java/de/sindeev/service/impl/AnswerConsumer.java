package de.sindeev.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import de.sindeev.RabbitMQ;
import de.sindeev.controller.UpdateController;
import de.sindeev.service.IAnswerConsumer;

@Service
public class AnswerConsumer implements IAnswerConsumer {

	private final UpdateController updateController;

    public AnswerConsumer(UpdateController updateController) {
    	this.updateController = updateController;
    }
	
	@Override
	@RabbitListener(queues = RabbitMQ.ANSWER_MESSAGE)
	public void consume(SendMessage message) {
        updateController.setView(message);
	}
}
