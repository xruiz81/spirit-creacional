package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CuentaEntidadIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEntidadId();

   void setEntidadId(java.lang.Long entidadId);

   java.lang.String getTipoEntidad();

   void setTipoEntidad(java.lang.String tipoEntidad);

   java.lang.String getNemonico();

   void setNemonico(java.lang.String nemonico);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);


}
