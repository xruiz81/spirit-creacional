/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spirit.performance.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.spirit.util.HibernateSequenceAllocationSize;

/**
 *
 * @author Windows
 */
@Entity
@Table(name = "PERFORMANCE")
public class PerformanceEJB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    @TableGenerator(name="SEQ_GEN",
    			allocationSize=HibernateSequenceAllocationSize.allocationSize
    )
       @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
    private Long id;
    @Column(name = "EJB")
    private String ejb;
    @Column(name = "METHOD")
    private String method;
    @Column(name = "SIGNATURE")
    private String signature;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "TIEMPO_SEGUNDOS")
    private BigDecimal tiempoSegundos;
    @Column(name = "TIEMPO_MINUTOS")
    private BigDecimal tiempoMinutos;

    public PerformanceEJB() {
    }

    public PerformanceEJB(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEjb() {
        return ejb;
    }

    public void setEjb(String ejb) {
        this.ejb = ejb;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getTiempoSegundos() {
        return tiempoSegundos;
    }

    public void setTiempoSegundos(BigDecimal tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
    }

    public BigDecimal getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(BigDecimal tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
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
        if (!(object instanceof PerformanceEJB)) {
            return false;
        }
        PerformanceEJB other = (PerformanceEJB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spirit.performance.entity.Performance[id=" + id + "]";
    }

}
