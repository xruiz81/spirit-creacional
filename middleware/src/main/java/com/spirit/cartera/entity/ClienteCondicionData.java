package com.spirit.cartera.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ClienteCondicionData implements ClienteCondicionIf, Serializable    {


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

   private java.lang.Long subtipoordenId;

   public java.lang.Long getSubtipoordenId() {
      return subtipoordenId;
   }

   public void setSubtipoordenId(java.lang.Long subtipoordenId) {
      this.subtipoordenId = subtipoordenId;
   }

   private java.lang.Long formapagoId;

   public java.lang.Long getFormapagoId() {
      return formapagoId;
   }

   public void setFormapagoId(java.lang.Long formapagoId) {
      this.formapagoId = formapagoId;
   }

   private java.lang.String observaciones;

   public java.lang.String getObservaciones() {
      return observaciones;
   }

   public void setObservaciones(java.lang.String observaciones) {
      this.observaciones = observaciones;
   }

   private java.sql.Date fechaini;

   public java.sql.Date getFechaini() {
      return fechaini;
   }

   public void setFechaini(java.sql.Date fechaini) {
      this.fechaini = fechaini;
   }

   private java.sql.Date fechafin;

   public java.sql.Date getFechafin() {
      return fechafin;
   }

   public void setFechafin(java.sql.Date fechafin) {
      this.fechafin = fechafin;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
   public ClienteCondicionData() {
   }

   public ClienteCondicionData(com.spirit.cartera.entity.ClienteCondicionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setSubtipoordenId(value.getSubtipoordenId());
      setFormapagoId(value.getFormapagoId());
      setObservaciones(value.getObservaciones());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setCodigo(value.getCodigo());
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
		return ToStringer.toString((ClienteCondicionIf)this);
	}
}
