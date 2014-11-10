package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PresupuestosData implements PresupuestosIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String tipo;

   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   private java.lang.Long clienteOficinaId;

   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   private java.lang.String concepto;

   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   private java.sql.Timestamp fechaAprobacion;

   public java.sql.Timestamp getFechaAprobacion() {
      return fechaAprobacion;
   }

   public void setFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
      this.fechaAprobacion = fechaAprobacion;
   }

   private java.math.BigDecimal subtotal;

   public java.math.BigDecimal getSubtotal() {
      return subtotal;
   }

   public void setSubtotal(java.math.BigDecimal subtotal) {
      this.subtotal = subtotal;
   }

   private java.math.BigDecimal descuento;

   public java.math.BigDecimal getDescuento() {
      return descuento;
   }

   public void setDescuento(java.math.BigDecimal descuento) {
      this.descuento = descuento;
   }

   private java.math.BigDecimal comision;

   public java.math.BigDecimal getComision() {
      return comision;
   }

   public void setComision(java.math.BigDecimal comision) {
      this.comision = comision;
   }

   private java.math.BigDecimal iva;

   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   private java.math.BigDecimal total;

   public java.math.BigDecimal getTotal() {
      return total;
   }

   public void setTotal(java.math.BigDecimal total) {
      this.total = total;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String prepago;

   public java.lang.String getPrepago() {
      return prepago;
   }

   public void setPrepago(java.lang.String prepago) {
      this.prepago = prepago;
   }

   private java.lang.String revision;

   public java.lang.String getRevision() {
      return revision;
   }

   public void setRevision(java.lang.String revision) {
      this.revision = revision;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }
   public PresupuestosData() {
   }

   public PresupuestosData(com.spirit.medios.entity.PresupuestosIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipo(value.getTipo());
      setClienteOficinaId(value.getClienteOficinaId());
      setConcepto(value.getConcepto());
      setFechaAprobacion(value.getFechaAprobacion());
      setSubtotal(value.getSubtotal());
      setDescuento(value.getDescuento());
      setComision(value.getComision());
      setIva(value.getIva());
      setTotal(value.getTotal());
      setEstado(value.getEstado());
      setPrepago(value.getPrepago());
      setRevision(value.getRevision());
      setFecha(value.getFecha());
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
		return ToStringer.toString((PresupuestosIf)this);
	}
}
