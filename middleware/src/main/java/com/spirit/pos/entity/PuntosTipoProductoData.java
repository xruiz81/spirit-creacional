package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PuntosTipoProductoData implements PuntosTipoProductoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoProductoId;

   public java.lang.Long getTipoProductoId() {
      return tipoProductoId;
   }

   public void setTipoProductoId(java.lang.Long tipoProductoId) {
      this.tipoProductoId = tipoProductoId;
   }

   private java.lang.Long tarjetaTipoId;

   public java.lang.Long getTarjetaTipoId() {
      return tarjetaTipoId;
   }

   public void setTarjetaTipoId(java.lang.Long tarjetaTipoId) {
      this.tarjetaTipoId = tarjetaTipoId;
   }

   private java.math.BigDecimal puntosReferido;

   public java.math.BigDecimal getPuntosReferido() {
      return puntosReferido;
   }

   public void setPuntosReferido(java.math.BigDecimal puntosReferido) {
      this.puntosReferido = puntosReferido;
   }

   private java.math.BigDecimal puntosCompras;

   public java.math.BigDecimal getPuntosCompras() {
      return puntosCompras;
   }

   public void setPuntosCompras(java.math.BigDecimal puntosCompras) {
      this.puntosCompras = puntosCompras;
   }

   private java.math.BigDecimal porcentajeDineroReferido;

   public java.math.BigDecimal getPorcentajeDineroReferido() {
      return porcentajeDineroReferido;
   }

   public void setPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) {
      this.porcentajeDineroReferido = porcentajeDineroReferido;
   }

   private java.math.BigDecimal porcentajeDineroCompras;

   public java.math.BigDecimal getPorcentajeDineroCompras() {
      return porcentajeDineroCompras;
   }

   public void setPorcentajeDineroCompras(java.math.BigDecimal porcentajeDineroCompras) {
      this.porcentajeDineroCompras = porcentajeDineroCompras;
   }
   public PuntosTipoProductoData() {
   }

   public PuntosTipoProductoData(com.spirit.pos.entity.PuntosTipoProductoIf value) {
      setId(value.getId());
      setTipoProductoId(value.getTipoProductoId());
      setTarjetaTipoId(value.getTarjetaTipoId());
      setPuntosReferido(value.getPuntosReferido());
      setPuntosCompras(value.getPuntosCompras());
      setPorcentajeDineroReferido(value.getPorcentajeDineroReferido());
      setPorcentajeDineroCompras(value.getPorcentajeDineroCompras());
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
		return ToStringer.toString((PuntosTipoProductoIf)this);
	}
}
