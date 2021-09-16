package com.krab.integration.messaging;

import com.krab.avro.InputMessageEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/api")
public class ConsumerRestController {

    private final ConsumerGateway gateway;

    public ConsumerRestController(ConsumerGateway gateway){
        this.gateway = gateway;
    }

    @GetMapping("/test1/{name}")
    public String testPrepareInputData2(@PathVariable("name") String name){
        return gateway.process2(name);
    }

    @GetMapping("/test2/avro")
    public String testPrepareInputData3(){
        return gateway.process(InputMessageEvent
                .newBuilder()
                .setMessageId("1234567")
                .setSomeValue2(123.0)
                .setSomeValue(System.currentTimeMillis())
                .setSomeFlag(false)
                .build()
                .toString()).toString();
    }
}
