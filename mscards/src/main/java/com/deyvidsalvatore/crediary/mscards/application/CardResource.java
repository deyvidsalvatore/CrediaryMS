package com.deyvidsalvatore.crediary.mscards.application;

import com.deyvidsalvatore.crediary.mscards.application.representation.request.CardRequest;
import com.deyvidsalvatore.crediary.mscards.application.representation.response.CustomerCardResponse;
import com.deyvidsalvatore.crediary.mscards.domain.Card;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cards")
@Slf4j
public class CardResource {

    private final CardService cardService;
    private final CustomerCardService customerCardService;

    public CardResource(CardService cardService, CustomerCardService customerCardService) {
        this.cardService = cardService;
        this.customerCardService = customerCardService;
    }

    @GetMapping
    public String status() {
        log.info("mscards ::: status ~> Getting Status");
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<Card> save(@Valid @RequestBody CardRequest request) {
        log.info("mscards ::: save ~> Saving a new card");
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("income={income}")
                .buildAndExpand(request.getIncome())
                .toUri();
        return ResponseEntity.created(headerLocation).body(this.cardService.save(request));
    }

    @GetMapping(params = "income")
    public ResponseEntity<?> getCardsByIncome(@RequestParam("income") Long income) {
        log.info("mscards ::: getCardsByIncome ~> Getting Cards By Income with {}", income);
        var result = this.cardService.getCardsByIncomeLessThan(income);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(params = "ssn")
    public ResponseEntity<?> getCardsByCustomer(@RequestParam("ssn") String ssn) {
        log.info("mscards ::: getCardsByCustomers ~> Getting Cards By Customer with as SSN {}", ssn);
        List<CustomerCardResponse> resultList = this.customerCardService.getCardsBySsn(ssn)
                .stream()
                .map(CustomerCardResponse::fromModel)
                .toList();
        return ResponseEntity.ok(resultList);
    }

}
