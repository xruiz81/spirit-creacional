package com.spirit.sri.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class SriCamposFormularioData implements SriCamposFormularioIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String impuesto;

   public java.lang.String getImpuesto() {
      return impuesto;
   }

   public void setImpuesto(java.lang.String impuesto) {
      this.impuesto = impuesto;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String concepto;

   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String valor;

   public java.lang.String getValor() {
      return valor;
   }

   public void setValor(java.lang.String valor) {
      this.valor = valor;
   }
   public SriCamposFormularioData() {
   }

   public SriCamposFormularioData(com.spirit.sri.entity.SriCamposFormularioIf value) {
      setId(value.getId());
      setImpuesto(value.getImpuesto());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setValor(value.getValor());
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
		return ToStringer.toString((SriCamposFormularioIf)this);
	}
}
