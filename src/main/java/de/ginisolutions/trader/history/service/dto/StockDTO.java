package de.ginisolutions.trader.history.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;

/**
 * A DTO for the {@link de.ginisolutions.trader.history.domain.Stock} entity.
 */
@ApiModel(description = "The Stock entity.\n@author A true hipster")
public class StockDTO implements Serializable {

    private String id;

    @NotNull
    private SYMBOL symbol;

    private String description;

    private String marketId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SYMBOL getSymbol() {
        return symbol;
    }

    public void setSymbol(SYMBOL symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockDTO)) {
            return false;
        }

        return id != null && id.equals(((StockDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", symbol='" + getSymbol() + "'" +
            ", marketId='" + getMarketId() + "'" +
            "}";
    }
}
