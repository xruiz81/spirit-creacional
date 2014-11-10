package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriIdentifTransaccionData implements SriIdentifTransaccionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long idTipoTransaccion;

   public java.lang.Long getIdTipoTransaccion() {
      return idTipoTransaccion;
   }

   public void setIdTipoTransaccion(java.lang.Long idTipoTransaccion) {
      this.idTipoTransaccion = idTipoTransaccion;
   }

   private java.lang.Long idTipoIdentificacion;

   public java.lang.Long getIdTipoIdentificacion() {
      return idTipoIdentificacion;
   }

   public void setIdTipoIdentificacion(java.lang.Long idTipoIdentificacion) {
      this.idTipoIdentificacion = idTipoIdentificacion;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
   public SriIdentifTransaccionData() {
   }

   public SriIdentifTransaccionData(com.spirit.sri.entity.SriIdentifTransaccionIf value) {
      setId(value.getId());
      setIdTipoTransaccion(value.getIdTipoTransaccion());
      setIdTipoIdentificacion(value.getIdTipoIdentificacion());
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
		return ToStringer.toString((SriIdentifTransaccionIf)this);
	}
}
