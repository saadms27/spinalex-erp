package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PyPersDa.
 */
@Entity
@Table(name = "py_pers_da")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PyPersDa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "emp_no")
    private String empNo;

    @Column(name = "u_u_cd")
    private Long uUCd;

    @Column(name = "sit_cd")
    private Long sitCd;

    @OneToOne    @JoinColumn(unique = true)
    private PyUnitCd uUCd;

    @OneToOne    @JoinColumn(unique = true)
    private PySituat sitCd;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public PyPersDa empNo(String empNo) {
        this.empNo = empNo;
        return this;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Long getuUCd() {
        return uUCd;
    }

    public PyPersDa uUCd(Long uUCd) {
        this.uUCd = uUCd;
        return this;
    }

    public void setuUCd(Long uUCd) {
        this.uUCd = uUCd;
    }

    public Long getSitCd() {
        return sitCd;
    }

    public PyPersDa sitCd(Long sitCd) {
        this.sitCd = sitCd;
        return this;
    }

    public void setSitCd(Long sitCd) {
        this.sitCd = sitCd;
    }

    public PyUnitCd getUUCd() {
        return uUCd;
    }

    public PyPersDa uUCd(PyUnitCd pyUnitCd) {
        this.uUCd = pyUnitCd;
        return this;
    }

    public void setUUCd(PyUnitCd pyUnitCd) {
        this.uUCd = pyUnitCd;
    }

    public PySituat getSitCd() {
        return sitCd;
    }

    public PyPersDa sitCd(PySituat pySituat) {
        this.sitCd = pySituat;
        return this;
    }

    public void setSitCd(PySituat pySituat) {
        this.sitCd = pySituat;
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
        PyPersDa pyPersDa = (PyPersDa) o;
        if (pyPersDa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pyPersDa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PyPersDa{" +
            "id=" + getId() +
            ", empNo='" + getEmpNo() + "'" +
            ", uUCd=" + getuUCd() +
            ", sitCd=" + getSitCd() +
            "}";
    }
}
