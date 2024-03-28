package de.sindeev.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import de.sindeev.RabbitMQ;
import de.sindeev.service.IAnswerProducer;

@Service
public class AnswerProducer implements IAnswerProducer {
	
    private final RabbitTemplate rabbitTemplate;

    public AnswerProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	@Override
	public void produceAnswer(SendMessage message) {
		rabbitTemplate.convertAndSend(RabbitMQ.ANSWER_MESSAGE, message);
	}
}
