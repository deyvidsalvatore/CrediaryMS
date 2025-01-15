package com.deyvidsalvatore.crediary.mscards.application;

import com.deyvidsalvatore.crediary.mscards.application.representation.request.CardRequest;
import com.deyvidsalvatore.crediary.mscards.domain.Card;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("cards")
public class CardResource {

    private final CardService cardService;

    public CardResource(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public String status() {
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<Card> save(@Valid @RequestBody CardRequest request) {
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("income={income}")
                .buildAndExpand(request.getIncome())
                .toUri();
        return ResponseEntity.created(headerLocation).body(this.cardService.save(request));
    }

}
