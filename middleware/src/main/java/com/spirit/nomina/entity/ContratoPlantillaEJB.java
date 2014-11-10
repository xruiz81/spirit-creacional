package com.spirit.nomina.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CONTRATO_PLANTILLA")
@Entity
public class ContratoPlantillaEJB implements Serializable, ContratoPlantillaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoContratoId;
   private java.lang.String codigo;
   private java.lang.String observacion;

   public ContratoPlantillaEJB() {
   }

   public ContratoPlantillaEJB(com.spirit.nomina.entity.ContratoPlantillaIf value) {
      setId(value.getId());
      setTipoContratoId(value.getTipoContratoId());
      setCodigo(value.getCodigo());
      setObservacion(value.getObservacion());
   }

   public java.lang.Long create(com.spirit.nomina.entity.ContratoPlantillaIf value) {
      setId(value.getId());
      setTipoContratoId(value.getTipoContratoId());
      setCodigo(value.getCodigo());
      setObservacion(value.getObservacion());
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

   @Column(name = "TIPO_CONTRATO_ID")
   public java.lang.Long getTipoContratoId() {
      return tipoContratoId;
   }

   public void setTipoContratoId(java.lang.Long tipoContratoId) {
      this.tipoContratoId = tipoContratoId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }
	public String toString() {
		return ToStringer.toString((ContratoPlantillaIf)this);
	}
}
