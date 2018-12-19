package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PySituat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PySituat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PySituatRepository extends JpaRepository<PySituat, Long> {

}
