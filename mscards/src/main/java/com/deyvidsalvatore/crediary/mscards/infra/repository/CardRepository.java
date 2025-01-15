package com.deyvidsalvatore.crediary.mscards.infra.repository;

import com.deyvidsalvatore.crediary.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByIncomeLessThanEqual(BigDecimal income);
}
