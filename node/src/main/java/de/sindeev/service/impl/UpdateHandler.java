package de.sindeev.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import de.sindeev.entity.IncomingUpdate;
import de.sindeev.entity.IncomingUpdateDAO;
import de.sindeev.service.IUpdateHandler;

@Service
public class UpdateHandler implements IUpdateHandler {

	private final IncomingUpdateDAO incomingUpdateDAO;
	private final AnswerProducer answerProducer;
	
	public UpdateHandler(IncomingUpdateDAO incomingUpdateDAO, AnswerProducer answerProducer) {
		this.incomingUpdateDAO = incomingUpdateDAO;
		this.answerProducer = answerProducer;
	}

	@Override
	public void handleUpdate(Update update) {
		// persisting 
		saveUpdate(update);

		var message = update.getMessage();
		var sendMessage = new SendMessage();
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setText("Node: Command is received");
		answerProducer.produceAnswer(sendMessage);
	}
	
	private void saveUpdate(Update update) {
		IncomingUpdate incomingUpdate = IncomingUpdate.builder().event(update).build();
		incomingUpdateDAO.save(incomingUpdate);
	}
}
