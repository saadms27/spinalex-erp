package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.SpinalexerpApp;

import io.github.jhipster.application.domain.PyUnitCd;
import io.github.jhipster.application.repository.PyUnitCdRepository;
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
 * Test class for the PyUnitCdResource REST controller.
 *
 * @see PyUnitCdResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpinalexerpApp.class)
public class PyUnitCdResourceIntTest {

    private static final Long DEFAULT_U_CD = 1L;
    private static final Long UPDATED_U_CD = 2L;

    private static final String DEFAULT_U_NM = "AAAAAAAAAA";
    private static final String UPDATED_U_NM = "BBBBBBBBBB";

    @Autowired
    private PyUnitCdRepository pyUnitCdRepository;

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

    private MockMvc restPyUnitCdMockMvc;

    private PyUnitCd pyUnitCd;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PyUnitCdResource pyUnitCdResource = new PyUnitCdResource(pyUnitCdRepository);
        this.restPyUnitCdMockMvc = MockMvcBuilders.standaloneSetup(pyUnitCdResource)
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
    public static PyUnitCd createEntity(EntityManager em) {
        PyUnitCd pyUnitCd = new PyUnitCd()
            .uCd(DEFAULT_U_CD)
            .uNm(DEFAULT_U_NM);
        return pyUnitCd;
    }

    @Before
    public void initTest() {
        pyUnitCd = createEntity(em);
    }

    @Test
    @Transactional
    public void createPyUnitCd() throws Exception {
        int databaseSizeBeforeCreate = pyUnitCdRepository.findAll().size();

        // Create the PyUnitCd
        restPyUnitCdMockMvc.perform(post("/api/py-unit-cds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pyUnitCd)))
            .andExpect(status().isCreated());

        // Validate the PyUnitCd in the database
        List<PyUnitCd> pyUnitCdList = pyUnitCdRepository.findAll();
        assertThat(pyUnitCdList).hasSize(databaseSizeBeforeCreate + 1);
        PyUnitCd testPyUnitCd = pyUnitCdList.get(pyUnitCdList.size() - 1);
        assertThat(testPyUnitCd.getuCd()).isEqualTo(DEFAULT_U_CD);
        assertThat(testPyUnitCd.getuNm()).isEqualTo(DEFAULT_U_NM);
    }

    @Test
    @Transactional
    public void createPyUnitCdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pyUnitCdRepository.findAll().size();

        // Create the PyUnitCd with an existing ID
        pyUnitCd.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPyUnitCdMockMvc.perform(post("/api/py-unit-cds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pyUnitCd)))
            .andExpect(status().isBadRequest());

        // Validate the PyUnitCd in the database
        List<PyUnitCd> pyUnitCdList = pyUnitCdRepository.findAll();
        assertThat(pyUnitCdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPyUnitCds() throws Exception {
        // Initialize the database
        pyUnitCdRepository.saveAndFlush(pyUnitCd);

        // Get all the pyUnitCdList
        restPyUnitCdMockMvc.perform(get("/api/py-unit-cds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pyUnitCd.getId().intValue())))
            .andExpect(jsonPath("$.[*].uCd").value(hasItem(DEFAULT_U_CD.intValue())))
            .andExpect(jsonPath("$.[*].uNm").value(hasItem(DEFAULT_U_NM.toString())));
    }
    
    @Test
    @Transactional
    public void getPyUnitCd() throws Exception {
        // Initialize the database
        pyUnitCdRepository.saveAndFlush(pyUnitCd);

        // Get the pyUnitCd
        restPyUnitCdMockMvc.perform(get("/api/py-unit-cds/{id}", pyUnitCd.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pyUnitCd.getId().intValue()))
            .andExpect(jsonPath("$.uCd").value(DEFAULT_U_CD.intValue()))
            .andExpect(jsonPath("$.uNm").value(DEFAULT_U_NM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPyUnitCd() throws Exception {
        // Get the pyUnitCd
        restPyUnitCdMockMvc.perform(get("/api/py-unit-cds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePyUnitCd() throws Exception {
        // Initialize the database
        pyUnitCdRepository.saveAndFlush(pyUnitCd);

        int databaseSizeBeforeUpdate = pyUnitCdRepository.findAll().size();

        // Update the pyUnitCd
        PyUnitCd updatedPyUnitCd = pyUnitCdRepository.findById(pyUnitCd.getId()).get();
        // Disconnect from session so that the updates on updatedPyUnitCd are not directly saved in db
        em.detach(updatedPyUnitCd);
        updatedPyUnitCd
            .uCd(UPDATED_U_CD)
            .uNm(UPDATED_U_NM);

        restPyUnitCdMockMvc.perform(put("/api/py-unit-cds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPyUnitCd)))
            .andExpect(status().isOk());

        // Validate the PyUnitCd in the database
        List<PyUnitCd> pyUnitCdList = pyUnitCdRepository.findAll();
        assertThat(pyUnitCdList).hasSize(databaseSizeBeforeUpdate);
        PyUnitCd testPyUnitCd = pyUnitCdList.get(pyUnitCdList.size() - 1);
        assertThat(testPyUnitCd.getuCd()).isEqualTo(UPDATED_U_CD);
        assertThat(testPyUnitCd.getuNm()).isEqualTo(UPDATED_U_NM);
    }

    @Test
    @Transactional
    public void updateNonExistingPyUnitCd() throws Exception {
        int databaseSizeBeforeUpdate = pyUnitCdRepository.findAll().size();

        // Create the PyUnitCd

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPyUnitCdMockMvc.perform(put("/api/py-unit-cds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pyUnitCd)))
            .andExpect(status().isBadRequest());

        // Validate the PyUnitCd in the database
        List<PyUnitCd> pyUnitCdList = pyUnitCdRepository.findAll();
        assertThat(pyUnitCdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePyUnitCd() throws Exception {
        // Initialize the database
        pyUnitCdRepository.saveAndFlush(pyUnitCd);

        int databaseSizeBeforeDelete = pyUnitCdRepository.findAll().size();

        // Get the pyUnitCd
        restPyUnitCdMockMvc.perform(delete("/api/py-unit-cds/{id}", pyUnitCd.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PyUnitCd> pyUnitCdList = pyUnitCdRepository.findAll();
        assertThat(pyUnitCdList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PyUnitCd.class);
        PyUnitCd pyUnitCd1 = new PyUnitCd();
        pyUnitCd1.setId(1L);
        PyUnitCd pyUnitCd2 = new PyUnitCd();
        pyUnitCd2.setId(pyUnitCd1.getId());
        assertThat(pyUnitCd1).isEqualTo(pyUnitCd2);
        pyUnitCd2.setId(2L);
        assertThat(pyUnitCd1).isNotEqualTo(pyUnitCd2);
        pyUnitCd1.setId(null);
        assertThat(pyUnitCd1).isNotEqualTo(pyUnitCd2);
    }
}
