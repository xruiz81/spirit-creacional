package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PersonalizacionDisenioData implements PersonalizacionDisenioIf, Serializable    {


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

   private java.lang.Long tipoProductoId;

   public java.lang.Long getTipoProductoId() {
      return tipoProductoId;
   }

   public void setTipoProductoId(java.lang.Long tipoProductoId) {
      this.tipoProductoId = tipoProductoId;
   }

   private java.lang.Long lineaId;

   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   private java.lang.Long personalizacionColorId;

   public java.lang.Long getPersonalizacionColorId() {
      return personalizacionColorId;
   }

   public void setPersonalizacionColorId(java.lang.Long personalizacionColorId) {
      this.personalizacionColorId = personalizacionColorId;
   }

   private java.lang.String miniDisplay;

   public java.lang.String getMiniDisplay() {
      return miniDisplay;
   }

   public void setMiniDisplay(java.lang.String miniDisplay) {
      this.miniDisplay = miniDisplay;
   }

   private java.lang.String front;

   public java.lang.String getFront() {
      return front;
   }

   public void setFront(java.lang.String front) {
      this.front = front;
   }

   private java.lang.String back;

   public java.lang.String getBack() {
      return back;
   }

   public void setBack(java.lang.String back) {
      this.back = back;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String etiqueta;

   public java.lang.String getEtiqueta() {
      return etiqueta;
   }

   public void setEtiqueta(java.lang.String etiqueta) {
      this.etiqueta = etiqueta;
   }
   public PersonalizacionDisenioData() {
   }

   public PersonalizacionDisenioData(com.spirit.facturacion.entity.PersonalizacionDisenioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipoProductoId(value.getTipoProductoId());
      setLineaId(value.getLineaId());
      setPersonalizacionColorId(value.getPersonalizacionColorId());
      setMiniDisplay(value.getMiniDisplay());
      setFront(value.getFront());
      setBack(value.getBack());
      setEmpresaId(value.getEmpresaId());
      setEtiqueta(value.getEtiqueta());
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
		return ToStringer.toString((PersonalizacionDisenioIf)this);
	}
}
