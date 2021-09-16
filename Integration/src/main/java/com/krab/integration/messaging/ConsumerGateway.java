package com.krab.integration.messaging;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ConsumerGateway {

     @Gateway(requestChannel = "integration.gateway.inject", replyChannel = "integration.gateway.finalOutbound", replyTimeout = 100000L)
     <T extends SpecificRecordBase> T process(String json);

     @Gateway(requestChannel = "integration.gateway.inject2",  replyChannel = "integration.gateway.finalOutbound")
     String process2(String json);
}
