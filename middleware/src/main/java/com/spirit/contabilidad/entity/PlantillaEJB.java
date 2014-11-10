package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PLANTILLA")
@Entity
public class PlantillaEJB implements Serializable, PlantillaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long eventocontableId;
   private java.lang.Long cuentaId;
   private java.lang.String debehaber;
   private java.lang.String referencia;
   private java.lang.String glosa;
   private java.lang.String nemonico;
   private java.lang.String formula;
   private java.lang.String tipoEntidad;
   private java.lang.Long cuentaPredeterminadaId;

   public PlantillaEJB() {
   }

   public PlantillaEJB(com.spirit.contabilidad.entity.PlantillaIf value) {
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

   public java.lang.Long create(com.spirit.contabilidad.entity.PlantillaIf value) {
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
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "EVENTOCONTABLE_ID")
   public java.lang.Long getEventocontableId() {
      return eventocontableId;
   }

   public void setEventocontableId(java.lang.Long eventocontableId) {
      this.eventocontableId = eventocontableId;
   }

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   @Column(name = "DEBEHABER")
   public java.lang.String getDebehaber() {
      return debehaber;
   }

   public void setDebehaber(java.lang.String debehaber) {
      this.debehaber = debehaber;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "GLOSA")
   public java.lang.String getGlosa() {
      return glosa;
   }

   public void setGlosa(java.lang.String glosa) {
      this.glosa = glosa;
   }

   @Column(name = "NEMONICO")
   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }

   @Column(name = "FORMULA")
   public java.lang.String getFormula() {
      return formula;
   }

   public void setFormula(java.lang.String formula) {
      this.formula = formula;
   }

   @Column(name = "TIPO_ENTIDAD")
   public java.lang.String getTipoEntidad() {
      return tipoEntidad;
   }

   public void setTipoEntidad(java.lang.String tipoEntidad) {
      this.tipoEntidad = tipoEntidad;
   }

   @Column(name = "CUENTA_PREDETERMINADA_ID")
   public java.lang.Long getCuentaPredeterminadaId() {
      return cuentaPredeterminadaId;
   }

   public void setCuentaPredeterminadaId(java.lang.Long cuentaPredeterminadaId) {
      this.cuentaPredeterminadaId = cuentaPredeterminadaId;
   }
	public String toString() {
		return ToStringer.toString((PlantillaIf)this);
	}
}
