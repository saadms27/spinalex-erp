package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.PyPersDa;
import io.github.jhipster.application.repository.PyPersDaRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PyPersDa.
 */
@RestController
@RequestMapping("/api")
public class PyPersDaResource {

    private final Logger log = LoggerFactory.getLogger(PyPersDaResource.class);

    private static final String ENTITY_NAME = "pyPersDa";

    private final PyPersDaRepository pyPersDaRepository;

    public PyPersDaResource(PyPersDaRepository pyPersDaRepository) {
        this.pyPersDaRepository = pyPersDaRepository;
    }

    /**
     * POST  /py-pers-das : Create a new pyPersDa.
     *
     * @param pyPersDa the pyPersDa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pyPersDa, or with status 400 (Bad Request) if the pyPersDa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/py-pers-das")
    @Timed
    public ResponseEntity<PyPersDa> createPyPersDa(@RequestBody PyPersDa pyPersDa) throws URISyntaxException {
        log.debug("REST request to save PyPersDa : {}", pyPersDa);
        if (pyPersDa.getId() != null) {
            throw new BadRequestAlertException("A new pyPersDa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PyPersDa result = pyPersDaRepository.save(pyPersDa);
        return ResponseEntity.created(new URI("/api/py-pers-das/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /py-pers-das : Updates an existing pyPersDa.
     *
     * @param pyPersDa the pyPersDa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pyPersDa,
     * or with status 400 (Bad Request) if the pyPersDa is not valid,
     * or with status 500 (Internal Server Error) if the pyPersDa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/py-pers-das")
    @Timed
    public ResponseEntity<PyPersDa> updatePyPersDa(@RequestBody PyPersDa pyPersDa) throws URISyntaxException {
        log.debug("REST request to update PyPersDa : {}", pyPersDa);
        if (pyPersDa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PyPersDa result = pyPersDaRepository.save(pyPersDa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pyPersDa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /py-pers-das : get all the pyPersDas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pyPersDas in body
     */
    @GetMapping("/py-pers-das")
    @Timed
    public List<PyPersDa> getAllPyPersDas() {
        log.debug("REST request to get all PyPersDas");
        return pyPersDaRepository.findAll();
    }

    /**
     * GET  /py-pers-das/:id : get the "id" pyPersDa.
     *
     * @param id the id of the pyPersDa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pyPersDa, or with status 404 (Not Found)
     */
    @GetMapping("/py-pers-das/{id}")
    @Timed
    public ResponseEntity<PyPersDa> getPyPersDa(@PathVariable Long id) {
        log.debug("REST request to get PyPersDa : {}", id);
        Optional<PyPersDa> pyPersDa = pyPersDaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pyPersDa);
    }

    /**
     * DELETE  /py-pers-das/:id : delete the "id" pyPersDa.
     *
     * @param id the id of the pyPersDa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/py-pers-das/{id}")
    @Timed
    public ResponseEntity<Void> deletePyPersDa(@PathVariable Long id) {
        log.debug("REST request to delete PyPersDa : {}", id);

        pyPersDaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
