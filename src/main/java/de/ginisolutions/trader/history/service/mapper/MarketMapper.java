package de.ginisolutions.trader.history.service.mapper;


import de.ginisolutions.trader.history.domain.*;
import de.ginisolutions.trader.history.service.dto.MarketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Market} and its DTO {@link MarketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MarketMapper extends EntityMapper<MarketDTO, Market> {


    @Mapping(target = "stocks", ignore = true)
    @Mapping(target = "removeStock", ignore = true)
    Market toEntity(MarketDTO marketDTO);

    default Market fromId(String id) {
        if (id == null) {
            return null;
        }
        Market market = new Market();
        market.setId(id);
        return market;
    }
}
