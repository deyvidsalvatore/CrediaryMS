package com.deyvidsalvatore.crediary.mscreditappraiser.application;

import com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.CommunicationMicroserviceError;
import com.deyvidsalvatore.crediary.mscreditappraiser.application.exceptions.CustomerDataNotFoundException;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.CustomerCard;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.CustomerData;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.CustomerSituation;
import com.deyvidsalvatore.crediary.mscreditappraiser.infra.clients.CardsResourceClient;
import com.deyvidsalvatore.crediary.mscreditappraiser.infra.clients.CustomerResourceClient;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
                    .cards(cardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            if (HttpStatus.NOT_FOUND.value() == e.status()) {
                throw new CustomerDataNotFoundException();
            }
            throw new CommunicationMicroserviceError(e.getMessage(), e.status());
        }
    }
}
