package de.ginisolutions.trader.history.service;

import de.ginisolutions.trader.history.domain.Market;
import de.ginisolutions.trader.common.enumeration.MARKET;
import de.ginisolutions.trader.history.repository.MarketRepository;
import de.ginisolutions.trader.history.service.dto.MarketDTO;
import de.ginisolutions.trader.history.service.mapper.MarketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Market}.
 */
@Service
public class MarketService {

    private final Logger log = LoggerFactory.getLogger(MarketService.class);

    private final MarketRepository marketRepository;

    private final MarketMapper marketMapper;

    public MarketService(MarketRepository marketRepository, MarketMapper marketMapper) {
        this.marketRepository = marketRepository;
        this.marketMapper = marketMapper;
    }

    /**
     * Save a market.
     *
     * @param marketEnum the market enum to create.
     * @return the persisted entity.
     */
    public MarketDTO create(MARKET marketEnum, String description) {
        log.debug("Request to create Market : {}", marketEnum);
        Market market;
        if (marketRepository.existsByMarket(marketEnum)) {
            throw new IllegalArgumentException("Market already exists by market enum");
        } else {
            market = marketRepository.save(new Market(marketEnum, description));
        }
        market = marketRepository.save(market);
        return marketMapper.toDto(market);
    }

    /**
     * Save a market.
     *
     * @param marketDTO the entity to save.
     * @return the persisted entity.
     */
    public MarketDTO save(MarketDTO marketDTO) {
        log.debug("Request to save Market : {}", marketDTO);
        Market market = marketMapper.toEntity(marketDTO);
        market = marketRepository.save(market);
        return marketMapper.toDto(market);
    }

    /**
     * Get all the markets.
     *
     * @return the list of entities.
     */
    public List<MarketDTO> findAll() {
        log.debug("Request to get all Markets");
        return marketRepository.findAll().stream()
            .map(marketMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one market by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<MarketDTO> findOne(String id) {
        log.debug("Request to get Market : {}", id);
        return marketRepository.findById(id)
            .map(marketMapper::toDto);
    }

    /**
     * Get one market by market enum.
     *
     * @param marketEnum the id of the entity.
     * @return the entity.
     */
    public Optional<MarketDTO> findOne(MARKET marketEnum) {
        log.debug("Request to get Market : {}", marketEnum);
        return marketRepository.findByMarket(marketEnum)
            .map(marketMapper::toDto);
    }

    /**
     * Delete the market by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Market : {}", id);

        marketRepository.deleteById(id);
    }
}
