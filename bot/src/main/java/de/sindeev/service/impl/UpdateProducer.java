package de.sindeev.service.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import de.sindeev.controller.UpdateController;
import de.sindeev.service.IUpdateProducer;

@Service
public class UpdateProducer implements IUpdateProducer {

	private Logger logger = Logger.getLogger(UpdateController.class);
	
    private final RabbitTemplate rabbitTemplate;

    public UpdateProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	@Override
	public void produce(Update update, String queue) {
        logger.debug(update.getMessage().getText());
        rabbitTemplate.convertAndSend(queue, update);
	}
}
