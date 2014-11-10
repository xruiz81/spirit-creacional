package com.spirit.seguridad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getStatus();

   void setStatus(java.lang.String status);

   java.lang.String getTipoRolUsuario();

   void setTipoRolUsuario(java.lang.String tipoRolUsuario);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
