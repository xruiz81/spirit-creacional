package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface BancoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getCodigoMulticash();

   void setCodigoMulticash(java.lang.String codigoMulticash);


}
