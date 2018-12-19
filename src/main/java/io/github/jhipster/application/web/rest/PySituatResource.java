package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.PySituat;
import io.github.jhipster.application.repository.PySituatRepository;
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
 * REST controller for managing PySituat.
 */
@RestController
@RequestMapping("/api")
public class PySituatResource {

    private final Logger log = LoggerFactory.getLogger(PySituatResource.class);

    private static final String ENTITY_NAME = "pySituat";

    private final PySituatRepository pySituatRepository;

    public PySituatResource(PySituatRepository pySituatRepository) {
        this.pySituatRepository = pySituatRepository;
    }

    /**
     * POST  /py-situats : Create a new pySituat.
     *
     * @param pySituat the pySituat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pySituat, or with status 400 (Bad Request) if the pySituat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/py-situats")
    @Timed
    public ResponseEntity<PySituat> createPySituat(@RequestBody PySituat pySituat) throws URISyntaxException {
        log.debug("REST request to save PySituat : {}", pySituat);
        if (pySituat.getId() != null) {
            throw new BadRequestAlertException("A new pySituat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PySituat result = pySituatRepository.save(pySituat);
        return ResponseEntity.created(new URI("/api/py-situats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /py-situats : Updates an existing pySituat.
     *
     * @param pySituat the pySituat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pySituat,
     * or with status 400 (Bad Request) if the pySituat is not valid,
     * or with status 500 (Internal Server Error) if the pySituat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/py-situats")
    @Timed
    public ResponseEntity<PySituat> updatePySituat(@RequestBody PySituat pySituat) throws URISyntaxException {
        log.debug("REST request to update PySituat : {}", pySituat);
        if (pySituat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PySituat result = pySituatRepository.save(pySituat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pySituat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /py-situats : get all the pySituats.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of pySituats in body
     */
    @GetMapping("/py-situats")
    @Timed
    public List<PySituat> getAllPySituats(@RequestParam(required = false) String filter) {
        if ("sitcd-is-null".equals(filter)) {
            log.debug("REST request to get all PySituats where sitCd is null");
            return StreamSupport
                .stream(pySituatRepository.findAll().spliterator(), false)
                .filter(pySituat -> pySituat.getSitCd() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all PySituats");
        return pySituatRepository.findAll();
    }

    /**
     * GET  /py-situats/:id : get the "id" pySituat.
     *
     * @param id the id of the pySituat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pySituat, or with status 404 (Not Found)
     */
    @GetMapping("/py-situats/{id}")
    @Timed
    public ResponseEntity<PySituat> getPySituat(@PathVariable Long id) {
        log.debug("REST request to get PySituat : {}", id);
        Optional<PySituat> pySituat = pySituatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pySituat);
    }

    /**
     * DELETE  /py-situats/:id : delete the "id" pySituat.
     *
     * @param id the id of the pySituat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/py-situats/{id}")
    @Timed
    public ResponseEntity<Void> deletePySituat(@PathVariable Long id) {
        log.debug("REST request to delete PySituat : {}", id);

        pySituatRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
