package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CuentasPorPagarData implements CuentasPorPagarIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long carteraId;

   public java.lang.Long getCarteraId() {
      return carteraId;
   }

   public void setCarteraId(java.lang.Long carteraId) {
      this.carteraId = carteraId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.sql.Timestamp fechaEmision;

   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.lang.String comentario;

   public java.lang.String getComentario() {
      return comentario;
   }

   public void setComentario(java.lang.String comentario) {
      this.comentario = comentario;
   }

   private java.lang.Long tipodocumentoId;

   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   private java.lang.Long referenciaId;

   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.math.BigDecimal saldo;

   public java.math.BigDecimal getSaldo() {
      return saldo;
   }

   public void setSaldo(java.math.BigDecimal saldo) {
      this.saldo = saldo;
   }

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   private java.sql.Timestamp fechaCompra;

   public java.sql.Timestamp getFechaCompra() {
      return fechaCompra;
   }

   public void setFechaCompra(java.sql.Timestamp fechaCompra) {
      this.fechaCompra = fechaCompra;
   }

   private java.lang.Long oficinaId;

   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   private java.lang.String razonSocial;

   public java.lang.String getRazonSocial() {
      return razonSocial;
   }

   public void setRazonSocial(java.lang.String razonSocial) {
      this.razonSocial = razonSocial;
   }

   private java.lang.String identificacion;

   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   private java.lang.Long proveedorOficinaId;

   public java.lang.Long getProveedorOficinaId() {
      return proveedorOficinaId;
   }

   public void setProveedorOficinaId(java.lang.Long proveedorOficinaId) {
      this.proveedorOficinaId = proveedorOficinaId;
   }

   private java.lang.Long tipoProveedorId;

   public java.lang.Long getTipoProveedorId() {
      return tipoProveedorId;
   }

   public void setTipoProveedorId(java.lang.Long tipoProveedorId) {
      this.tipoProveedorId = tipoProveedorId;
   }

   private java.lang.String codigoTipoProveedor;

   public java.lang.String getCodigoTipoProveedor() {
      return codigoTipoProveedor;
   }

   public void setCodigoTipoProveedor(java.lang.String codigoTipoProveedor) {
      this.codigoTipoProveedor = codigoTipoProveedor;
   }

   private java.lang.String tipoProveedor;

   public java.lang.String getTipoProveedor() {
      return tipoProveedor;
   }

   public void setTipoProveedor(java.lang.String tipoProveedor) {
      this.tipoProveedor = tipoProveedor;
   }
   public CuentasPorPagarData() {
   }

   public CuentasPorPagarData(com.spirit.cartera.entity.CuentasPorPagarIf value) {
      setId(value.getId());
      setCarteraId(value.getCarteraId());
      setCodigo(value.getCodigo());
      setPreimpreso(value.getPreimpreso());
      setFechaEmision(value.getFechaEmision());
      setComentario(value.getComentario());
      setTipodocumentoId(value.getTipodocumentoId());
      setReferenciaId(value.getReferenciaId());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setCompraId(value.getCompraId());
      setFechaCompra(value.getFechaCompra());
      setOficinaId(value.getOficinaId());
      setObservacion(value.getObservacion());
      setProveedorId(value.getProveedorId());
      setRazonSocial(value.getRazonSocial());
      setIdentificacion(value.getIdentificacion());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setTipoProveedorId(value.getTipoProveedorId());
      setCodigoTipoProveedor(value.getCodigoTipoProveedor());
      setTipoProveedor(value.getTipoProveedor());
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
		return ToStringer.toString((CuentasPorPagarIf)this);
	}
}
