package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "LISTA_PRECIO")
@Entity
public class ListaPrecioEJB implements Serializable, ListaPrecioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.String referenciaFisica;
   private java.sql.Date fechaCreacion;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFinal;
   private java.lang.String estado;

   public ListaPrecioEJB() {
   }

   public ListaPrecioEJB(com.spirit.facturacion.entity.ListaPrecioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setReferenciaFisica(value.getReferenciaFisica());
      setFechaCreacion(value.getFechaCreacion());
      setFechaInicio(value.getFechaInicio());
      setFechaFinal(value.getFechaFinal());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.ListaPrecioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setReferenciaFisica(value.getReferenciaFisica());
      setFechaCreacion(value.getFechaCreacion());
      setFechaInicio(value.getFechaInicio());
      setFechaFinal(value.getFechaFinal());
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

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "REFERENCIA_FISICA")
   public java.lang.String getReferenciaFisica() {
      return referenciaFisica;
   }

   public void setReferenciaFisica(java.lang.String referenciaFisica) {
      this.referenciaFisica = referenciaFisica;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FINAL")
   public java.sql.Date getFechaFinal() {
      return fechaFinal;
   }

   public void setFechaFinal(java.sql.Date fechaFinal) {
      this.fechaFinal = fechaFinal;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((ListaPrecioIf)this);
	}
}
