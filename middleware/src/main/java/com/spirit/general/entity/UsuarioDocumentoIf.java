package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface UsuarioDocumentoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.String getPermisoImpresion();

   void setPermisoImpresion(java.lang.String permisoImpresion);

   java.lang.String getPermisoRegistro();

   void setPermisoRegistro(java.lang.String permisoRegistro);

   java.lang.String getPermisoBorrado();

   void setPermisoBorrado(java.lang.String permisoBorrado);

   java.lang.String getPermisoAutorizar();

   void setPermisoAutorizar(java.lang.String permisoAutorizar);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
