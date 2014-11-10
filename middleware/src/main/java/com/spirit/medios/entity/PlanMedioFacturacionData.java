package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PlanMedioFacturacionData implements PlanMedioFacturacionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long planMedioId;

   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
   }

   private java.lang.Long comercialId;

   public java.lang.Long getComercialId() {
      return comercialId;
   }

   public void setComercialId(java.lang.Long comercialId) {
      this.comercialId = comercialId;
   }

   private java.sql.Timestamp fechaInicio;

   public java.sql.Timestamp getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Timestamp fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Timestamp fechaFin;

   public java.sql.Timestamp getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Timestamp fechaFin) {
      this.fechaFin = fechaFin;
   }

   private java.lang.Long pedidoId;

   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   private java.lang.String canje;

   public java.lang.String getCanje() {
      return canje;
   }

   public void setCanje(java.lang.String canje) {
      this.canje = canje;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.math.BigDecimal porcentajeCanje;

   public java.math.BigDecimal getPorcentajeCanje() {
      return porcentajeCanje;
   }

   public void setPorcentajeCanje(java.math.BigDecimal porcentajeCanje) {
      this.porcentajeCanje = porcentajeCanje;
   }

   private java.lang.Long ordenMedioId;

   public java.lang.Long getOrdenMedioId() {
      return ordenMedioId;
   }

   public void setOrdenMedioId(java.lang.Long ordenMedioId) {
      this.ordenMedioId = ordenMedioId;
   }

   private java.lang.Long ordenMedioDetalleId;

   public java.lang.Long getOrdenMedioDetalleId() {
      return ordenMedioDetalleId;
   }

   public void setOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) {
      this.ordenMedioDetalleId = ordenMedioDetalleId;
   }

   private java.lang.Long ordenMedioDetalleMapaId;

   public java.lang.Long getOrdenMedioDetalleMapaId() {
      return ordenMedioDetalleMapaId;
   }

   public void setOrdenMedioDetalleMapaId(java.lang.Long ordenMedioDetalleMapaId) {
      this.ordenMedioDetalleMapaId = ordenMedioDetalleMapaId;
   }

   private java.lang.Long productoClienteId;

   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.Long campanaProductoVersionId;

   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }
   public PlanMedioFacturacionData() {
   }

   public PlanMedioFacturacionData(com.spirit.medios.entity.PlanMedioFacturacionIf value) {
      setId(value.getId());
      setPlanMedioId(value.getPlanMedioId());
      setComercialId(value.getComercialId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setPedidoId(value.getPedidoId());
      setCanje(value.getCanje());
      setProveedorId(value.getProveedorId());
      setEstado(value.getEstado());
      setPorcentajeCanje(value.getPorcentajeCanje());
      setOrdenMedioId(value.getOrdenMedioId());
      setOrdenMedioDetalleId(value.getOrdenMedioDetalleId());
      setOrdenMedioDetalleMapaId(value.getOrdenMedioDetalleMapaId());
      setProductoClienteId(value.getProductoClienteId());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
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
		return ToStringer.toString((PlanMedioFacturacionIf)this);
	}
}
