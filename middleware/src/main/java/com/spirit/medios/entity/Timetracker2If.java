package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface Timetracker2If extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getActividad();

   void setActividad(java.lang.String actividad);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
