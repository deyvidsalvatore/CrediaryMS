package com.deyvidsalvatore.crediary.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queue.emission-cards}")
    private String emissionCardQueue;

    @Bean
    public Queue queueEmissionCard() {
        return new Queue(emissionCardQueue, true);
    }
}
