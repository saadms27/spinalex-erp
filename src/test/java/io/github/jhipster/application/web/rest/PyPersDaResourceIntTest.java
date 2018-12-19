package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.SpinalexerpApp;

import io.github.jhipster.application.domain.PyPersDa;
import io.github.jhipster.application.repository.PyPersDaRepository;
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
 * Test class for the PyPersDaResource REST controller.
 *
 * @see PyPersDaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpinalexerpApp.class)
public class PyPersDaResourceIntTest {

    private static final String DEFAULT_EMP_NO = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_U_U_CD = 1L;
    private static final Long UPDATED_U_U_CD = 2L;

    private static final Long DEFAULT_SIT_CD = 1L;
    private static final Long UPDATED_SIT_CD = 2L;

    @Autowired
    private PyPersDaRepository pyPersDaRepository;

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

    private MockMvc restPyPersDaMockMvc;

    private PyPersDa pyPersDa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PyPersDaResource pyPersDaResource = new PyPersDaResource(pyPersDaRepository);
        this.restPyPersDaMockMvc = MockMvcBuilders.standaloneSetup(pyPersDaResource)
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
    public static PyPersDa createEntity(EntityManager em) {
        PyPersDa pyPersDa = new PyPersDa()
            .empNo(DEFAULT_EMP_NO)
            .uUCd(DEFAULT_U_U_CD)
            .sitCd(DEFAULT_SIT_CD);
        return pyPersDa;
    }

    @Before
    public void initTest() {
        pyPersDa = createEntity(em);
    }

    @Test
    @Transactional
    public void createPyPersDa() throws Exception {
        int databaseSizeBeforeCreate = pyPersDaRepository.findAll().size();

        // Create the PyPersDa
        restPyPersDaMockMvc.perform(post("/api/py-pers-das")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pyPersDa)))
            .andExpect(status().isCreated());

        // Validate the PyPersDa in the database
        List<PyPersDa> pyPersDaList = pyPersDaRepository.findAll();
        assertThat(pyPersDaList).hasSize(databaseSizeBeforeCreate + 1);
        PyPersDa testPyPersDa = pyPersDaList.get(pyPersDaList.size() - 1);
        assertThat(testPyPersDa.getEmpNo()).isEqualTo(DEFAULT_EMP_NO);
        assertThat(testPyPersDa.getuUCd()).isEqualTo(DEFAULT_U_U_CD);
        assertThat(testPyPersDa.getSitCd()).isEqualTo(DEFAULT_SIT_CD);
    }

    @Test
    @Transactional
    public void createPyPersDaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pyPersDaRepository.findAll().size();

        // Create the PyPersDa with an existing ID
        pyPersDa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPyPersDaMockMvc.perform(post("/api/py-pers-das")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pyPersDa)))
            .andExpect(status().isBadRequest());

        // Validate the PyPersDa in the database
        List<PyPersDa> pyPersDaList = pyPersDaRepository.findAll();
        assertThat(pyPersDaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPyPersDas() throws Exception {
        // Initialize the database
        pyPersDaRepository.saveAndFlush(pyPersDa);

        // Get all the pyPersDaList
        restPyPersDaMockMvc.perform(get("/api/py-pers-das?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pyPersDa.getId().intValue())))
            .andExpect(jsonPath("$.[*].empNo").value(hasItem(DEFAULT_EMP_NO.toString())))
            .andExpect(jsonPath("$.[*].uUCd").value(hasItem(DEFAULT_U_U_CD.intValue())))
            .andExpect(jsonPath("$.[*].sitCd").value(hasItem(DEFAULT_SIT_CD.intValue())));
    }
    
    @Test
    @Transactional
    public void getPyPersDa() throws Exception {
        // Initialize the database
        pyPersDaRepository.saveAndFlush(pyPersDa);

        // Get the pyPersDa
        restPyPersDaMockMvc.perform(get("/api/py-pers-das/{id}", pyPersDa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pyPersDa.getId().intValue()))
            .andExpect(jsonPath("$.empNo").value(DEFAULT_EMP_NO.toString()))
            .andExpect(jsonPath("$.uUCd").value(DEFAULT_U_U_CD.intValue()))
            .andExpect(jsonPath("$.sitCd").value(DEFAULT_SIT_CD.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPyPersDa() throws Exception {
        // Get the pyPersDa
        restPyPersDaMockMvc.perform(get("/api/py-pers-das/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePyPersDa() throws Exception {
        // Initialize the database
        pyPersDaRepository.saveAndFlush(pyPersDa);

        int databaseSizeBeforeUpdate = pyPersDaRepository.findAll().size();

        // Update the pyPersDa
        PyPersDa updatedPyPersDa = pyPersDaRepository.findById(pyPersDa.getId()).get();
        // Disconnect from session so that the updates on updatedPyPersDa are not directly saved in db
        em.detach(updatedPyPersDa);
        updatedPyPersDa
            .empNo(UPDATED_EMP_NO)
            .uUCd(UPDATED_U_U_CD)
            .sitCd(UPDATED_SIT_CD);

        restPyPersDaMockMvc.perform(put("/api/py-pers-das")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPyPersDa)))
            .andExpect(status().isOk());

        // Validate the PyPersDa in the database
        List<PyPersDa> pyPersDaList = pyPersDaRepository.findAll();
        assertThat(pyPersDaList).hasSize(databaseSizeBeforeUpdate);
        PyPersDa testPyPersDa = pyPersDaList.get(pyPersDaList.size() - 1);
        assertThat(testPyPersDa.getEmpNo()).isEqualTo(UPDATED_EMP_NO);
        assertThat(testPyPersDa.getuUCd()).isEqualTo(UPDATED_U_U_CD);
        assertThat(testPyPersDa.getSitCd()).isEqualTo(UPDATED_SIT_CD);
    }

    @Test
    @Transactional
    public void updateNonExistingPyPersDa() throws Exception {
        int databaseSizeBeforeUpdate = pyPersDaRepository.findAll().size();

        // Create the PyPersDa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPyPersDaMockMvc.perform(put("/api/py-pers-das")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pyPersDa)))
            .andExpect(status().isBadRequest());

        // Validate the PyPersDa in the database
        List<PyPersDa> pyPersDaList = pyPersDaRepository.findAll();
        assertThat(pyPersDaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePyPersDa() throws Exception {
        // Initialize the database
        pyPersDaRepository.saveAndFlush(pyPersDa);

        int databaseSizeBeforeDelete = pyPersDaRepository.findAll().size();

        // Get the pyPersDa
        restPyPersDaMockMvc.perform(delete("/api/py-pers-das/{id}", pyPersDa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PyPersDa> pyPersDaList = pyPersDaRepository.findAll();
        assertThat(pyPersDaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PyPersDa.class);
        PyPersDa pyPersDa1 = new PyPersDa();
        pyPersDa1.setId(1L);
        PyPersDa pyPersDa2 = new PyPersDa();
        pyPersDa2.setId(pyPersDa1.getId());
        assertThat(pyPersDa1).isEqualTo(pyPersDa2);
        pyPersDa2.setId(2L);
        assertThat(pyPersDa1).isNotEqualTo(pyPersDa2);
        pyPersDa1.setId(null);
        assertThat(pyPersDa1).isNotEqualTo(pyPersDa2);
    }
}
