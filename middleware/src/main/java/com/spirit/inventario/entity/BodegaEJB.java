package com.spirit.inventario.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "BODEGA")
@Entity
public class BodegaEJB implements Serializable, BodegaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long oficinaId;
   private java.lang.Long funcionBodegaId;
   private java.lang.Long tipoBodegaId;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.String estado;

   public BodegaEJB() {
   }

   public BodegaEJB(com.spirit.inventario.entity.BodegaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setOficinaId(value.getOficinaId());
      setFuncionBodegaId(value.getFuncionBodegaId());
      setTipoBodegaId(value.getTipoBodegaId());
      setFechaCreacion(value.getFechaCreacion());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.inventario.entity.BodegaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setOficinaId(value.getOficinaId());
      setFuncionBodegaId(value.getFuncionBodegaId());
      setTipoBodegaId(value.getTipoBodegaId());
      setFechaCreacion(value.getFechaCreacion());
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

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "FUNCION_BODEGA_ID")
   public java.lang.Long getFuncionBodegaId() {
      return funcionBodegaId;
   }

   public void setFuncionBodegaId(java.lang.Long funcionBodegaId) {
      this.funcionBodegaId = funcionBodegaId;
   }

   @Column(name = "TIPO_BODEGA_ID")
   public java.lang.Long getTipoBodegaId() {
      return tipoBodegaId;
   }

   public void setTipoBodegaId(java.lang.Long tipoBodegaId) {
      this.tipoBodegaId = tipoBodegaId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((BodegaIf)this);
	}
}
