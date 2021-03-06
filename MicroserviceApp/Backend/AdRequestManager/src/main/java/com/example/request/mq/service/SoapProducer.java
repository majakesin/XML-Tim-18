package com.example.request.mq.service;

import com.example.request.mq.dto.ReservationMQ;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SoapProducer {
	@Autowired
	private Gson gson;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${rabbitmq.exchange.soap}")
	private String exchange;

	@Value("${rabbitmq.routingkey.soap}")
	private String routingKey;

	public void sendSoap(ReservationMQ msg) {
		amqpTemplate.convertAndSend(exchange, routingKey, gson.toJson(msg));
	}

}
