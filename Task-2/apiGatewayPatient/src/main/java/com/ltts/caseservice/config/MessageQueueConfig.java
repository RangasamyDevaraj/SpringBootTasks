package com.ltts.caseservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MessageQueueConfig {

	public final static String EXCHANGE = "patient_exchange"; 
	
	public final static String CREATE_QUEUE = "create_patient_queue"; 
	public final static String CREATE_ROUTING_KEY = "create_patient_routingkey"; 
	
	public final static String UPDATE_QUEUE = "update_patient_queue"; 
	public final static String UPDATE_ROUTING_KEY = "update_patient_routingkey";  
	
	public final static String GET_QUEUE = "get_patient_queue"; 
	public final static String GET_ROUTING_KEY = "get_patient_routingkey";  
	
	public final static String DELETE_QUEUE = "delete_patient_queue"; 
	public final static String DELETE_ROUTING_KEY = "delete_patient_routingkey";  
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}
	
	@Bean
	public Queue createQueue(){
		return new Queue(CREATE_QUEUE);
	}

	@Bean
	public Binding createbinding(Queue createQueue,TopicExchange exchange)
	{
		return BindingBuilder.bind(createQueue).to(exchange).with(CREATE_ROUTING_KEY);
	}
	
	@Bean
	public Queue updateQueue(){
		return new Queue(UPDATE_QUEUE);
	}
	
	@Bean
	public Binding updateBinding(Queue updateQueue,TopicExchange exchange)
	{
		return BindingBuilder.bind(updateQueue).to(exchange).with(UPDATE_ROUTING_KEY);
	}
	
	@Bean
	public Queue getQueue()
	{
		return new Queue(GET_QUEUE);
	}
	
	@Bean
	public Binding getBinding(Queue getQueue,TopicExchange exchange)
	{
		return BindingBuilder.bind(getQueue).to(exchange).with(GET_ROUTING_KEY);
	}
	
	@Bean
	public Queue deleteQueue()
	{
		return QueueBuilder.durable(DELETE_QUEUE)
				.withArgument("x-dead-letter-exchange", "deadLetterExchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter").build();
	}
	
	@Bean
	public Binding deleteBinding(Queue deleteQueue,TopicExchange exchange)
	{
		return BindingBuilder.bind(deleteQueue).to(exchange).with(DELETE_ROUTING_KEY);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
	 @Bean
	 public DirectExchange deadLetterExchange() {
	        return new DirectExchange("deadLetterExchange");
	    }
	 @Bean
	 public Queue dlq() {
	        return QueueBuilder.durable("deadLetter.queue").build();
	    }
	 @Bean
	 public Binding DLQbinding() {
	        return BindingBuilder.bind(dlq())
	                .to(deadLetterExchange()).with("deadLetter");
	    }
}
