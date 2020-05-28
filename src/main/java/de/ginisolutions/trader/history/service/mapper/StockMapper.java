package de.ginisolutions.trader.history.service.mapper;


import de.ginisolutions.trader.history.domain.*;
import de.ginisolutions.trader.history.service.dto.StockDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Stock} and its DTO {@link StockDTO}.
 */
@Mapper(componentModel = "spring", uses = {MarketMapper.class})
public interface StockMapper extends EntityMapper<StockDTO, Stock> {

    @Mapping(source = "market.id", target = "marketId")
    StockDTO toDto(Stock stock);

    @Mapping(target = "ticks", ignore = true)
    @Mapping(target = "removeTick", ignore = true)
    @Mapping(source = "marketId", target = "market")
    Stock toEntity(StockDTO stockDTO);

    default Stock fromId(String id) {
        if (id == null) {
            return null;
        }
        Stock stock = new Stock();
        stock.setId(id);
        return stock;
    }
}
