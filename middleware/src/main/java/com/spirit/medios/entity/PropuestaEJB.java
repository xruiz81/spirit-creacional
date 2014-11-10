package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PROPUESTA")
@Entity
public class PropuestaEJB implements Serializable, PropuestaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long ordentrabajoId;
   private java.lang.Long usuarioId;
   private java.sql.Date fecha;
   private java.math.BigDecimal valor;
   private java.lang.String observacion;
   private java.lang.String estado;

   public PropuestaEJB() {
   }

   public PropuestaEJB(com.spirit.medios.entity.PropuestaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setOrdentrabajoId(value.getOrdentrabajoId());
      setUsuarioId(value.getUsuarioId());
      setFecha(value.getFecha());
      setValor(value.getValor());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.medios.entity.PropuestaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setOrdentrabajoId(value.getOrdentrabajoId());
      setUsuarioId(value.getUsuarioId());
      setFecha(value.getFecha());
      setValor(value.getValor());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
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
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "ORDENTRABAJO_ID")
   public java.lang.Long getOrdentrabajoId() {
      return ordentrabajoId;
   }

   public void setOrdentrabajoId(java.lang.Long ordentrabajoId) {
      this.ordentrabajoId = ordentrabajoId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((PropuestaIf)this);
	}
}
