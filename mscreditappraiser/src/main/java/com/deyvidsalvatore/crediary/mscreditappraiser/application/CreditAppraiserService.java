package com.deyvidsalvatore.crediary.mscreditappraiser.application;

import com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.CommunicationMicroserviceError;
import com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.CustomerDataNotFoundException;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal.AppraisalCustomerStatus;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal.ApprovedCard;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal.Card;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.customer.CustomerCard;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.customer.CustomerData;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.customer.CustomerSituation;
import com.deyvidsalvatore.crediary.mscreditappraiser.infra.clients.CardsResourceClient;
import com.deyvidsalvatore.crediary.mscreditappraiser.infra.clients.CustomerResourceClient;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class CreditAppraiserService {

    private final CustomerResourceClient customerClient;
    private final CardsResourceClient cardsClient;

    public CreditAppraiserService(CustomerResourceClient customerClient, CardsResourceClient cardsClient) {
        this.customerClient = customerClient;
        this.cardsClient = cardsClient;
    }

    public CustomerSituation getCustomerSituation(String ssn) {
        try {
            ResponseEntity<CustomerData> customerDataResponse = this.customerClient.customerData(ssn);
            ResponseEntity<List<CustomerCard>> cardsResponse = this.cardsClient.getCardsByCustomer(ssn);
            return CustomerSituation.builder()
                    .customer(customerDataResponse.getBody())
                    .cards(Objects.requireNonNull(cardsResponse.getBody()).isEmpty() ? cardsResponse.getBody() : null)
                    .build();
        } catch (FeignException.FeignClientException e) {
            if (HttpStatus.NOT_FOUND.value() == e.status()) {
                throw new CustomerDataNotFoundException();
            }
            throw new CommunicationMicroserviceError(e.getMessage(), e.status());
        }
    }

    public AppraisalCustomerStatus makeAvaliation(String ssn, Long income) {
        try {
            ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(ssn);
            ResponseEntity<List<Card>> cardsResponse = cardsClient.getCardsByIncome(income);

            List<Card> cards = cardsResponse.getBody();
            CustomerData customerData = customerDataResponse.getBody();
            int age = Objects.requireNonNull(customerData).getAge();

            List<ApprovedCard> approvedCards = Objects.requireNonNull(cards).stream()
                    .map(card -> createApprovedCard(card, age))
                    .toList();

            return new AppraisalCustomerStatus(approvedCards);

        } catch (FeignException.FeignClientException e) {
            handleFeignException(e);
            return null;
        }
    }

    private ApprovedCard createApprovedCard(Card card, int age) {
        BigDecimal basicLimit = card.getBasicLimit();
        BigDecimal ageFactor = BigDecimal.valueOf(age).divide(BigDecimal.valueOf(10), RoundingMode.UP);
        BigDecimal approvedLimit = ageFactor.multiply(basicLimit);

        ApprovedCard approvedCard = new ApprovedCard();
        approvedCard.setCard(card.getName());
        approvedCard.setBrand(card.getBrand());
        approvedCard.setAvailableLimit(approvedLimit);

        return approvedCard;
    }

    private void handleFeignException(FeignException.FeignClientException e) {
        if (HttpStatus.NOT_FOUND.value() == e.status()) {
            throw new CustomerDataNotFoundException();
        }
        throw new CommunicationMicroserviceError(e.getMessage(), e.status());
    }

}
