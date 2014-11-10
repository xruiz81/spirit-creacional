package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PersonalizacionDisenioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getTipoProductoId();

   void setTipoProductoId(java.lang.Long tipoProductoId);

   java.lang.Long getLineaId();

   void setLineaId(java.lang.Long lineaId);

   java.lang.Long getPersonalizacionColorId();

   void setPersonalizacionColorId(java.lang.Long personalizacionColorId);

   java.lang.String getMiniDisplay();

   void setMiniDisplay(java.lang.String miniDisplay);

   java.lang.String getFront();

   void setFront(java.lang.String front);

   java.lang.String getBack();

   void setBack(java.lang.String back);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getEtiqueta();

   void setEtiqueta(java.lang.String etiqueta);


}
