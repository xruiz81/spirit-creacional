package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PERSONALIZACION_DISENIO")
@Entity
public class PersonalizacionDisenioEJB implements Serializable, PersonalizacionDisenioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long tipoProductoId;
   private java.lang.Long lineaId;
   private java.lang.Long personalizacionColorId;
   private java.lang.String miniDisplay;
   private java.lang.String front;
   private java.lang.String back;
   private java.lang.Long empresaId;
   private java.lang.String etiqueta;

   public PersonalizacionDisenioEJB() {
   }

   public PersonalizacionDisenioEJB(com.spirit.facturacion.entity.PersonalizacionDisenioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipoProductoId(value.getTipoProductoId());
      setLineaId(value.getLineaId());
      setPersonalizacionColorId(value.getPersonalizacionColorId());
      setMiniDisplay(value.getMiniDisplay());
      setFront(value.getFront());
      setBack(value.getBack());
      setEmpresaId(value.getEmpresaId());
      setEtiqueta(value.getEtiqueta());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.PersonalizacionDisenioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipoProductoId(value.getTipoProductoId());
      setLineaId(value.getLineaId());
      setPersonalizacionColorId(value.getPersonalizacionColorId());
      setMiniDisplay(value.getMiniDisplay());
      setFront(value.getFront());
      setBack(value.getBack());
      setEmpresaId(value.getEmpresaId());
      setEtiqueta(value.getEtiqueta());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "TIPO_PRODUCTO_ID")
   public java.lang.Long getTipoProductoId() {
      return tipoProductoId;
   }

   public void setTipoProductoId(java.lang.Long tipoProductoId) {
      this.tipoProductoId = tipoProductoId;
   }

   @Column(name = "LINEA_ID")
   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   @Column(name = "PERSONALIZACION_COLOR_ID")
   public java.lang.Long getPersonalizacionColorId() {
      return personalizacionColorId;
   }

   public void setPersonalizacionColorId(java.lang.Long personalizacionColorId) {
      this.personalizacionColorId = personalizacionColorId;
   }

   @Column(name = "MINI_DISPLAY")
   public java.lang.String getMiniDisplay() {
      return miniDisplay;
   }

   public void setMiniDisplay(java.lang.String miniDisplay) {
      this.miniDisplay = miniDisplay;
   }

   @Column(name = "FRONT")
   public java.lang.String getFront() {
      return front;
   }

   public void setFront(java.lang.String front) {
      this.front = front;
   }

   @Column(name = "BACK")
   public java.lang.String getBack() {
      return back;
   }

   public void setBack(java.lang.String back) {
      this.back = back;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "ETIQUETA")
   public java.lang.String getEtiqueta() {
      return etiqueta;
   }

   public void setEtiqueta(java.lang.String etiqueta) {
      this.etiqueta = etiqueta;
   }
	public String toString() {
		return ToStringer.toString((PersonalizacionDisenioIf)this);
	}
}
