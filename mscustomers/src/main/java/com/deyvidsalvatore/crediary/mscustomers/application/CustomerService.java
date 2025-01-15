package com.deyvidsalvatore.crediary.mscustomers.application;

import com.deyvidsalvatore.crediary.mscustomers.application.exceptions.CustomerDataIntegrationViolation;
import com.deyvidsalvatore.crediary.mscustomers.application.exceptions.CustomerNotFound;
import com.deyvidsalvatore.crediary.mscustomers.domain.Customer;
import com.deyvidsalvatore.crediary.mscustomers.application.representation.request.CustomerRequest;
import com.deyvidsalvatore.crediary.mscustomers.infra.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer save(CustomerRequest customer) {
        if (existsBySSN(customer.getSsn())) {
            throw new CustomerDataIntegrationViolation("Customer Duplication: Cannot use this SSN");
        }

        return this.customerRepository.save(
                new Customer(customer.getSsn(), customer.getName(), customer.getAge())
        );
    }

    public Customer getBySSN(String ssn) {
        return this.customerRepository.findBySsn(ssn)
                .orElseThrow(() -> new CustomerNotFound("Customer Not Found with this SSN"));
    }

    private boolean existsBySSN(String ssn) {
        return this.customerRepository.existsBySsn(ssn);
    }
}
