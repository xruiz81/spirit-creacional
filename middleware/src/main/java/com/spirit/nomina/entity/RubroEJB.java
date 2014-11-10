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

@Table(name = "RUBRO")
@Entity
public class RubroEJB implements Serializable, RubroIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long empresaId;
   private java.lang.String frecuencia;
   private java.lang.String tipoRubro;
   private java.lang.String nombre;
   private java.lang.Long tiporolId;
   private java.sql.Date fecha;
   private java.lang.String politica;
   private java.lang.String modoOperacion;
   private java.lang.String pagoIndividual;
   private java.lang.String nemonico;

   public RubroEJB() {
   }

   public RubroEJB(com.spirit.nomina.entity.RubroIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setEmpresaId(value.getEmpresaId());
      setFrecuencia(value.getFrecuencia());
      setTipoRubro(value.getTipoRubro());
      setNombre(value.getNombre());
      setTiporolId(value.getTiporolId());
      setFecha(value.getFecha());
      setPolitica(value.getPolitica());
      setModoOperacion(value.getModoOperacion());
      setPagoIndividual(value.getPagoIndividual());
      setNemonico(value.getNemonico());
   }

   public java.lang.Long create(com.spirit.nomina.entity.RubroIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setEmpresaId(value.getEmpresaId());
      setFrecuencia(value.getFrecuencia());
      setTipoRubro(value.getTipoRubro());
      setNombre(value.getNombre());
      setTiporolId(value.getTiporolId());
      setFecha(value.getFecha());
      setPolitica(value.getPolitica());
      setModoOperacion(value.getModoOperacion());
      setPagoIndividual(value.getPagoIndividual());
      setNemonico(value.getNemonico());
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

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "FRECUENCIA")
   public java.lang.String getFrecuencia() {
      return frecuencia;
   }

   public void setFrecuencia(java.lang.String frecuencia) {
      this.frecuencia = frecuencia;
   }

   @Column(name = "TIPO_RUBRO")
   public java.lang.String getTipoRubro() {
      return tipoRubro;
   }

   public void setTipoRubro(java.lang.String tipoRubro) {
      this.tipoRubro = tipoRubro;
   }

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "TIPOROL_ID")
   public java.lang.Long getTiporolId() {
      return tiporolId;
   }

   public void setTiporolId(java.lang.Long tiporolId) {
      this.tiporolId = tiporolId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "POLITICA")
   public java.lang.String getPolitica() {
      return politica;
   }

   public void setPolitica(java.lang.String politica) {
      this.politica = politica;
   }

   @Column(name = "MODO_OPERACION")
   public java.lang.String getModoOperacion() {
      return modoOperacion;
   }

   public void setModoOperacion(java.lang.String modoOperacion) {
      this.modoOperacion = modoOperacion;
   }

   @Column(name = "PAGO_INDIVIDUAL")
   public java.lang.String getPagoIndividual() {
      return pagoIndividual;
   }

   public void setPagoIndividual(java.lang.String pagoIndividual) {
      this.pagoIndividual = pagoIndividual;
   }

   @Column(name = "NEMONICO")
   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }
	public String toString() {
		return ToStringer.toString((RubroIf)this);
	}
}
