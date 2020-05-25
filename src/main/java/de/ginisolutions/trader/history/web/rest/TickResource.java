package de.ginisolutions.trader.history.web.rest;

import de.ginisolutions.trader.history.domain.Tick;
import de.ginisolutions.trader.history.repository.TickRepository;
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
 * REST controller for managing {@link de.ginisolutions.trader.history.domain.Tick}.
 */
@RestController
@RequestMapping("/api")
public class TickResource {

    private final Logger log = LoggerFactory.getLogger(TickResource.class);

    private static final String ENTITY_NAME = "historyServiceTick";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TickRepository tickRepository;

    public TickResource(TickRepository tickRepository) {
        this.tickRepository = tickRepository;
    }

    /**
     * {@code POST  /ticks} : Create a new tick.
     *
     * @param tick the tick to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tick, or with status {@code 400 (Bad Request)} if the tick has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ticks")
    public ResponseEntity<Tick> createTick(@Valid @RequestBody Tick tick) throws URISyntaxException {
        log.debug("REST request to save Tick : {}", tick);
        if (tick.getId() != null) {
            throw new BadRequestAlertException("A new tick cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tick result = tickRepository.save(tick);
        return ResponseEntity.created(new URI("/api/ticks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ticks} : Updates an existing tick.
     *
     * @param tick the tick to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tick,
     * or with status {@code 400 (Bad Request)} if the tick is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tick couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ticks")
    public ResponseEntity<Tick> updateTick(@Valid @RequestBody Tick tick) throws URISyntaxException {
        log.debug("REST request to update Tick : {}", tick);
        if (tick.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tick result = tickRepository.save(tick);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tick.getId()))
            .body(result);
    }

    /**
     * {@code GET  /ticks} : get all the ticks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ticks in body.
     */
    @GetMapping("/ticks")
    public List<Tick> getAllTicks() {
        log.debug("REST request to get all Ticks");
        return tickRepository.findAll();
    }

    /**
     * {@code GET  /ticks/:id} : get the "id" tick.
     *
     * @param id the id of the tick to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tick, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ticks/{id}")
    public ResponseEntity<Tick> getTick(@PathVariable String id) {
        log.debug("REST request to get Tick : {}", id);
        Optional<Tick> tick = tickRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tick);
    }

    /**
     * {@code DELETE  /ticks/:id} : delete the "id" tick.
     *
     * @param id the id of the tick to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ticks/{id}")
    public ResponseEntity<Void> deleteTick(@PathVariable String id) {
        log.debug("REST request to delete Tick : {}", id);

        tickRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
