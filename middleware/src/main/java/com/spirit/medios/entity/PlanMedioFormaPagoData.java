package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PlanMedioFormaPagoData implements PlanMedioFormaPagoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long pedidoId;

   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   private java.lang.Long planMedioId;

   public java.lang.Long getPlanMedioId() {
      return planMedioId;
   }

   public void setPlanMedioId(java.lang.Long planMedioId) {
      this.planMedioId = planMedioId;
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

   private java.lang.String tipoFormaPago;

   public java.lang.String getTipoFormaPago() {
      return tipoFormaPago;
   }

   public void setTipoFormaPago(java.lang.String tipoFormaPago) {
      this.tipoFormaPago = tipoFormaPago;
   }

   private java.lang.Long formaPagoId;

   public java.lang.Long getFormaPagoId() {
      return formaPagoId;
   }

   public void setFormaPagoId(java.lang.Long formaPagoId) {
      this.formaPagoId = formaPagoId;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long formaPagoCampanaProductoVersionId;

   public java.lang.Long getFormaPagoCampanaProductoVersionId() {
      return formaPagoCampanaProductoVersionId;
   }

   public void setFormaPagoCampanaProductoVersionId(java.lang.Long formaPagoCampanaProductoVersionId) {
      this.formaPagoCampanaProductoVersionId = formaPagoCampanaProductoVersionId;
   }
   public PlanMedioFormaPagoData() {
   }

   public PlanMedioFormaPagoData(com.spirit.medios.entity.PlanMedioFormaPagoIf value) {
      setId(value.getId());
      setPedidoId(value.getPedidoId());
      setPlanMedioId(value.getPlanMedioId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setTipoFormaPago(value.getTipoFormaPago());
      setFormaPagoId(value.getFormaPagoId());
      setEstado(value.getEstado());
      setFormaPagoCampanaProductoVersionId(value.getFormaPagoCampanaProductoVersionId());
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
		return ToStringer.toString((PlanMedioFormaPagoIf)this);
	}
}
