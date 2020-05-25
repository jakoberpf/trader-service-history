package de.ginisolutions.trader.history.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import de.ginisolutions.trader.history.web.rest.TestUtil;

public class TickTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tick.class);
        Tick tick1 = new Tick();
        tick1.setId("id1");
        Tick tick2 = new Tick();
        tick2.setId(tick1.getId());
        assertThat(tick1).isEqualTo(tick2);
        tick2.setId("id2");
        assertThat(tick1).isNotEqualTo(tick2);
        tick1.setId(null);
        assertThat(tick1).isNotEqualTo(tick2);
    }
}
