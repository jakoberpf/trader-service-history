package de.ginisolutions.trader.history.domain;

import de.ginisolutions.trader.common.enumeration.MARKET;
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
 * The Market entity.\n@author A true hipster
 */
@ApiModel(description = "The Market entity.\n@author A true hipster")
@Document(collection = "market")
public class Market implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("market")
    private MARKET market;

    @NotNull
    @Field("description")
    private String description;

    @DBRef
    @Field("stock")
    private Set<Stock> stocks = new HashSet<>();

    public Market() {
    }

    public Market(@NotNull MARKET market, @NotNull String description) {
        this.market = market;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MARKET getMarket() {
        return market;
    }

    public Market market(MARKET market) {
        this.market = market;
        return this;
    }

    public void setMarket(MARKET market) {
        this.market = market;
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
}

