package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "EVENTO_CONTABLE")
@Entity
public class EventoContableEJB implements Serializable, EventoContableIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.Long moduloId;
   private java.lang.Long oficinaId;
   private java.lang.Long documentoId;
   private java.lang.Long lineaId;
   private java.lang.Long planCuentaId;
   private java.lang.Long etapa;
   private java.lang.String autorizacionRequerida;
   private java.lang.String agruparDetalles;
   private java.lang.String usarDetalleDescripcion;
   private java.lang.String validoAlGuardar;
   private java.lang.String validoAlActualizar;
   private java.lang.Long subtipoAsientoId;

   public EventoContableEJB() {
   }

   public EventoContableEJB(com.spirit.contabilidad.entity.EventoContableIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setModuloId(value.getModuloId());
      setOficinaId(value.getOficinaId());
      setDocumentoId(value.getDocumentoId());
      setLineaId(value.getLineaId());
      setPlanCuentaId(value.getPlanCuentaId());
      setEtapa(value.getEtapa());
      setAutorizacionRequerida(value.getAutorizacionRequerida());
      setAgruparDetalles(value.getAgruparDetalles());
      setUsarDetalleDescripcion(value.getUsarDetalleDescripcion());
      setValidoAlGuardar(value.getValidoAlGuardar());
      setValidoAlActualizar(value.getValidoAlActualizar());
      setSubtipoAsientoId(value.getSubtipoAsientoId());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.EventoContableIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setModuloId(value.getModuloId());
      setOficinaId(value.getOficinaId());
      setDocumentoId(value.getDocumentoId());
      setLineaId(value.getLineaId());
      setPlanCuentaId(value.getPlanCuentaId());
      setEtapa(value.getEtapa());
      setAutorizacionRequerida(value.getAutorizacionRequerida());
      setAgruparDetalles(value.getAgruparDetalles());
      setUsarDetalleDescripcion(value.getUsarDetalleDescripcion());
      setValidoAlGuardar(value.getValidoAlGuardar());
      setValidoAlActualizar(value.getValidoAlActualizar());
      setSubtipoAsientoId(value.getSubtipoAsientoId());
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

   @Column(name = "MODULO_ID")
   public java.lang.Long getModuloId() {
      return moduloId;
   }

   public void setModuloId(java.lang.Long moduloId) {
      this.moduloId = moduloId;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "LINEA_ID")
   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   @Column(name = "PLAN_CUENTA_ID")
   public java.lang.Long getPlanCuentaId() {
      return planCuentaId;
   }

   public void setPlanCuentaId(java.lang.Long planCuentaId) {
      this.planCuentaId = planCuentaId;
   }

   @Column(name = "ETAPA")
   public java.lang.Long getEtapa() {
      return etapa;
   }

   public void setEtapa(java.lang.Long etapa) {
      this.etapa = etapa;
   }

   @Column(name = "AUTORIZACION_REQUERIDA")
   public java.lang.String getAutorizacionRequerida() {
      return autorizacionRequerida;
   }

   public void setAutorizacionRequerida(java.lang.String autorizacionRequerida) {
      this.autorizacionRequerida = autorizacionRequerida;
   }

   @Column(name = "AGRUPAR_DETALLES")
   public java.lang.String getAgruparDetalles() {
      return agruparDetalles;
   }

   public void setAgruparDetalles(java.lang.String agruparDetalles) {
      this.agruparDetalles = agruparDetalles;
   }

   @Column(name = "USAR_DETALLE_DESCRIPCION")
   public java.lang.String getUsarDetalleDescripcion() {
      return usarDetalleDescripcion;
   }

   public void setUsarDetalleDescripcion(java.lang.String usarDetalleDescripcion) {
      this.usarDetalleDescripcion = usarDetalleDescripcion;
   }

   @Column(name = "VALIDO_AL_GUARDAR")
   public java.lang.String getValidoAlGuardar() {
      return validoAlGuardar;
   }

   public void setValidoAlGuardar(java.lang.String validoAlGuardar) {
      this.validoAlGuardar = validoAlGuardar;
   }

   @Column(name = "VALIDO_AL_ACTUALIZAR")
   public java.lang.String getValidoAlActualizar() {
      return validoAlActualizar;
   }

   public void setValidoAlActualizar(java.lang.String validoAlActualizar) {
      this.validoAlActualizar = validoAlActualizar;
   }

   @Column(name = "SUBTIPO_ASIENTO_ID")
   public java.lang.Long getSubtipoAsientoId() {
      return subtipoAsientoId;
   }

   public void setSubtipoAsientoId(java.lang.Long subtipoAsientoId) {
      this.subtipoAsientoId = subtipoAsientoId;
   }
	public String toString() {
		return ToStringer.toString((EventoContableIf)this);
	}
}
