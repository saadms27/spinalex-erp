package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PySituat.
 */
@Entity
@Table(name = "py_situat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PySituat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sit_cd")
    private Long sitCd;

    @Column(name = "sit_nm")
    private String sitNm;

    @OneToOne(mappedBy = "sitCd")
    @JsonIgnore
    private PyPersDa sitCd;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSitCd() {
        return sitCd;
    }

    public PySituat sitCd(Long sitCd) {
        this.sitCd = sitCd;
        return this;
    }

    public void setSitCd(Long sitCd) {
        this.sitCd = sitCd;
    }

    public String getSitNm() {
        return sitNm;
    }

    public PySituat sitNm(String sitNm) {
        this.sitNm = sitNm;
        return this;
    }

    public void setSitNm(String sitNm) {
        this.sitNm = sitNm;
    }

    public PyPersDa getSitCd() {
        return sitCd;
    }

    public PySituat sitCd(PyPersDa pyPersDa) {
        this.sitCd = pyPersDa;
        return this;
    }

    public void setSitCd(PyPersDa pyPersDa) {
        this.sitCd = pyPersDa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PySituat pySituat = (PySituat) o;
        if (pySituat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pySituat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PySituat{" +
            "id=" + getId() +
            ", sitCd=" + getSitCd() +
            ", sitNm='" + getSitNm() + "'" +
            "}";
    }
}
