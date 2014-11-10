package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class MovimientoData implements MovimientoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipodocumentoId;

   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.Long bodegaId;

   public java.lang.Long getBodegaId() {
      return bodegaId;
   }

   public void setBodegaId(java.lang.Long bodegaId) {
      this.bodegaId = bodegaId;
   }

   private java.lang.Long bodegarefId;

   public java.lang.Long getBodegarefId() {
      return bodegarefId;
   }

   public void setBodegarefId(java.lang.Long bodegarefId) {
      this.bodegarefId = bodegarefId;
   }

   private java.lang.Long ordencompraId;

   public java.lang.Long getOrdencompraId() {
      return ordencompraId;
   }

   public void setOrdencompraId(java.lang.Long ordencompraId) {
      this.ordencompraId = ordencompraId;
   }

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   private java.lang.Long referenciaId;

   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.sql.Timestamp fechaDocumento;

   public java.sql.Timestamp getFechaDocumento() {
      return fechaDocumento;
   }

   public void setFechaDocumento(java.sql.Timestamp fechaDocumento) {
      this.fechaDocumento = fechaDocumento;
   }

   private java.math.BigDecimal costo;

   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   private java.math.BigDecimal precio;

   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Long usuarioautId;

   public java.lang.Long getUsuarioautId() {
      return usuarioautId;
   }

   public void setUsuarioautId(java.lang.Long usuarioautId) {
      this.usuarioautId = usuarioautId;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long tipodocumentoOrigenId;

   public java.lang.Long getTipodocumentoOrigenId() {
      return tipodocumentoOrigenId;
   }

   public void setTipodocumentoOrigenId(java.lang.Long tipodocumentoOrigenId) {
      this.tipodocumentoOrigenId = tipodocumentoOrigenId;
   }
   public MovimientoData() {
   }

   public MovimientoData(com.spirit.inventario.entity.MovimientoIf value) {
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



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((MovimientoIf)this);
	}
}
