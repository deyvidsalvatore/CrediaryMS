package com.deyvidsalvatore.crediary.mscreditappraiser.infra.clients;

import com.deyvidsalvatore.crediary.mscreditappraiser.domain.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "ssn")
    ResponseEntity<CustomerData> customerData(@RequestParam("ssn") String ssn);
}
