package de.ginisolutions.trader.history.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TickMapperTest {

    private TickMapper tickMapper;

    @BeforeEach
    public void setUp() {
        tickMapper = new TickMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(tickMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tickMapper.fromId(null)).isNull();
    }
}
