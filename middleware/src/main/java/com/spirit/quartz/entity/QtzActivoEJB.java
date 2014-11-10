/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spirit.quartz.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.spirit.util.HibernateSequenceAllocationSize;

/**
 *
 * @author Windows
 */
@Entity
@Table(name = "QTZ_ACTIVO")
public class QtzActivoEJB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    @TableGenerator(name="SEQ_GEN",
    			allocationSize=HibernateSequenceAllocationSize.allocationSize
    )
       @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
    private Long id;
    @Column(name = "QTZ_CLASS")
    private String qtzClass;
    @Column(name = "ACTIVO")
    private String activo;

    public QtzActivoEJB() {
    }

    public QtzActivoEJB(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQtzClass() {
        return qtzClass;
    }

    public void setQtzClass(String qtzClass) {
        this.qtzClass = qtzClass;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QtzActivoEJB)) {
            return false;
        }
        QtzActivoEJB other = (QtzActivoEJB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spirit.quartz.entity.QtzActivo[id=" + id + "]";
    }

}
