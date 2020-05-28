package de.ginisolutions.trader.history.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import de.ginisolutions.trader.history.web.rest.TestUtil;

public class TickDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TickDTO.class);
        TickDTO tickDTO1 = new TickDTO();
        tickDTO1.setId("id1");
        TickDTO tickDTO2 = new TickDTO();
        assertThat(tickDTO1).isNotEqualTo(tickDTO2);
        tickDTO2.setId(tickDTO1.getId());
        assertThat(tickDTO1).isEqualTo(tickDTO2);
        tickDTO2.setId("id2");
        assertThat(tickDTO1).isNotEqualTo(tickDTO2);
        tickDTO1.setId(null);
        assertThat(tickDTO1).isNotEqualTo(tickDTO2);
    }
}
