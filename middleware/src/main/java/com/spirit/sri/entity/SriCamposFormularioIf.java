package com.spirit.sri.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriCamposFormularioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getImpuesto();

   void setImpuesto(java.lang.String impuesto);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getConcepto();

   void setConcepto(java.lang.String concepto);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getValor();

   void setValor(java.lang.String valor);


}
