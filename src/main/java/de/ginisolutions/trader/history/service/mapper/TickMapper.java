package de.ginisolutions.trader.history.service.mapper;


import de.ginisolutions.trader.history.domain.*;
import de.ginisolutions.trader.history.service.dto.TickDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tick} and its DTO {@link TickDTO}.
 */
@Mapper(componentModel = "spring", uses = {StockMapper.class})
public interface TickMapper extends EntityMapper<TickDTO, Tick> {

    @Mapping(source = "stock.id", target = "stockId")
    TickDTO toDto(Tick tick);

    @Mapping(source = "stockId", target = "stock")
    Tick toEntity(TickDTO tickDTO);

    default Tick fromId(String id) {
        if (id == null) {
            return null;
        }
        Tick tick = new Tick();
        tick.setId(id);
        return tick;
    }
}
