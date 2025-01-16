package com.deyvidsalvatore.crediary.mscreditappraiser.infra.mqueue;

import com.deyvidsalvatore.crediary.mscreditappraiser.domain.template.RequestCardEmissionData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestEmissionCardPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissionCard;

    public RequestEmissionCardPublisher(RabbitTemplate rabbitTemplate, Queue queueEmissionCard) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueEmissionCard = queueEmissionCard;
    }

    public void requestCard(RequestCardEmissionData data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        this.rabbitTemplate.convertAndSend(queueEmissionCard.getName(), json);
    }

    private String convertIntoJson(RequestCardEmissionData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
