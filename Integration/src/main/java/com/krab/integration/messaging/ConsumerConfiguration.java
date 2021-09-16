package com.krab.integration.messaging;

import com.krab.avro.InputMessageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.integration.transformer.SimpleToAvroTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class ConsumerConfiguration {

    @Bean
    public MessageChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel replyChannel() {
        return new DirectChannel();
    }

    @Bean(name = "integration.gateway.finalOutbound")
    public MessageChannel transformedChannel() {
        return new QueueChannel();
    }

    @Bean
    @Transformer(inputChannel = "integration.gateway.transform",
            outputChannel = "integration.gateway.transformed")
    public AbstractTransformer transformer()
    {
        return new ObjectToStringTransformer();
    }

    @Bean
    @Transformer(inputChannel = "integration.gateway.transformToAvro",
            outputChannel = "integration.gateway.transformedAvro")
    public AbstractTransformer avroTransformer()
    {
        return new JsonToObjectTransformer(InputMessageEvent.class);
    }
}
