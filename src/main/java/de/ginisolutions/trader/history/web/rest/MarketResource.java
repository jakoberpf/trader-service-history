package de.ginisolutions.trader.history.web.rest;

import de.ginisolutions.trader.history.domain.Market;
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
     * @param market the market to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new market, or with status {@code 400 (Bad Request)} if the market has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/markets")
    public ResponseEntity<Market> createMarket(@Valid @RequestBody Market market) throws URISyntaxException {
        log.debug("REST request to save Market : {}", market);
        if (market.getId() != null) {
            throw new BadRequestAlertException("A new market cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Market result = marketRepository.save(market);
        return ResponseEntity.created(new URI("/api/markets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /markets} : Updates an existing market.
     *
     * @param market the market to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated market,
     * or with status {@code 400 (Bad Request)} if the market is not valid,
     * or with status {@code 500 (Internal Server Error)} if the market couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/markets")
    public ResponseEntity<Market> updateMarket(@Valid @RequestBody Market market) throws URISyntaxException {
        log.debug("REST request to update Market : {}", market);
        if (market.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Market result = marketRepository.save(market);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, market.getId()))
            .body(result);
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
