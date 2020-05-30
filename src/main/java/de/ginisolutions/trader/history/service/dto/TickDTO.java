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
    private Double open;

    @NotNull
    private Double close;

    @NotNull
    private Double high;

    @NotNull
    private Double low;

    @NotNull
    private Double volume;


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

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
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
