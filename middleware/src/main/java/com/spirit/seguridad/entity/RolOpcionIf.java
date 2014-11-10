package com.spirit.seguridad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolOpcionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getRolId();

   void setRolId(java.lang.Long rolId);

   java.lang.Long getMenuId();

   void setMenuId(java.lang.Long menuId);

   java.lang.String getNinguno();

   void setNinguno(java.lang.String ninguno);

   java.lang.String getGrabarActualizar();

   void setGrabarActualizar(java.lang.String grabarActualizar);

   java.lang.String getBorrar();

   void setBorrar(java.lang.String borrar);

   java.lang.String getConsultar();

   void setConsultar(java.lang.String consultar);

   java.lang.String getAutorizar();

   void setAutorizar(java.lang.String autorizar);

   java.lang.String getImprimir();

   void setImprimir(java.lang.String imprimir);

   java.lang.String getGenerarGrafico();

   void setGenerarGrafico(java.lang.String generarGrafico);

   java.lang.String getDuplicar();

   void setDuplicar(java.lang.String duplicar);


}
