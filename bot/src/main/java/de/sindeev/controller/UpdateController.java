package de.sindeev.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import de.sindeev.RabbitMQ;
import de.sindeev.service.impl.UpdateProducer;

@Component
public class UpdateController {

	private Logger logger = Logger.getLogger(UpdateController.class);
	
	private LibraryBot bot;
	
	private final UpdateProducer updateProducer;
	
    public UpdateController(UpdateProducer updateProducer) {
		this.updateProducer = updateProducer;
    }
	
    public void registerBot(LibraryBot bot) {
        this.bot = bot;
    }
    
    public void processUpdate(Update update) {
    	if (update != null) {
            if (update.hasMessage()) {
                var message = update.getMessage();
                if (message.hasText()) {
                    updateProducer.produce(update, RabbitMQ.UPDATE_MESSAGE);
                    setCommandIsReceivedView(update);
                }
            } else {
                logger.error("Unsupported message type is received: " + update);
                setUnsupportedCommandView(update);
            }
    	}
    }
    
    private void setUnsupportedCommandView(Update update) {
        var sendMessage = generateSendMessage(update,
                "This command is not supported.");
        bot.sendAnswer(sendMessage);
    }

    private void setCommandIsReceivedView(Update update) {
        var sendMessage = generateSendMessage(update, "Your command was received.");
        bot.sendAnswer(sendMessage);
    }
    
    public SendMessage generateSendMessage(Update update, String text) {
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        return sendMessage;
    }
    
}
