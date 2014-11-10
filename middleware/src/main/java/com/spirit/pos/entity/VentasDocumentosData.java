package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class VentasDocumentosData implements VentasDocumentosIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long ventastrackId;

   public java.lang.Long getVentastrackId() {
      return ventastrackId;
   }

   public void setVentastrackId(java.lang.Long ventastrackId) {
      this.ventastrackId = ventastrackId;
   }

   private java.lang.String tablaNombre;

   public java.lang.String getTablaNombre() {
      return tablaNombre;
   }

   public void setTablaNombre(java.lang.String tablaNombre) {
      this.tablaNombre = tablaNombre;
   }

   private java.lang.Long tablaId;

   public java.lang.Long getTablaId() {
      return tablaId;
   }

   public void setTablaId(java.lang.Long tablaId) {
      this.tablaId = tablaId;
   }

   private java.lang.String revisado;

   public java.lang.String getRevisado() {
      return revisado;
   }

   public void setRevisado(java.lang.String revisado) {
      this.revisado = revisado;
   }

   private java.lang.String numDoc;

   public java.lang.String getNumDoc() {
      return numDoc;
   }

   public void setNumDoc(java.lang.String numDoc) {
      this.numDoc = numDoc;
   }
   public VentasDocumentosData() {
   }

   public VentasDocumentosData(com.spirit.pos.entity.VentasDocumentosIf value) {
      setId(value.getId());
      setVentastrackId(value.getVentastrackId());
      setTablaNombre(value.getTablaNombre());
      setTablaId(value.getTablaId());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
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
		return ToStringer.toString((VentasDocumentosIf)this);
	}
}
