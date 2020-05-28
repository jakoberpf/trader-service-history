package de.ginisolutions.trader.history.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MarketMapperTest {

    private MarketMapper marketMapper;

    @BeforeEach
    public void setUp() {
        marketMapper = new MarketMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(marketMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(marketMapper.fromId(null)).isNull();
    }
}
