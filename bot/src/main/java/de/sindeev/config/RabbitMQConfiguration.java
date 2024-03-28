package de.sindeev.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.sindeev.RabbitMQ;

@Configuration
public class RabbitMQConfiguration {
	
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue updateMessageQueue() {
        return new Queue(RabbitMQ.UPDATE_MESSAGE);
    }

    @Bean
    public Queue answerMessageQueue() {
        return new Queue(RabbitMQ.ANSWER_MESSAGE);
    }
}
