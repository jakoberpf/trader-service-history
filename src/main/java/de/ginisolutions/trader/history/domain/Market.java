package de.ginisolutions.trader.history.domain;

import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import de.ginisolutions.trader.history.domain.enumeration.MarketE;

/**
 * The Market entity.\n@author A true hipster
 */
@ApiModel(description = "The Market entity.\n@author A true hipster")
@Document(collection = "market")
public class Market implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("market")
    private MarketE market;

    @DBRef
    @Field("stock")
    private Set<Stock> stocks = new HashSet<>();

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

    public Market name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Market description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MarketE getMarket() {
        return market;
    }

    public Market market(MarketE market) {
        this.market = market;
        return this;
    }

    public void setMarket(MarketE market) {
        this.market = market;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public Market stocks(Set<Stock> stocks) {
        this.stocks = stocks;
        return this;
    }

    public Market addStock(Stock stock) {
        this.stocks.add(stock);
        stock.setMarket(this);
        return this;
    }

    public Market removeStock(Stock stock) {
        this.stocks.remove(stock);
        stock.setMarket(null);
        return this;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Market)) {
            return false;
        }
        return id != null && id.equals(((Market) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Market{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", market='" + getMarket() + "'" +
            "}";
    }
}
