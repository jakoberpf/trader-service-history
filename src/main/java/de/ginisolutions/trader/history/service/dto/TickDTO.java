package de.ginisolutions.trader.history.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link de.ginisolutions.trader.history.domain.Tick} entity.
 */
@ApiModel(description = "The Tick entity.\n@author A true hipster")
public class TickDTO implements Serializable {
    
    private String id;

    @NotNull
    private Long timestamp;

    @NotNull
    private Float open;

    @NotNull
    private Float close;

    @NotNull
    private Float high;

    @NotNull
    private Float low;

    @NotNull
    private Float volume;


    private String stockId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Float getOpen() {
        return open;
    }

    public void setOpen(Float open) {
        this.open = open;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TickDTO)) {
            return false;
        }

        return id != null && id.equals(((TickDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TickDTO{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", open=" + getOpen() +
            ", close=" + getClose() +
            ", high=" + getHigh() +
            ", low=" + getLow() +
            ", volume=" + getVolume() +
            ", stockId='" + getStockId() + "'" +
            "}";
    }
}
