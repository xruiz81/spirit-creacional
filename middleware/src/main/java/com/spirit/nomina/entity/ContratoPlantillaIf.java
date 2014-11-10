package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoPlantillaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipoContratoId();

   void setTipoContratoId(java.lang.Long tipoContratoId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);


}
