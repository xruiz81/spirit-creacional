package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class AsociacionTransaccionData implements AsociacionTransaccionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.sql.Timestamp fechaEmision;

   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.String tablaOrigen;

   public java.lang.String getTablaOrigen() {
      return tablaOrigen;
   }

   public void setTablaOrigen(java.lang.String tablaOrigen) {
      this.tablaOrigen = tablaOrigen;
   }

   private java.lang.Long tipoDocumentoOrigenId;

   public java.lang.Long getTipoDocumentoOrigenId() {
      return tipoDocumentoOrigenId;
   }

   public void setTipoDocumentoOrigenId(java.lang.Long tipoDocumentoOrigenId) {
      this.tipoDocumentoOrigenId = tipoDocumentoOrigenId;
   }

   private java.lang.Long transaccionOrigenId;

   public java.lang.Long getTransaccionOrigenId() {
      return transaccionOrigenId;
   }

   public void setTransaccionOrigenId(java.lang.Long transaccionOrigenId) {
      this.transaccionOrigenId = transaccionOrigenId;
   }

   private java.lang.String tablaDestino;

   public java.lang.String getTablaDestino() {
      return tablaDestino;
   }

   public void setTablaDestino(java.lang.String tablaDestino) {
      this.tablaDestino = tablaDestino;
   }

   private java.lang.Long tipoDocumentoDestinoId;

   public java.lang.Long getTipoDocumentoDestinoId() {
      return tipoDocumentoDestinoId;
   }

   public void setTipoDocumentoDestinoId(java.lang.Long tipoDocumentoDestinoId) {
      this.tipoDocumentoDestinoId = tipoDocumentoDestinoId;
   }

   private java.lang.Long transaccionDestinoId;

   public java.lang.Long getTransaccionDestinoId() {
      return transaccionDestinoId;
   }

   public void setTransaccionDestinoId(java.lang.Long transaccionDestinoId) {
      this.transaccionDestinoId = transaccionDestinoId;
   }
   public AsociacionTransaccionData() {
   }

   public AsociacionTransaccionData(com.spirit.cartera.entity.AsociacionTransaccionIf value) {
      setId(value.getId());
      setFechaEmision(value.getFechaEmision());
      setFechaCreacion(value.getFechaCreacion());
      setTablaOrigen(value.getTablaOrigen());
      setTipoDocumentoOrigenId(value.getTipoDocumentoOrigenId());
      setTransaccionOrigenId(value.getTransaccionOrigenId());
      setTablaDestino(value.getTablaDestino());
      setTipoDocumentoDestinoId(value.getTipoDocumentoDestinoId());
      setTransaccionDestinoId(value.getTransaccionDestinoId());
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
		return ToStringer.toString((AsociacionTransaccionIf)this);
	}
}
