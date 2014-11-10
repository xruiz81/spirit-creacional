package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PlantillaData implements PlantillaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long eventocontableId;

   public java.lang.Long getEventocontableId() {
      return eventocontableId;
   }

   public void setEventocontableId(java.lang.Long eventocontableId) {
      this.eventocontableId = eventocontableId;
   }

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   private java.lang.String debehaber;

   public java.lang.String getDebehaber() {
      return debehaber;
   }

   public void setDebehaber(java.lang.String debehaber) {
      this.debehaber = debehaber;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.lang.String glosa;

   public java.lang.String getGlosa() {
      return glosa;
   }

   public void setGlosa(java.lang.String glosa) {
      this.glosa = glosa;
   }

   private java.lang.String nemonico;

   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }

   private java.lang.String formula;

   public java.lang.String getFormula() {
      return formula;
   }

   public void setFormula(java.lang.String formula) {
      this.formula = formula;
   }

   private java.lang.String tipoEntidad;

   public java.lang.String getTipoEntidad() {
      return tipoEntidad;
   }

   public void setTipoEntidad(java.lang.String tipoEntidad) {
      this.tipoEntidad = tipoEntidad;
   }

   private java.lang.Long cuentaPredeterminadaId;

   public java.lang.Long getCuentaPredeterminadaId() {
      return cuentaPredeterminadaId;
   }

   public void setCuentaPredeterminadaId(java.lang.Long cuentaPredeterminadaId) {
      this.cuentaPredeterminadaId = cuentaPredeterminadaId;
   }
   public PlantillaData() {
   }

   public PlantillaData(com.spirit.contabilidad.entity.PlantillaIf value) {
      setId(value.getId());
      setEventocontableId(value.getEventocontableId());
      setCuentaId(value.getCuentaId());
      setDebehaber(value.getDebehaber());
      setReferencia(value.getReferencia());
      setGlosa(value.getGlosa());
      setNemonico(value.getNemonico());
      setFormula(value.getFormula());
      setTipoEntidad(value.getTipoEntidad());
      setCuentaPredeterminadaId(value.getCuentaPredeterminadaId());
   }



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((PlantillaIf)this);
	}
}
