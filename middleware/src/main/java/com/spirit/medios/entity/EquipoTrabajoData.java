package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EquipoTrabajoData implements EquipoTrabajoIf, Serializable    {


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

   private java.lang.Long tipoordenId;

   public java.lang.Long getTipoordenId() {
      return tipoordenId;
   }

   public void setTipoordenId(java.lang.Long tipoordenId) {
      this.tipoordenId = tipoordenId;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
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

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public EquipoTrabajoData() {
   }

   public EquipoTrabajoData(com.spirit.medios.entity.EquipoTrabajoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipoordenId(value.getTipoordenId());
      setEmpresaId(value.getEmpresaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setEstado(value.getEstado());
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
		return ToStringer.toString((EquipoTrabajoIf)this);
	}
}
