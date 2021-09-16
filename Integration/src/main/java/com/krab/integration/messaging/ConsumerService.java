package com.krab.integration.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

    @ServiceActivator(inputChannel = "integration.gateway.inject", outputChannel = "integration.gateway.transformToAvro")
    public Message<?> insertAvroModel(Message<?> message) {
        System.out.println("#####geting json#####");
        System.out.println(message.getPayload());
        return message;
    }

    @ServiceActivator(inputChannel = "integration.gateway.transformedAvro", outputChannel = "integration.gateway.finalOutbound")
    public Message<?> getTransformedMessageToAvro(Message<?> message) {
        System.out.println("#####Transform executed#####");
        System.out.println(message.getPayload().getClass());
        return message;
    }

    @ServiceActivator(inputChannel = "integration.gateway.inject2", outputChannel = "integration.gateway.transform")
    public Message<?> insertMessage(Message<?> message) {
        MessageBuilder.fromMessage(message);
        return MessageBuilder.withPayload("Test payload from builder: " + message.getPayload()).build();
    }

    @ServiceActivator(inputChannel = "integration.gateway.transformed", outputChannel = "integration.gateway.finalOutbound")
    public Message<String> getTransformedMessage(Message<?> message) {
        return MessageBuilder
                .withPayload("Test payload from builder: " + message
                       .getPayload()).build();
    }
}
