package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.PyUnitCd;
import io.github.jhipster.application.repository.PyUnitCdRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing PyUnitCd.
 */
@RestController
@RequestMapping("/api")
public class PyUnitCdResource {

    private final Logger log = LoggerFactory.getLogger(PyUnitCdResource.class);

    private static final String ENTITY_NAME = "pyUnitCd";

    private final PyUnitCdRepository pyUnitCdRepository;

    public PyUnitCdResource(PyUnitCdRepository pyUnitCdRepository) {
        this.pyUnitCdRepository = pyUnitCdRepository;
    }

    /**
     * POST  /py-unit-cds : Create a new pyUnitCd.
     *
     * @param pyUnitCd the pyUnitCd to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pyUnitCd, or with status 400 (Bad Request) if the pyUnitCd has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/py-unit-cds")
    @Timed
    public ResponseEntity<PyUnitCd> createPyUnitCd(@RequestBody PyUnitCd pyUnitCd) throws URISyntaxException {
        log.debug("REST request to save PyUnitCd : {}", pyUnitCd);
        if (pyUnitCd.getId() != null) {
            throw new BadRequestAlertException("A new pyUnitCd cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PyUnitCd result = pyUnitCdRepository.save(pyUnitCd);
        return ResponseEntity.created(new URI("/api/py-unit-cds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /py-unit-cds : Updates an existing pyUnitCd.
     *
     * @param pyUnitCd the pyUnitCd to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pyUnitCd,
     * or with status 400 (Bad Request) if the pyUnitCd is not valid,
     * or with status 500 (Internal Server Error) if the pyUnitCd couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/py-unit-cds")
    @Timed
    public ResponseEntity<PyUnitCd> updatePyUnitCd(@RequestBody PyUnitCd pyUnitCd) throws URISyntaxException {
        log.debug("REST request to update PyUnitCd : {}", pyUnitCd);
        if (pyUnitCd.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PyUnitCd result = pyUnitCdRepository.save(pyUnitCd);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pyUnitCd.getId().toString()))
            .body(result);
    }

    /**
     * GET  /py-unit-cds : get all the pyUnitCds.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of pyUnitCds in body
     */
    @GetMapping("/py-unit-cds")
    @Timed
    public List<PyUnitCd> getAllPyUnitCds(@RequestParam(required = false) String filter) {
        if ("ucd-is-null".equals(filter)) {
            log.debug("REST request to get all PyUnitCds where uCd is null");
            return StreamSupport
                .stream(pyUnitCdRepository.findAll().spliterator(), false)
                .filter(pyUnitCd -> pyUnitCd.getUCd() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all PyUnitCds");
        return pyUnitCdRepository.findAll();
    }

    /**
     * GET  /py-unit-cds/:id : get the "id" pyUnitCd.
     *
     * @param id the id of the pyUnitCd to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pyUnitCd, or with status 404 (Not Found)
     */
    @GetMapping("/py-unit-cds/{id}")
    @Timed
    public ResponseEntity<PyUnitCd> getPyUnitCd(@PathVariable Long id) {
        log.debug("REST request to get PyUnitCd : {}", id);
        Optional<PyUnitCd> pyUnitCd = pyUnitCdRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pyUnitCd);
    }

    /**
     * DELETE  /py-unit-cds/:id : delete the "id" pyUnitCd.
     *
     * @param id the id of the pyUnitCd to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/py-unit-cds/{id}")
    @Timed
    public ResponseEntity<Void> deletePyUnitCd(@PathVariable Long id) {
        log.debug("REST request to delete PyUnitCd : {}", id);

        pyUnitCdRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
