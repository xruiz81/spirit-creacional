package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CajaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.String getTurno();

   void setTurno(java.lang.String turno);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
