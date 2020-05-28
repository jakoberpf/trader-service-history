package de.ginisolutions.trader.history.web.rest;

import de.ginisolutions.trader.history.domain.Market;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.repository.MarketRepository;
import de.ginisolutions.trader.history.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link de.ginisolutions.trader.history.domain.Market}.
 */
@RestController
@RequestMapping("/api")
public class MarketResource {

    private final Logger log = LoggerFactory.getLogger(MarketResource.class);

    private static final String ENTITY_NAME = "historyServiceMarket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MarketRepository marketRepository;

    public MarketResource(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    /**
     * {@code POST  /markets} : Create a new market.
     *
     * @param marketName the MARKET name of the market to create
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new market, or with status {@code 400 (Bad Request)} if the market has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/markets")
    public ResponseEntity<Market> createMarket(@Valid @RequestParam String marketName) throws URISyntaxException {
        log.debug("REST request to create Market : {}", marketName);
        Optional<MARKET> marketEnum;
        try {
            marketEnum = Optional.of(MARKET.valueOf(marketName));
        } catch (Exception e) {
            throw new BadRequestAlertException("The provided market name is not valid", ENTITY_NAME, "nameInvalid");
        }
        Optional<Market> result;
        if (marketRepository.existsByMarket(marketEnum.get())) {
            throw new BadRequestAlertException("Market already exists by market enum", ENTITY_NAME, "enumexists");
        } else {
            result = Optional.of(marketRepository.save(new Market(marketEnum.get())));
        }
        return ResponseEntity.created(new URI("/api/markets/" + result.get().getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.get().getId()))
            .body(result.get());
    }

    /**
     * {@code GET  /markets} : get all the markets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of markets in body.
     */
    @GetMapping("/markets")
    public List<Market> getAllMarkets() {
        log.debug("REST request to get all Markets");
        return marketRepository.findAll();
    }

    /**
     * {@code GET  /markets/:id} : get the "id" market.
     *
     * @param id the id of the market to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the market, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/markets/{id}")
    public ResponseEntity<Market> getMarket(@PathVariable String id) {
        log.debug("REST request to get Market : {}", id);
        Optional<Market> market = marketRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(market);
    }

    /**
     * {@code GET  /markets/:id} : get the "id" market.
     *
     * @param marketName the id of the market to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the market, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/markets/{marketName}")
    public ResponseEntity<Market> getMarketByMarket(@PathVariable String marketName) {
        log.debug("REST request to get Market : {}", marketName);
        Optional<Market> market = marketRepository.findById(marketName);
        return ResponseUtil.wrapOrNotFound(market);
    }

    /**
     * {@code DELETE  /markets/:id} : delete the "id" market.
     *
     * @param id the id of the market to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/markets/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable String id) {
        log.debug("REST request to delete Market : {}", id);

        marketRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
