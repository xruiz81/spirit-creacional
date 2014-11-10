package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TarjetaTipoData implements TarjetaTipoIf, Serializable    {


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

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.Long padreId;

   public java.lang.Long getPadreId() {
      return padreId;
   }

   public void setPadreId(java.lang.Long padreId) {
      this.padreId = padreId;
   }

   private java.lang.String puntosDinero;

   public java.lang.String getPuntosDinero() {
      return puntosDinero;
   }

   public void setPuntosDinero(java.lang.String puntosDinero) {
      this.puntosDinero = puntosDinero;
   }

   private java.math.BigDecimal dsctoReferido;

   public java.math.BigDecimal getDsctoReferido() {
      return dsctoReferido;
   }

   public void setDsctoReferido(java.math.BigDecimal dsctoReferido) {
      this.dsctoReferido = dsctoReferido;
   }

   private java.math.BigDecimal dsctoPropietario;

   public java.math.BigDecimal getDsctoPropietario() {
      return dsctoPropietario;
   }

   public void setDsctoPropietario(java.math.BigDecimal dsctoPropietario) {
      this.dsctoPropietario = dsctoPropietario;
   }

   private java.math.BigDecimal porcentajeDineroReferido;

   public java.math.BigDecimal getPorcentajeDineroReferido() {
      return porcentajeDineroReferido;
   }

   public void setPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) {
      this.porcentajeDineroReferido = porcentajeDineroReferido;
   }

   private java.math.BigDecimal porcentajeDineroPropietario;

   public java.math.BigDecimal getPorcentajeDineroPropietario() {
      return porcentajeDineroPropietario;
   }

   public void setPorcentajeDineroPropietario(java.math.BigDecimal porcentajeDineroPropietario) {
      this.porcentajeDineroPropietario = porcentajeDineroPropietario;
   }

   private java.lang.Long statusSiguiente;

   public java.lang.Long getStatusSiguiente() {
      return statusSiguiente;
   }

   public void setStatusSiguiente(java.lang.Long statusSiguiente) {
      this.statusSiguiente = statusSiguiente;
   }

   private java.lang.Long statusAnterior;

   public java.lang.Long getStatusAnterior() {
      return statusAnterior;
   }

   public void setStatusAnterior(java.lang.Long statusAnterior) {
      this.statusAnterior = statusAnterior;
   }

   private java.math.BigDecimal puntosSubirStatus;

   public java.math.BigDecimal getPuntosSubirStatus() {
      return puntosSubirStatus;
   }

   public void setPuntosSubirStatus(java.math.BigDecimal puntosSubirStatus) {
      this.puntosSubirStatus = puntosSubirStatus;
   }

   private java.math.BigDecimal dineroSubirStatus;

   public java.math.BigDecimal getDineroSubirStatus() {
      return dineroSubirStatus;
   }

   public void setDineroSubirStatus(java.math.BigDecimal dineroSubirStatus) {
      this.dineroSubirStatus = dineroSubirStatus;
   }

   private java.math.BigDecimal puntosMantenerStatus;

   public java.math.BigDecimal getPuntosMantenerStatus() {
      return puntosMantenerStatus;
   }

   public void setPuntosMantenerStatus(java.math.BigDecimal puntosMantenerStatus) {
      this.puntosMantenerStatus = puntosMantenerStatus;
   }

   private java.math.BigDecimal dineroMantenerStatus;

   public java.math.BigDecimal getDineroMantenerStatus() {
      return dineroMantenerStatus;
   }

   public void setDineroMantenerStatus(java.math.BigDecimal dineroMantenerStatus) {
      this.dineroMantenerStatus = dineroMantenerStatus;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
   public TarjetaTipoData() {
   }

   public TarjetaTipoData(com.spirit.pos.entity.TarjetaTipoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setPadreId(value.getPadreId());
      setPuntosDinero(value.getPuntosDinero());
      setDsctoReferido(value.getDsctoReferido());
      setDsctoPropietario(value.getDsctoPropietario());
      setPorcentajeDineroReferido(value.getPorcentajeDineroReferido());
      setPorcentajeDineroPropietario(value.getPorcentajeDineroPropietario());
      setStatusSiguiente(value.getStatusSiguiente());
      setStatusAnterior(value.getStatusAnterior());
      setPuntosSubirStatus(value.getPuntosSubirStatus());
      setDineroSubirStatus(value.getDineroSubirStatus());
      setPuntosMantenerStatus(value.getPuntosMantenerStatus());
      setDineroMantenerStatus(value.getDineroMantenerStatus());
      setEmpresaId(value.getEmpresaId());
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
		return ToStringer.toString((TarjetaTipoIf)this);
	}
}
