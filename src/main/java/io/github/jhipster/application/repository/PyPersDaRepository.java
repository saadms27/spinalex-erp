package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PyPersDa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PyPersDa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PyPersDaRepository extends JpaRepository<PyPersDa, Long> {

}
