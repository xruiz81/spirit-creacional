package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoResultadoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getOrden();

   void setOrden(java.lang.Long orden);

   java.lang.String getUtilidad();

   void setUtilidad(java.lang.String utilidad);

   java.lang.String getLeyendaResultado();

   void setLeyendaResultado(java.lang.String leyendaResultado);


}
