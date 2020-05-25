package de.ginisolutions.trader.history.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * The Tick entity.\n@author A true hipster
 */
@ApiModel(description = "The Tick entity.\n@author A true hipster")
@Document(collection = "tick")
public class Tick implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("timestamp")
    private Long timestamp;

    @NotNull
    @Field("open")
    private Float open;

    @NotNull
    @Field("close")
    private Float close;

    @NotNull
    @Field("high")
    private Float high;

    @NotNull
    @Field("low")
    private Float low;

    @NotNull
    @Field("volume")
    private Float volume;

    @DBRef
    @Field("stock")
    @JsonIgnoreProperties(value = "ticks", allowSetters = true)
    private Stock stock;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Tick timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Float getOpen() {
        return open;
    }

    public Tick open(Float open) {
        this.open = open;
        return this;
    }

    public void setOpen(Float open) {
        this.open = open;
    }

    public Float getClose() {
        return close;
    }

    public Tick close(Float close) {
        this.close = close;
        return this;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Float getHigh() {
        return high;
    }

    public Tick high(Float high) {
        this.high = high;
        return this;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public Tick low(Float low) {
        this.low = low;
        return this;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getVolume() {
        return volume;
    }

    public Tick volume(Float volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Stock getStock() {
        return stock;
    }

    public Tick stock(Stock stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tick)) {
            return false;
        }
        return id != null && id.equals(((Tick) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tick{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", open=" + getOpen() +
            ", close=" + getClose() +
            ", high=" + getHigh() +
            ", low=" + getLow() +
            ", volume=" + getVolume() +
            "}";
    }
}
