package com.spirit.sri.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriIvaServicioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Integer getCodigo();

   void setCodigo(java.lang.Integer codigo);

   java.lang.String getDescripcionPorcentaje();

   void setDescripcionPorcentaje(java.lang.String descripcionPorcentaje);


}
