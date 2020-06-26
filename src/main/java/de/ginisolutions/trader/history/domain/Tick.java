package de.ginisolutions.trader.common.model;

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
    private Double open;

    @NotNull
    @Field("close")
    private Double close;

    @NotNull
    @Field("high")
    private Double high;

    @NotNull
    @Field("low")
    private Double low;

    @NotNull
    @Field("volume")
    private Double volume;

    @DBRef
    @Field("stock")
    @JsonIgnoreProperties(value = "ticks", allowSetters = true)
    private Stock stock;

    public Tick() {
    }

    public Tick(@NotNull Long timestamp, @NotNull Double open, @NotNull Double close, @NotNull Double high, @NotNull Double low, @NotNull Double volume) {
        this.timestamp = timestamp;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

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

    public Double getOpen() {
        return open;
    }

    public Tick open(Double open) {
        this.open = open;
        return this;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public Tick close(Double close) {
        this.close = close;
        return this;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public Tick high(Double high) {
        this.high = high;
        return this;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public Tick low(Double low) {
        this.low = low;
        return this;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return volume;
    }

    public Tick volume(Double volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Double volume) {
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
