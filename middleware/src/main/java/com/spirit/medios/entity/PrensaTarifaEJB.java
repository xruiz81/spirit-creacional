package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PRENSA_TARIFA")
@Entity
public class PrensaTarifaEJB implements Serializable, PrensaTarifaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteId;
   private java.lang.Long prensaSeccionId;
   private java.lang.Long prensaUbicacionId;
   private java.lang.Long prensaFormatoId;
   private java.lang.String codigo;
   private java.lang.String color;
   private java.lang.String dia;
   private java.lang.String tarifaCalculada;
   private java.math.BigDecimal tarifa;
   private java.math.BigDecimal recargo;
   private java.lang.String operacion;

   public PrensaTarifaEJB() {
   }

   public PrensaTarifaEJB(com.spirit.medios.entity.PrensaTarifaIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setPrensaSeccionId(value.getPrensaSeccionId());
      setPrensaUbicacionId(value.getPrensaUbicacionId());
      setPrensaFormatoId(value.getPrensaFormatoId());
      setCodigo(value.getCodigo());
      setColor(value.getColor());
      setDia(value.getDia());
      setTarifaCalculada(value.getTarifaCalculada());
      setTarifa(value.getTarifa());
      setRecargo(value.getRecargo());
      setOperacion(value.getOperacion());
   }

   public java.lang.Long create(com.spirit.medios.entity.PrensaTarifaIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setPrensaSeccionId(value.getPrensaSeccionId());
      setPrensaUbicacionId(value.getPrensaUbicacionId());
      setPrensaFormatoId(value.getPrensaFormatoId());
      setCodigo(value.getCodigo());
      setColor(value.getColor());
      setDia(value.getDia());
      setTarifaCalculada(value.getTarifaCalculada());
      setTarifa(value.getTarifa());
      setRecargo(value.getRecargo());
      setOperacion(value.getOperacion());
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

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "PRENSA_SECCION_ID")
   public java.lang.Long getPrensaSeccionId() {
      return prensaSeccionId;
   }

   public void setPrensaSeccionId(java.lang.Long prensaSeccionId) {
      this.prensaSeccionId = prensaSeccionId;
   }

   @Column(name = "PRENSA_UBICACION_ID")
   public java.lang.Long getPrensaUbicacionId() {
      return prensaUbicacionId;
   }

   public void setPrensaUbicacionId(java.lang.Long prensaUbicacionId) {
      this.prensaUbicacionId = prensaUbicacionId;
   }

   @Column(name = "PRENSA_FORMATO_ID")
   public java.lang.Long getPrensaFormatoId() {
      return prensaFormatoId;
   }

   public void setPrensaFormatoId(java.lang.Long prensaFormatoId) {
      this.prensaFormatoId = prensaFormatoId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "COLOR")
   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   @Column(name = "DIA")
   public java.lang.String getDia() {
      return dia;
   }

   public void setDia(java.lang.String dia) {
      this.dia = dia;
   }

   @Column(name = "TARIFA_CALCULADA")
   public java.lang.String getTarifaCalculada() {
      return tarifaCalculada;
   }

   public void setTarifaCalculada(java.lang.String tarifaCalculada) {
      this.tarifaCalculada = tarifaCalculada;
   }

   @Column(name = "TARIFA")
   public java.math.BigDecimal getTarifa() {
      return tarifa;
   }

   public void setTarifa(java.math.BigDecimal tarifa) {
      this.tarifa = tarifa;
   }

   @Column(name = "RECARGO")
   public java.math.BigDecimal getRecargo() {
      return recargo;
   }

   public void setRecargo(java.math.BigDecimal recargo) {
      this.recargo = recargo;
   }

   @Column(name = "OPERACION")
   public java.lang.String getOperacion() {
      return operacion;
   }

   public void setOperacion(java.lang.String operacion) {
      this.operacion = operacion;
   }
	public String toString() {
		return ToStringer.toString((PrensaTarifaIf)this);
	}
}
