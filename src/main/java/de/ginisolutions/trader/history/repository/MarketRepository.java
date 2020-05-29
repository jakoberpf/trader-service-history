package de.ginisolutions.trader.history.repository;

import de.ginisolutions.trader.history.domain.Market;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Market entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarketRepository extends MongoRepository<Market, String> {

    Optional<Market> findByMarket(MARKET market);

    boolean existsByMarket(MARKET market);
}
