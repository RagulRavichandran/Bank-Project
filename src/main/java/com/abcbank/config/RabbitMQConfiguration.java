package com.abcbank.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	
	
    public static final String BillerSenderQueue="billReq.sender.queue";
	public static final String BillerReceiverQueue="billReq.receiver.queue";
	public static final String SENDERQUEUE="serviceReq.sender.queue";
	public static final String RECIVERQUEUE="serviceReq.receiver.queue";
	public static final String Exchange="exchange";
	public static final String  ROUTINGKEYBILLERSENDER="billReq.sender.routingkey";
	public static final String  ROUTINGKEYBILLERRECEIVER="billReq.receiver.routingkey";
	public static final String ROUTINGKEYSENDER="serviceReq.sender.routingkey";
	public static final String ROUTINGKEYRECEIVER="serviceReq.receiver.routingkey";
	
	
	@Bean
	Queue queueBillSender()
	{
		return new Queue(BillerSenderQueue,false);
	}
	
	
	@Bean
	Queue queueBillReceiver()
	{
		return new Queue(BillerReceiverQueue,false);
	}
	@Bean
	Queue queueSender()
	{
		return new Queue(SENDERQUEUE, false);
		
	}
	@Bean
	Queue queueReceiver()
	{
		return new Queue(RECIVERQUEUE, false);
		
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(Exchange);
	}
	
	@Bean
	Binding binding(Queue queueBillSender,DirectExchange exchange) {
		return BindingBuilder.bind(queueBillSender).to(exchange).with(ROUTINGKEYBILLERSENDER);
		
	}
	
	@Bean
	Binding binding1(Queue queueBillReceiver,DirectExchange exchange) {
		return BindingBuilder.bind(queueBillReceiver).to(exchange).with(ROUTINGKEYBILLERRECEIVER);
		
	}
	
	@Bean
	 Binding binding2(Queue queueSender,DirectExchange exchange )
	 {
		return BindingBuilder.bind(queueSender).to(exchange).with(ROUTINGKEYSENDER);
		 
	 }
	 @Bean
	 Binding binding3(Queue queueReceiver,DirectExchange exchange )
	 {
		return BindingBuilder.bind(queueReceiver).to(exchange).with(ROUTINGKEYRECEIVER);
		 
	 }
	
	@Bean
	public MessageConverter JsonMessageConverter()
	 {
		 return new Jackson2JsonMessageConverter();
	 }
	 @Bean
	 public  AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
	 {
		 final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		 rabbitTemplate.setMessageConverter(JsonMessageConverter());
		 return rabbitTemplate;
	 }
	

}
