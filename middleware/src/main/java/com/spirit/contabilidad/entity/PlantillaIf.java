package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlantillaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEventocontableId();

   void setEventocontableId(java.lang.Long eventocontableId);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);

   java.lang.String getDebehaber();

   void setDebehaber(java.lang.String debehaber);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.lang.String getGlosa();

   void setGlosa(java.lang.String glosa);

   java.lang.String getNemonico();

   void setNemonico(java.lang.String nemonico);

   java.lang.String getFormula();

   void setFormula(java.lang.String formula);

   java.lang.String getTipoEntidad();

   void setTipoEntidad(java.lang.String tipoEntidad);

   java.lang.Long getCuentaPredeterminadaId();

   void setCuentaPredeterminadaId(java.lang.Long cuentaPredeterminadaId);


}
