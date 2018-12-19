package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.SpinalexerpApp;

import io.github.jhipster.application.domain.PySituat;
import io.github.jhipster.application.repository.PySituatRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PySituatResource REST controller.
 *
 * @see PySituatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpinalexerpApp.class)
public class PySituatResourceIntTest {

    private static final Long DEFAULT_SIT_CD = 1L;
    private static final Long UPDATED_SIT_CD = 2L;

    private static final String DEFAULT_SIT_NM = "AAAAAAAAAA";
    private static final String UPDATED_SIT_NM = "BBBBBBBBBB";

    @Autowired
    private PySituatRepository pySituatRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPySituatMockMvc;

    private PySituat pySituat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PySituatResource pySituatResource = new PySituatResource(pySituatRepository);
        this.restPySituatMockMvc = MockMvcBuilders.standaloneSetup(pySituatResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PySituat createEntity(EntityManager em) {
        PySituat pySituat = new PySituat()
            .sitCd(DEFAULT_SIT_CD)
            .sitNm(DEFAULT_SIT_NM);
        return pySituat;
    }

    @Before
    public void initTest() {
        pySituat = createEntity(em);
    }

    @Test
    @Transactional
    public void createPySituat() throws Exception {
        int databaseSizeBeforeCreate = pySituatRepository.findAll().size();

        // Create the PySituat
        restPySituatMockMvc.perform(post("/api/py-situats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pySituat)))
            .andExpect(status().isCreated());

        // Validate the PySituat in the database
        List<PySituat> pySituatList = pySituatRepository.findAll();
        assertThat(pySituatList).hasSize(databaseSizeBeforeCreate + 1);
        PySituat testPySituat = pySituatList.get(pySituatList.size() - 1);
        assertThat(testPySituat.getSitCd()).isEqualTo(DEFAULT_SIT_CD);
        assertThat(testPySituat.getSitNm()).isEqualTo(DEFAULT_SIT_NM);
    }

    @Test
    @Transactional
    public void createPySituatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pySituatRepository.findAll().size();

        // Create the PySituat with an existing ID
        pySituat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPySituatMockMvc.perform(post("/api/py-situats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pySituat)))
            .andExpect(status().isBadRequest());

        // Validate the PySituat in the database
        List<PySituat> pySituatList = pySituatRepository.findAll();
        assertThat(pySituatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPySituats() throws Exception {
        // Initialize the database
        pySituatRepository.saveAndFlush(pySituat);

        // Get all the pySituatList
        restPySituatMockMvc.perform(get("/api/py-situats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pySituat.getId().intValue())))
            .andExpect(jsonPath("$.[*].sitCd").value(hasItem(DEFAULT_SIT_CD.intValue())))
            .andExpect(jsonPath("$.[*].sitNm").value(hasItem(DEFAULT_SIT_NM.toString())));
    }
    
    @Test
    @Transactional
    public void getPySituat() throws Exception {
        // Initialize the database
        pySituatRepository.saveAndFlush(pySituat);

        // Get the pySituat
        restPySituatMockMvc.perform(get("/api/py-situats/{id}", pySituat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pySituat.getId().intValue()))
            .andExpect(jsonPath("$.sitCd").value(DEFAULT_SIT_CD.intValue()))
            .andExpect(jsonPath("$.sitNm").value(DEFAULT_SIT_NM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPySituat() throws Exception {
        // Get the pySituat
        restPySituatMockMvc.perform(get("/api/py-situats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePySituat() throws Exception {
        // Initialize the database
        pySituatRepository.saveAndFlush(pySituat);

        int databaseSizeBeforeUpdate = pySituatRepository.findAll().size();

        // Update the pySituat
        PySituat updatedPySituat = pySituatRepository.findById(pySituat.getId()).get();
        // Disconnect from session so that the updates on updatedPySituat are not directly saved in db
        em.detach(updatedPySituat);
        updatedPySituat
            .sitCd(UPDATED_SIT_CD)
            .sitNm(UPDATED_SIT_NM);

        restPySituatMockMvc.perform(put("/api/py-situats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPySituat)))
            .andExpect(status().isOk());

        // Validate the PySituat in the database
        List<PySituat> pySituatList = pySituatRepository.findAll();
        assertThat(pySituatList).hasSize(databaseSizeBeforeUpdate);
        PySituat testPySituat = pySituatList.get(pySituatList.size() - 1);
        assertThat(testPySituat.getSitCd()).isEqualTo(UPDATED_SIT_CD);
        assertThat(testPySituat.getSitNm()).isEqualTo(UPDATED_SIT_NM);
    }

    @Test
    @Transactional
    public void updateNonExistingPySituat() throws Exception {
        int databaseSizeBeforeUpdate = pySituatRepository.findAll().size();

        // Create the PySituat

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPySituatMockMvc.perform(put("/api/py-situats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pySituat)))
            .andExpect(status().isBadRequest());

        // Validate the PySituat in the database
        List<PySituat> pySituatList = pySituatRepository.findAll();
        assertThat(pySituatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePySituat() throws Exception {
        // Initialize the database
        pySituatRepository.saveAndFlush(pySituat);

        int databaseSizeBeforeDelete = pySituatRepository.findAll().size();

        // Get the pySituat
        restPySituatMockMvc.perform(delete("/api/py-situats/{id}", pySituat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PySituat> pySituatList = pySituatRepository.findAll();
        assertThat(pySituatList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PySituat.class);
        PySituat pySituat1 = new PySituat();
        pySituat1.setId(1L);
        PySituat pySituat2 = new PySituat();
        pySituat2.setId(pySituat1.getId());
        assertThat(pySituat1).isEqualTo(pySituat2);
        pySituat2.setId(2L);
        assertThat(pySituat1).isNotEqualTo(pySituat2);
        pySituat1.setId(null);
        assertThat(pySituat1).isNotEqualTo(pySituat2);
    }
}
