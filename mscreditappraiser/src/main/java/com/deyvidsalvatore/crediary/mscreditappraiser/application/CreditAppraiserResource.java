package com.deyvidsalvatore.crediary.mscreditappraiser.application;

import com.deyvidsalvatore.crediary.mscreditappraiser.domain.CustomerSituation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-appraiser")
public class CreditAppraiserResource {

    private final CreditAppraiserService creditAppraiserService;

    public CreditAppraiserResource(CreditAppraiserService creditAppraiserService) {
        this.creditAppraiserService = creditAppraiserService;
    }

    @GetMapping
    public String status() {
        return "Ok";
    }

    @GetMapping(value = "situation", params = "ssn")
    public ResponseEntity<CustomerSituation> getCustomerSituation(@RequestParam("ssn") String ssn) {
        CustomerSituation customerSituation = this.creditAppraiserService.getCustomerSituation(ssn);
        return ResponseEntity.ok(customerSituation);
    }
}
