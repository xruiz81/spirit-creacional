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

@Table(name = "MOVIMIENTO")
@Entity
public class MovimientoEJB implements Serializable, MovimientoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipodocumentoId;
   private java.lang.String codigo;
   private java.lang.Long bodegaId;
   private java.lang.Long bodegarefId;
   private java.lang.Long ordencompraId;
   private java.lang.Long compraId;
   private java.lang.Long referenciaId;
   private java.sql.Timestamp fechaCreacion;
   private java.sql.Timestamp fechaDocumento;
   private java.math.BigDecimal costo;
   private java.math.BigDecimal precio;
   private java.lang.String preimpreso;
   private java.lang.Long usuarioId;
   private java.lang.Long usuarioautId;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.Long tipodocumentoOrigenId;

   public MovimientoEJB() {
   }

   public MovimientoEJB(com.spirit.inventario.entity.MovimientoIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setBodegaId(value.getBodegaId());
      setBodegarefId(value.getBodegarefId());
      setOrdencompraId(value.getOrdencompraId());
      setCompraId(value.getCompraId());
      setReferenciaId(value.getReferenciaId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaDocumento(value.getFechaDocumento());
      setCosto(value.getCosto());
      setPrecio(value.getPrecio());
      setPreimpreso(value.getPreimpreso());
      setUsuarioId(value.getUsuarioId());
      setUsuarioautId(value.getUsuarioautId());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setTipodocumentoOrigenId(value.getTipodocumentoOrigenId());
   }

   public java.lang.Long create(com.spirit.inventario.entity.MovimientoIf value) {
      setId(value.getId());
      setTipodocumentoId(value.getTipodocumentoId());
      setCodigo(value.getCodigo());
      setBodegaId(value.getBodegaId());
      setBodegarefId(value.getBodegarefId());
      setOrdencompraId(value.getOrdencompraId());
      setCompraId(value.getCompraId());
      setReferenciaId(value.getReferenciaId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaDocumento(value.getFechaDocumento());
      setCosto(value.getCosto());
      setPrecio(value.getPrecio());
      setPreimpreso(value.getPreimpreso());
      setUsuarioId(value.getUsuarioId());
      setUsuarioautId(value.getUsuarioautId());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setTipodocumentoOrigenId(value.getTipodocumentoOrigenId());
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

   @Column(name = "TIPODOCUMENTO_ID")
   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "BODEGA_ID")
   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   @Column(name = "BODEGAREF_ID")
   public java.lang.Long getBodegarefId() {
      return bodegarefId;
   }

   public void setBodegarefId(java.lang.Long bodegarefId) {
      this.bodegarefId = bodegarefId;
   }

   @Column(name = "ORDENCOMPRA_ID")
   public java.lang.Long getOrdencompraId() {
      return ordencompraId;
   }

   public void setOrdencompraId(java.lang.Long ordencompraId) {
      this.ordencompraId = ordencompraId;
   }

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   @Column(name = "REFERENCIA_ID")
   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_DOCUMENTO")
   public java.sql.Timestamp getFechaDocumento() {
      return fechaDocumento;
   }

   public void setFechaDocumento(java.sql.Timestamp fechaDocumento) {
      this.fechaDocumento = fechaDocumento;
   }

   @Column(name = "COSTO")
   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   @Column(name = "PRECIO")
   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   @Column(name = "PREIMPRESO")
   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "USUARIOAUT_ID")
   public java.lang.Long getUsuarioautId() {
      return usuarioautId;
   }

   public void setUsuarioautId(java.lang.Long usuarioautId) {
      this.usuarioautId = usuarioautId;
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

   @Column(name = "TIPODOCUMENTO_ORIGEN_ID")
   public java.lang.Long getTipodocumentoOrigenId() {
      return tipodocumentoOrigenId;
   }

   public void setTipodocumentoOrigenId(java.lang.Long tipodocumentoOrigenId) {
      this.tipodocumentoOrigenId = tipodocumentoOrigenId;
   }
	public String toString() {
		return ToStringer.toString((MovimientoIf)this);
	}
}
