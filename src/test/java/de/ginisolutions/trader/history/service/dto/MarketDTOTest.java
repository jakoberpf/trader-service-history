package de.ginisolutions.trader.history.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import de.ginisolutions.trader.history.web.rest.TestUtil;

public class MarketDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarketDTO.class);
        MarketDTO marketDTO1 = new MarketDTO();
        marketDTO1.setId("id1");
        MarketDTO marketDTO2 = new MarketDTO();
        assertThat(marketDTO1).isNotEqualTo(marketDTO2);
        marketDTO2.setId(marketDTO1.getId());
        assertThat(marketDTO1).isEqualTo(marketDTO2);
        marketDTO2.setId("id2");
        assertThat(marketDTO1).isNotEqualTo(marketDTO2);
        marketDTO1.setId(null);
        assertThat(marketDTO1).isNotEqualTo(marketDTO2);
    }
}
