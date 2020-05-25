package de.ginisolutions.trader.history.repository;

import de.ginisolutions.trader.history.domain.Tick;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Tick entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TickRepository extends MongoRepository<Tick, String> {
}
