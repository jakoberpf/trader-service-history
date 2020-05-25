package de.ginisolutions.trader.history.repository;

import de.ginisolutions.trader.history.domain.Market;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Market entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarketRepository extends MongoRepository<Market, String> {
}
