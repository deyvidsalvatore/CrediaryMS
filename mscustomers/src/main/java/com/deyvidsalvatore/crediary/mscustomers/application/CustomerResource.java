package com.deyvidsalvatore.crediary.mscustomers.application;

import com.deyvidsalvatore.crediary.mscustomers.application.representation.request.CustomerRequest;
import com.deyvidsalvatore.crediary.mscustomers.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customers")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String status() {
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<Customer> save(@Valid @RequestBody CustomerRequest customer) {
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
        return ResponseEntity.ok(this.customerService.getBySSN(ssn));
    }
}
