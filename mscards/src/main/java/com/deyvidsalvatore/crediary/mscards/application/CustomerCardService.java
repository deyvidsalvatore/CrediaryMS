package com.deyvidsalvatore.crediary.mscards.application;

import com.deyvidsalvatore.crediary.mscards.domain.CustomerCard;
import com.deyvidsalvatore.crediary.mscards.infra.repository.CustomerCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCardService {

    private final CustomerCardRepository customerCardRepository;

    public CustomerCardService(CustomerCardRepository customerCardRepository) {
        this.customerCardRepository = customerCardRepository;
    }

    public List<CustomerCard> getCardsBySsn(String ssn) {
        return this.customerCardRepository.findBySsn(ssn);
    }
}
