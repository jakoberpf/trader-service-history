package de.ginisolutions.trader.history.web.rest;

import de.ginisolutions.trader.history.HistoryServiceApp;
import de.ginisolutions.trader.history.config.TestSecurityConfiguration;
import de.ginisolutions.trader.history.domain.Market;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.repository.MarketRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MarketResource} REST controller.
 */
@SpringBootTest(classes = { HistoryServiceApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class MarketResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final MARKET DEFAULT_MARKET = MARKET.SAMPLE_ENUM;
    private static final MARKET UPDATED_MARKET = MARKET.SAMPLE_ENUM;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private MockMvc restMarketMockMvc;

    private Market market;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Market createEntity() {
        Market market = new Market()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .market(DEFAULT_MARKET);
        return market;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Market createUpdatedEntity() {
        Market market = new Market()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .market(UPDATED_MARKET);
        return market;
    }

    @BeforeEach
    public void initTest() {
        marketRepository.deleteAll();
        market = createEntity();
    }

    @Test
    public void createMarket() throws Exception {
        int databaseSizeBeforeCreate = marketRepository.findAll().size();
        // Create the Market
        restMarketMockMvc.perform(post("/api/markets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(market)))
            .andExpect(status().isCreated());

        // Validate the Market in the database
        List<Market> marketList = marketRepository.findAll();
        assertThat(marketList).hasSize(databaseSizeBeforeCreate + 1);
        Market testMarket = marketList.get(marketList.size() - 1);
        assertThat(testMarket.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMarket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMarket.getMarket()).isEqualTo(DEFAULT_MARKET);
    }

    @Test
    public void createMarketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = marketRepository.findAll().size();

        // Create the Market with an existing ID
        market.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarketMockMvc.perform(post("/api/markets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(market)))
            .andExpect(status().isBadRequest());

        // Validate the Market in the database
        List<Market> marketList = marketRepository.findAll();
        assertThat(marketList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = marketRepository.findAll().size();
        // set the field null
        market.setName(null);

        // Create the Market, which fails.


        restMarketMockMvc.perform(post("/api/markets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(market)))
            .andExpect(status().isBadRequest());

        List<Market> marketList = marketRepository.findAll();
        assertThat(marketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllMarkets() throws Exception {
        // Initialize the database
        marketRepository.save(market);

        // Get all the marketList
        restMarketMockMvc.perform(get("/api/markets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(market.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET.toString())));
    }

    @Test
    public void getMarket() throws Exception {
        // Initialize the database
        marketRepository.save(market);

        // Get the market
        restMarketMockMvc.perform(get("/api/markets/{id}", market.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(market.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.market").value(DEFAULT_MARKET.toString()));
    }
    @Test
    public void getNonExistingMarket() throws Exception {
        // Get the market
        restMarketMockMvc.perform(get("/api/markets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMarket() throws Exception {
        // Initialize the database
        marketRepository.save(market);

        int databaseSizeBeforeUpdate = marketRepository.findAll().size();

        // Update the market
        Market updatedMarket = marketRepository.findById(market.getId()).get();
        updatedMarket
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .market(UPDATED_MARKET);

        restMarketMockMvc.perform(put("/api/markets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMarket)))
            .andExpect(status().isOk());

        // Validate the Market in the database
        List<Market> marketList = marketRepository.findAll();
        assertThat(marketList).hasSize(databaseSizeBeforeUpdate);
        Market testMarket = marketList.get(marketList.size() - 1);
        assertThat(testMarket.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMarket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMarket.getMarket()).isEqualTo(UPDATED_MARKET);
    }

    @Test
    public void updateNonExistingMarket() throws Exception {
        int databaseSizeBeforeUpdate = marketRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMarketMockMvc.perform(put("/api/markets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(market)))
            .andExpect(status().isBadRequest());

        // Validate the Market in the database
        List<Market> marketList = marketRepository.findAll();
        assertThat(marketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMarket() throws Exception {
        // Initialize the database
        marketRepository.save(market);

        int databaseSizeBeforeDelete = marketRepository.findAll().size();

        // Delete the market
        restMarketMockMvc.perform(delete("/api/markets/{id}", market.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Market> marketList = marketRepository.findAll();
        assertThat(marketList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
