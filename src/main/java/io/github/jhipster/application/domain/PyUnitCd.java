package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PyUnitCd.
 */
@Entity
@Table(name = "py_unit_cd")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PyUnitCd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "u_cd")
    private Long uCd;

    @Column(name = "u_nm")
    private String uNm;

    @OneToOne(mappedBy = "uUCd")
    @JsonIgnore
    private PyPersDa uCd;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuCd() {
        return uCd;
    }

    public PyUnitCd uCd(Long uCd) {
        this.uCd = uCd;
        return this;
    }

    public void setuCd(Long uCd) {
        this.uCd = uCd;
    }

    public String getuNm() {
        return uNm;
    }

    public PyUnitCd uNm(String uNm) {
        this.uNm = uNm;
        return this;
    }

    public void setuNm(String uNm) {
        this.uNm = uNm;
    }

    public PyPersDa getUCd() {
        return uCd;
    }

    public PyUnitCd uCd(PyPersDa pyPersDa) {
        this.uCd = pyPersDa;
        return this;
    }

    public void setUCd(PyPersDa pyPersDa) {
        this.uCd = pyPersDa;
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
        PyUnitCd pyUnitCd = (PyUnitCd) o;
        if (pyUnitCd.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pyUnitCd.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PyUnitCd{" +
            "id=" + getId() +
            ", uCd=" + getuCd() +
            ", uNm='" + getuNm() + "'" +
            "}";
    }
}
