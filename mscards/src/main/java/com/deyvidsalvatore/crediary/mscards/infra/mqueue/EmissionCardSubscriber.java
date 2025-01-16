package com.deyvidsalvatore.crediary.mscards.infra.mqueue;

import com.deyvidsalvatore.crediary.mscards.application.exceptions.CardNotFound;
import com.deyvidsalvatore.crediary.mscards.domain.Card;
import com.deyvidsalvatore.crediary.mscards.domain.CustomerCard;
import com.deyvidsalvatore.crediary.mscards.domain.RequestCardEmissionData;
import com.deyvidsalvatore.crediary.mscards.infra.repository.CardRepository;
import com.deyvidsalvatore.crediary.mscards.infra.repository.CustomerCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissionCardSubscriber {

    private final CardRepository cardRepository;
    private final CustomerCardRepository customerCardRepository;

    public EmissionCardSubscriber(CardRepository cardRepository, CustomerCardRepository customerCardRepository) {
        this.cardRepository = cardRepository;
        this.customerCardRepository = customerCardRepository;
    }

    @RabbitListener(queues = "${mq.queue.emission-cards}")
    public void receiveCardEmission(@Payload String payload) throws JsonProcessingException {
        var data = getRequestCardEmissionData(payload);
        var card = findCardById(data);
        CustomerCard customerCard = createCustomerCard(card, data);
        this.customerCardRepository.save(customerCard);
    }

    private static RequestCardEmissionData getRequestCardEmissionData(String payload) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.readValue(payload, RequestCardEmissionData.class);
    }

    private Card findCardById(RequestCardEmissionData data) {
        return this.cardRepository.findById(data.getCardId())
                .orElseThrow(() -> new CardNotFound("Card was not found with this ID!"));
    }

    private static CustomerCard createCustomerCard(Card card, RequestCardEmissionData data) {
        CustomerCard customerCard = new CustomerCard();
        customerCard.setCard(card);
        customerCard.setSsn(data.getSsn());
        customerCard.setLimit(data.getFreeLimit());
        return customerCard;
    }
}
