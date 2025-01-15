package com.deyvidsalvatore.crediary.mscards.application;

import com.deyvidsalvatore.crediary.mscards.application.exceptions.CardNotFound;
import com.deyvidsalvatore.crediary.mscards.application.representation.request.CardRequest;
import com.deyvidsalvatore.crediary.mscards.domain.Card;
import com.deyvidsalvatore.crediary.mscards.domain.CardBrand;
import com.deyvidsalvatore.crediary.mscards.infra.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public Card save(CardRequest cardRequest) {
        validateCardBrand(cardRequest.getBrand());
        return this.cardRepository.save(
                new Card(
                    cardRequest.getName(),
                    cardRequest.getBrand(),
                    cardRequest.getIncome(),
                    cardRequest.getBasicLimit()
                )
        );
    }

    public List<Card> getCardsByIncomeLessThan(Long income) {
        return this.cardRepository.findByIncomeLessThanEqual(
                BigDecimal.valueOf(income)
        );
    }

    private void validateCardBrand(CardBrand cardBrand) {
        if (!EnumSet.allOf(CardBrand.class).contains(cardBrand)) {
            throw new CardNotFound("Card brand not found: " + cardBrand);
        }
    }
}
