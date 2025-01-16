package com.deyvidsalvatore.crediary.mscreditappraiser.application;

import com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal.AppraisalCustomerStatus;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.appraisal.AppraisalData;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.customer.CustomerSituation;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.template.ProtocolCardRequest;
import com.deyvidsalvatore.crediary.mscreditappraiser.domain.template.RequestCardEmissionData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<AppraisalCustomerStatus> appraise(@RequestBody AppraisalData appraisal) {
        return ResponseEntity.ok(
                this.creditAppraiserService.makeAvaliation(appraisal.getSsn(), appraisal.getIncome())
        );
    }

    @PostMapping("request-card")
    public ResponseEntity<ProtocolCardRequest> requestCard(@RequestBody RequestCardEmissionData data) {
        try {
            return ResponseEntity.ok(
                    this.creditAppraiserService.requestCardEmission(data)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
