package com.spirit.nomina.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "TIPO_ROL")
@Entity
public class TipoRolEJB implements Serializable, TipoRolIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empresaId;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String nemonico;
   private java.lang.String rubroEventual;
   private java.lang.Long documentoId;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;
   private java.lang.String rubroProvisionado;
   private java.lang.String formaPago;

   public TipoRolEJB() {
   }

   public TipoRolEJB(com.spirit.nomina.entity.TipoRolIf value) {
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setNemonico(value.getNemonico());
      setRubroEventual(value.getRubroEventual());
      setDocumentoId(value.getDocumentoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setRubroProvisionado(value.getRubroProvisionado());
      setFormaPago(value.getFormaPago());
   }

   public java.lang.Long create(com.spirit.nomina.entity.TipoRolIf value) {
      setId(value.getId());
      setEmpresaId(value.getEmpresaId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setNemonico(value.getNemonico());
      setRubroEventual(value.getRubroEventual());
      setDocumentoId(value.getDocumentoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setRubroProvisionado(value.getRubroProvisionado());
      setFormaPago(value.getFormaPago());
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

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
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

   @Column(name = "NEMONICO")
   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }

   @Column(name = "RUBRO_EVENTUAL")
   public java.lang.String getRubroEventual() {
      return rubroEventual;
   }

   public void setRubroEventual(java.lang.String rubroEventual) {
      this.rubroEventual = rubroEventual;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
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

   @Column(name = "RUBRO_PROVISIONADO")
   public java.lang.String getRubroProvisionado() {
      return rubroProvisionado;
   }

   public void setRubroProvisionado(java.lang.String rubroProvisionado) {
      this.rubroProvisionado = rubroProvisionado;
   }

   @Column(name = "FORMA_PAGO")
   public java.lang.String getFormaPago() {
      return formaPago;
   }

   public void setFormaPago(java.lang.String formaPago) {
      this.formaPago = formaPago;
   }
	public String toString() {
		return ToStringer.toString((TipoRolIf)this);
	}
}
