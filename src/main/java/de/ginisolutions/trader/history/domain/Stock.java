package de.ginisolutions.trader.history.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.ginisolutions.trader.common.enumeration.SYMBOL;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Stock entity.\n@author A true hipster
 */
@ApiModel(description = "The Stock entity.\n@author A true hipster")
@Document(collection = "stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("symbol")
    private SYMBOL symbol;

    @Field("description")
    private String description;

    @DBRef
    @Field("tick")
    private Set<Tick> ticks = new HashSet<>();

    @DBRef
    @Field("market")
    @JsonIgnoreProperties(value = "stocks", allowSetters = true)
    private Market market;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SYMBOL getSymbol() {
        return symbol;
    }

    public Stock symbol(SYMBOL symbol) {
        this.symbol = symbol;
        return this;
    }

    public void setSymbol(SYMBOL symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public Stock description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tick> getTicks() {
        return ticks;
    }

    public Stock ticks(Set<Tick> ticks) {
        this.ticks = ticks;
        return this;
    }

    public Stock addTick(Tick tick) {
        this.ticks.add(tick);
        tick.setStock(this);
        return this;
    }

    public Stock removeTick(Tick tick) {
        this.ticks.remove(tick);
        tick.setStock(null);
        return this;
    }

    public void setTicks(Set<Tick> ticks) {
        this.ticks = ticks;
    }

    public Market getMarket() {
        return market;
    }

    public Stock market(Market market) {
        this.market = market;
        return this;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        return id != null && id.equals(((Stock) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

