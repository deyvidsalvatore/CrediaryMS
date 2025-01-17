package com.deyvidsalvatore.crediary.mscustomers.application;

import com.deyvidsalvatore.crediary.mscustomers.application.representation.request.CustomerRequest;
import com.deyvidsalvatore.crediary.mscustomers.domain.Customer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customers")
@Slf4j
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String status() {
        log.info("mscustomers ::: status ~> Getting status");
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<Customer> save(@Valid @RequestBody CustomerRequest customer) {
        log.info("mscustomers ::: save ~> Saving a customer");
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("ssn={ssn}")
                .buildAndExpand(customer.getSsn())
                .toUri();

        return ResponseEntity.created(headerLocation)
                .body(this.customerService.save(customer));
    }

    @GetMapping(params = "ssn")
    public ResponseEntity<Customer> getBySsn(@RequestParam("ssn") String ssn) {
        log.info("mscustomers ::: getBySsn ~> Getting customer by SSN {}", ssn);
        return ResponseEntity.ok(this.customerService.getBySSN(ssn));
    }
}
