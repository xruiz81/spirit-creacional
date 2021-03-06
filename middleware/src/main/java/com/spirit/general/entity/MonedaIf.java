package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MonedaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getSimbolo();

   void setSimbolo(java.lang.String simbolo);

   java.lang.String getPlural();

   void setPlural(java.lang.String plural);

   java.lang.String getSufijoCantidadLetras();

   void setSufijoCantidadLetras(java.lang.String sufijoCantidadLetras);

   java.lang.String getPredeterminada();

   void setPredeterminada(java.lang.String predeterminada);


}
