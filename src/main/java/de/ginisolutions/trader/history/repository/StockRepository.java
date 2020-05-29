package de.ginisolutions.trader.history.repository;

import de.ginisolutions.trader.history.domain.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Stock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
}
