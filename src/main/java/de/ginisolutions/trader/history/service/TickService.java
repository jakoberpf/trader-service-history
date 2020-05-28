package de.ginisolutions.trader.history.service;

import de.ginisolutions.trader.history.domain.Tick;
import de.ginisolutions.trader.history.repository.TickRepository;
import de.ginisolutions.trader.history.service.dto.TickDTO;
import de.ginisolutions.trader.history.service.mapper.TickMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Tick}.
 */
@Service
public class TickService {

    private final Logger log = LoggerFactory.getLogger(TickService.class);

    private final TickRepository tickRepository;

    private final TickMapper tickMapper;

    public TickService(TickRepository tickRepository, TickMapper tickMapper) {
        this.tickRepository = tickRepository;
        this.tickMapper = tickMapper;
    }

    /**
     * Save a tick.
     *
     * @param tickDTO the entity to save.
     * @return the persisted entity.
     */
    public TickDTO save(TickDTO tickDTO) {
        log.debug("Request to save Tick : {}", tickDTO);
        Tick tick = tickMapper.toEntity(tickDTO);
        tick = tickRepository.save(tick);
        return tickMapper.toDto(tick);
    }

    /**
     * Get all the ticks.
     *
     * @return the list of entities.
     */
    public List<TickDTO> findAll() {
        log.debug("Request to get all Ticks");
        return tickRepository.findAll().stream()
            .map(tickMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tick by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<TickDTO> findOne(String id) {
        log.debug("Request to get Tick : {}", id);
        return tickRepository.findById(id)
            .map(tickMapper::toDto);
    }

    /**
     * Delete the tick by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Tick : {}", id);

        tickRepository.deleteById(id);
    }
}
