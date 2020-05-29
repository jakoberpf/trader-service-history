package de.ginisolutions.trader.history.web.rest;

import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.service.MarketService;
import de.ginisolutions.trader.history.web.rest.errors.BadRequestAlertException;
import de.ginisolutions.trader.history.service.dto.MarketDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    private final MarketService marketService;

    public MarketResource(MarketService marketService) {
        this.marketService = marketService;
    }

    /**
     * {@code POST  /markets} : Create a new market.
     *
     * @param marketName the market to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new marketDTO, or with status {@code 400 (Bad Request)} if the market has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/markets")
    public ResponseEntity<MarketDTO> createMarket(@Valid @RequestParam String marketName, @Valid @RequestParam String description) throws URISyntaxException {
        log.debug("REST request to create Market : {}", marketName);
        Optional<MARKET> marketEnum;
        try {
            marketEnum = Optional.of(MARKET.valueOf(marketName));
        } catch (Exception e) {
            throw new BadRequestAlertException("The provided market name is not valid", ENTITY_NAME, "nameInvalid");
        }
        MarketDTO result = marketService.create(marketEnum.get(), description);
        return ResponseEntity.created(new URI("/api/markets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /markets} : Updates an existing market.
     *
     * @param marketDTO the marketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated marketDTO,
     * or with status {@code 400 (Bad Request)} if the marketDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the marketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/markets")
    public ResponseEntity<MarketDTO> updateMarket(@Valid @RequestBody MarketDTO marketDTO) throws URISyntaxException {
        log.debug("REST request to update Market : {}", marketDTO);
        if (marketDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MarketDTO result = marketService.save(marketDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, marketDTO.getId()))
            .body(result);
    }

    /**
     * {@code GET  /markets} : get all the markets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of markets in body.
     */
    @GetMapping("/markets")
    public List<MarketDTO> getAllMarkets() {
        log.debug("REST request to get all Markets");
        return marketService.findAll();
    }

    /**
     * {@code GET  /markets/:id} : get the "id" market or "enum" market
     *
     * @param id the id or enum of the marketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the marketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/markets/{id}")
    public ResponseEntity<MarketDTO> getMarket(@PathVariable String id) {
        Optional<MARKET> marketEnum = Optional.empty();
        Optional<MarketDTO> marketDTO = Optional.empty();
        try {
            marketEnum = Optional.of(MARKET.valueOf(id));
        } catch (IllegalArgumentException ex) {
            marketDTO = marketService.findOne(id);
        }
        if (marketEnum.isPresent()) {
            marketDTO = marketService.findOne(marketEnum.get());
        }
        log.debug("REST request to get Market : {}", id);
        return ResponseUtil.wrapOrNotFound(marketDTO);
    }

    /**
     * {@code DELETE  /markets/:id} : delete the "id" market.
     *
     * @param id the id of the marketDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/markets/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable String id) {
        log.debug("REST request to delete Market : {}", id);

        marketService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
