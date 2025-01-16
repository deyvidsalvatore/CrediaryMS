package com.deyvidsalvatore.crediary.mscreditappraiser.infra.clients;

import com.deyvidsalvatore.crediary.mscreditappraiser.domain.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {

    @GetMapping(params = "ssn")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("ssn") String ssn);
}
