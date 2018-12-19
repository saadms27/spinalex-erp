package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PyUnitCd;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PyUnitCd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PyUnitCdRepository extends JpaRepository<PyUnitCd, Long> {

}
