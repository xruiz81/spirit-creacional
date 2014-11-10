package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface UsuarioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getUsuario();

   void setUsuario(java.lang.String usuario);

   java.lang.String getClave();

   void setClave(java.lang.String clave);

   java.lang.String getTipousuario();

   void setTipousuario(java.lang.String tipousuario);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getTipousuarioTimetracker();

   void setTipousuarioTimetracker(java.lang.String tipousuarioTimetracker);


}
