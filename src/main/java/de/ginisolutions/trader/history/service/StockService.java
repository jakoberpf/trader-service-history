package de.ginisolutions.trader.history.service;

import de.ginisolutions.trader.history.domain.Stock;
import de.ginisolutions.trader.history.repository.StockRepository;
import de.ginisolutions.trader.history.service.dto.StockDTO;
import de.ginisolutions.trader.history.service.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Stock}.
 */
@Service
public class StockService {

    private final Logger log = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    /**
     * Save a stock.
     *
     * @param stockDTO the entity to save.
     * @return the persisted entity.
     */
    public StockDTO save(StockDTO stockDTO) {
        log.debug("Request to save Stock : {}", stockDTO);
        Stock stock = stockMapper.toEntity(stockDTO);
        stock = stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    /**
     * Get all the stocks.
     *
     * @return the list of entities.
     */
    public List<StockDTO> findAll() {
        log.debug("Request to get all Stocks");
        return stockRepository.findAll().stream()
            .map(stockMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one stock by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<StockDTO> findOne(String id) {
        log.debug("Request to get Stock : {}", id);
        return stockRepository.findById(id)
            .map(stockMapper::toDto);
    }

    /**
     * Delete the stock by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Stock : {}", id);

        stockRepository.deleteById(id);
    }
}
