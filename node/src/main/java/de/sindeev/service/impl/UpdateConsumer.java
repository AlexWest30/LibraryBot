package de.sindeev.service.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import de.sindeev.RabbitMQ;
import de.sindeev.service.IAnswerProducer;
import de.sindeev.service.IUpdateConsumer;

@Service
public class UpdateConsumer implements IUpdateConsumer {

	private final IAnswerProducer answerProducer;
	
	private Logger logger = Logger.getLogger(UpdateConsumer.class);
	
	public UpdateConsumer(IAnswerProducer answerProducer) {
		this.answerProducer = answerProducer;
	}
	
	@Override
	@RabbitListener(queues = RabbitMQ.UPDATE_MESSAGE)
	public void consumeUpdate(Update update) {
		logger.debug("Node: Command is received");
		
		var message = update.getMessage();
		var sendMessage = new SendMessage();
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setText("Node: Command is received");
		answerProducer.produceAnswer(sendMessage);
	}
}
