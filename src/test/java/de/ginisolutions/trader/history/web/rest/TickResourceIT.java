package de.ginisolutions.trader.history.web.rest;

import de.ginisolutions.trader.history.HistoryServiceApp;
import de.ginisolutions.trader.history.config.TestSecurityConfiguration;
import de.ginisolutions.trader.history.domain.Tick;
import de.ginisolutions.trader.history.repository.TickRepository;

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
 * Integration tests for the {@link TickResource} REST controller.
 */
@SpringBootTest(classes = { HistoryServiceApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class TickResourceIT {

    private static final Long DEFAULT_TIMESTAMP = 1L;
    private static final Long UPDATED_TIMESTAMP = 2L;

    private static final Double DEFAULT_OPEN = 1.0;
    private static final Double UPDATED_OPEN = 2.0;

    private static final Double DEFAULT_CLOSE = 1.0;
    private static final Double UPDATED_CLOSE = 2.0;

    private static final Double DEFAULT_HIGH = 1.0;
    private static final Double UPDATED_HIGH = 2.0;

    private static final Double DEFAULT_LOW = 1.0;
    private static final Double UPDATED_LOW = 2.0;

    private static final Double DEFAULT_VOLUME = 1.0;
    private static final Double UPDATED_VOLUME = 2.0;

    @Autowired
    private TickRepository tickRepository;

    @Autowired
    private MockMvc restTickMockMvc;

    private Tick tick;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tick createEntity() {
        Tick tick = new Tick()
            .timestamp(DEFAULT_TIMESTAMP)
            .open(DEFAULT_OPEN)
            .close(DEFAULT_CLOSE)
            .high(DEFAULT_HIGH)
            .low(DEFAULT_LOW)
            .volume(DEFAULT_VOLUME);
        return tick;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tick createUpdatedEntity() {
        Tick tick = new Tick()
            .timestamp(UPDATED_TIMESTAMP)
            .open(UPDATED_OPEN)
            .close(UPDATED_CLOSE)
            .high(UPDATED_HIGH)
            .low(UPDATED_LOW)
            .volume(UPDATED_VOLUME);
        return tick;
    }

    @BeforeEach
    public void initTest() {
        tickRepository.deleteAll();
        tick = createEntity();
    }

    @Test
    public void createTick() throws Exception {
        int databaseSizeBeforeCreate = tickRepository.findAll().size();
        // Create the Tick
        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isCreated());

        // Validate the Tick in the database
        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeCreate + 1);
        Tick testTick = tickList.get(tickList.size() - 1);
        assertThat(testTick.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testTick.getOpen()).isEqualTo(DEFAULT_OPEN);
        assertThat(testTick.getClose()).isEqualTo(DEFAULT_CLOSE);
        assertThat(testTick.getHigh()).isEqualTo(DEFAULT_HIGH);
        assertThat(testTick.getLow()).isEqualTo(DEFAULT_LOW);
        assertThat(testTick.getVolume()).isEqualTo(DEFAULT_VOLUME);
    }

    @Test
    public void createTickWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tickRepository.findAll().size();

        // Create the Tick with an existing ID
        tick.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        // Validate the Tick in the database
        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = tickRepository.findAll().size();
        // set the field null
        tick.setTimestamp(null);

        // Create the Tick, which fails.


        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOpenIsRequired() throws Exception {
        int databaseSizeBeforeTest = tickRepository.findAll().size();
        // set the field null
        tick.setOpen(null);

        // Create the Tick, which fails.


        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCloseIsRequired() throws Exception {
        int databaseSizeBeforeTest = tickRepository.findAll().size();
        // set the field null
        tick.setClose(null);

        // Create the Tick, which fails.


        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkHighIsRequired() throws Exception {
        int databaseSizeBeforeTest = tickRepository.findAll().size();
        // set the field null
        tick.setHigh(null);

        // Create the Tick, which fails.


        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLowIsRequired() throws Exception {
        int databaseSizeBeforeTest = tickRepository.findAll().size();
        // set the field null
        tick.setLow(null);

        // Create the Tick, which fails.


        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tickRepository.findAll().size();
        // set the field null
        tick.setVolume(null);

        // Create the Tick, which fails.


        restTickMockMvc.perform(post("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllTicks() throws Exception {
        // Initialize the database
        tickRepository.save(tick);

        // Get all the tickList
        restTickMockMvc.perform(get("/api/ticks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tick.getId())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.intValue())))
            .andExpect(jsonPath("$.[*].open").value(hasItem(DEFAULT_OPEN)))
            .andExpect(jsonPath("$.[*].close").value(hasItem(DEFAULT_CLOSE)))
            .andExpect(jsonPath("$.[*].high").value(hasItem(DEFAULT_HIGH)))
            .andExpect(jsonPath("$.[*].low").value(hasItem(DEFAULT_LOW)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)));
    }

    @Test
    public void getTick() throws Exception {
        // Initialize the database
        tickRepository.save(tick);

        // Get the tick
        restTickMockMvc.perform(get("/api/ticks/{id}", tick.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tick.getId()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.intValue()))
            .andExpect(jsonPath("$.open").value(DEFAULT_OPEN))
            .andExpect(jsonPath("$.close").value(DEFAULT_CLOSE))
            .andExpect(jsonPath("$.high").value(DEFAULT_HIGH))
            .andExpect(jsonPath("$.low").value(DEFAULT_LOW))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME));
    }
    @Test
    public void getNonExistingTick() throws Exception {
        // Get the tick
        restTickMockMvc.perform(get("/api/ticks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTick() throws Exception {
        // Initialize the database
        tickRepository.save(tick);

        int databaseSizeBeforeUpdate = tickRepository.findAll().size();

        // Update the tick
        Tick updatedTick = tickRepository.findById(tick.getId()).get();
        updatedTick
            .timestamp(UPDATED_TIMESTAMP)
            .open(UPDATED_OPEN)
            .close(UPDATED_CLOSE)
            .high(UPDATED_HIGH)
            .low(UPDATED_LOW)
            .volume(UPDATED_VOLUME);

        restTickMockMvc.perform(put("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTick)))
            .andExpect(status().isOk());

        // Validate the Tick in the database
        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeUpdate);
        Tick testTick = tickList.get(tickList.size() - 1);
        assertThat(testTick.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testTick.getOpen()).isEqualTo(UPDATED_OPEN);
        assertThat(testTick.getClose()).isEqualTo(UPDATED_CLOSE);
        assertThat(testTick.getHigh()).isEqualTo(UPDATED_HIGH);
        assertThat(testTick.getLow()).isEqualTo(UPDATED_LOW);
        assertThat(testTick.getVolume()).isEqualTo(UPDATED_VOLUME);
    }

    @Test
    public void updateNonExistingTick() throws Exception {
        int databaseSizeBeforeUpdate = tickRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTickMockMvc.perform(put("/api/ticks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tick)))
            .andExpect(status().isBadRequest());

        // Validate the Tick in the database
        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTick() throws Exception {
        // Initialize the database
        tickRepository.save(tick);

        int databaseSizeBeforeDelete = tickRepository.findAll().size();

        // Delete the tick
        restTickMockMvc.perform(delete("/api/ticks/{id}", tick.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tick> tickList = tickRepository.findAll();
        assertThat(tickList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
