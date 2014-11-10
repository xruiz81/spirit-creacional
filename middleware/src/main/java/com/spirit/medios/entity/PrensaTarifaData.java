package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PrensaTarifaData implements PrensaTarifaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.lang.Long prensaSeccionId;

   public java.lang.Long getPrensaSeccionId() {
      return prensaSeccionId;
   }

   public void setPrensaSeccionId(java.lang.Long prensaSeccionId) {
      this.prensaSeccionId = prensaSeccionId;
   }

   private java.lang.Long prensaUbicacionId;

   public java.lang.Long getPrensaUbicacionId() {
      return prensaUbicacionId;
   }

   public void setPrensaUbicacionId(java.lang.Long prensaUbicacionId) {
      this.prensaUbicacionId = prensaUbicacionId;
   }

   private java.lang.Long prensaFormatoId;

   public java.lang.Long getPrensaFormatoId() {
      return prensaFormatoId;
   }

   public void setPrensaFormatoId(java.lang.Long prensaFormatoId) {
      this.prensaFormatoId = prensaFormatoId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String color;

   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   private java.lang.String dia;

   public java.lang.String getDia() {
      return dia;
   }

   public void setDia(java.lang.String dia) {
      this.dia = dia;
   }

   private java.lang.String tarifaCalculada;

   public java.lang.String getTarifaCalculada() {
      return tarifaCalculada;
   }

   public void setTarifaCalculada(java.lang.String tarifaCalculada) {
      this.tarifaCalculada = tarifaCalculada;
   }

   private java.math.BigDecimal tarifa;

   public java.math.BigDecimal getTarifa() {
      return tarifa;
   }

   public void setTarifa(java.math.BigDecimal tarifa) {
      this.tarifa = tarifa;
   }

   private java.math.BigDecimal recargo;

   public java.math.BigDecimal getRecargo() {
      return recargo;
   }

   public void setRecargo(java.math.BigDecimal recargo) {
      this.recargo = recargo;
   }

   private java.lang.String operacion;

   public java.lang.String getOperacion() {
      return operacion;
   }

   public void setOperacion(java.lang.String operacion) {
      this.operacion = operacion;
   }
   public PrensaTarifaData() {
   }

   public PrensaTarifaData(com.spirit.medios.entity.PrensaTarifaIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setPrensaSeccionId(value.getPrensaSeccionId());
      setPrensaUbicacionId(value.getPrensaUbicacionId());
      setPrensaFormatoId(value.getPrensaFormatoId());
      setCodigo(value.getCodigo());
      setColor(value.getColor());
      setDia(value.getDia());
      setTarifaCalculada(value.getTarifaCalculada());
      setTarifa(value.getTarifa());
      setRecargo(value.getRecargo());
      setOperacion(value.getOperacion());
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
		return ToStringer.toString((PrensaTarifaIf)this);
	}
}
