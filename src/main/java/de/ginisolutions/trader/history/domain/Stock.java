package de.ginisolutions.trader.history.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import de.ginisolutions.trader.history.domain.enumeration.Symbol;

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
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @NotNull
    @Field("symbol")
    private Symbol symbol;

    @DBRef
    @Field("tick")
    private Set<Tick> ticks = new HashSet<>();

    @DBRef
    @Field("market")
    @JsonIgnoreProperties(value = "stocks", allowSetters = true)
    private Market market;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Stock name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
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

    public Symbol getSymbol() {
        return symbol;
    }

    public Stock symbol(Symbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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

    // prettier-ignore
    @Override
    public String toString() {
        return "Stock{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", symbol='" + getSymbol() + "'" +
            "}";
    }
}
