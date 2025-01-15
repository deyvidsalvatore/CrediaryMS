package com.deyvidsalvatore.crediary.mscards.infra.repository;

import com.deyvidsalvatore.crediary.mscards.domain.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {
    List<CustomerCard> findBySsn(String ssn);
}
