package com.spirit.sri.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SRI_IVA")
@Entity
public class SriIvaEJB implements Serializable, SriIvaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Integer codigo;
   private java.lang.Integer porcentaje;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;

   public SriIvaEJB() {
   }

   public SriIvaEJB(com.spirit.sri.entity.SriIvaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setPorcentaje(value.getPorcentaje());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriIvaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setPorcentaje(value.getPorcentaje());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "CODIGO")
   public java.lang.Integer getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.Integer codigo) {
      this.codigo = codigo;
   }

   @Column(name = "PORCENTAJE")
   public java.lang.Integer getPorcentaje() {
      return porcentaje;
   }

   public void setPorcentaje(java.lang.Integer porcentaje) {
      this.porcentaje = porcentaje;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }
	public String toString() {
		return ToStringer.toString((SriIvaIf)this);
	}
}
