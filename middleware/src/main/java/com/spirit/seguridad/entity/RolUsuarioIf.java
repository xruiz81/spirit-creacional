package com.spirit.seguridad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolUsuarioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getRolId();

   void setRolId(java.lang.Long rolId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);


}
