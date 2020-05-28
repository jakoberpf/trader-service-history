package de.ginisolutions.trader.history.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;

/**
 * A DTO for the {@link de.ginisolutions.trader.history.domain.Market} entity.
 */
@ApiModel(description = "The Market entity.\n@author A true hipster")
public class MarketDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    private String description;

    private MARKET market;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MARKET getMarket() {
        return market;
    }

    public void setMarket(MARKET market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarketDTO)) {
            return false;
        }

        return id != null && id.equals(((MarketDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MarketDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", market='" + getMarket() + "'" +
            "}";
    }
}
