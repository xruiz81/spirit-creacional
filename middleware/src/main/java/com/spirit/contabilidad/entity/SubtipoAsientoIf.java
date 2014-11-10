package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SubtipoAsientoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getTipoId();

   void setTipoId(java.lang.Long tipoId);

   java.lang.Long getOrden();

   void setOrden(java.lang.Long orden);

   java.lang.String getStatus();

   void setStatus(java.lang.String status);

   java.lang.Long getTipo();

   void setTipo(java.lang.Long tipo);


}
