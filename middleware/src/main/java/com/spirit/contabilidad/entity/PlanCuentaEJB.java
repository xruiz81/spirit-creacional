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

@Table(name = "PLAN_CUENTA")
@Entity
public class PlanCuentaEJB implements Serializable, PlanCuentaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.sql.Date fecha;
   private java.lang.Long monedaId;
   private java.lang.String estado;
   private java.lang.String mascara;
   private java.lang.String predeterminado;

   public PlanCuentaEJB() {
   }

   public PlanCuentaEJB(com.spirit.contabilidad.entity.PlanCuentaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setFecha(value.getFecha());
      setMonedaId(value.getMonedaId());
      setEstado(value.getEstado());
      setMascara(value.getMascara());
      setPredeterminado(value.getPredeterminado());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.PlanCuentaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setFecha(value.getFecha());
      setMonedaId(value.getMonedaId());
      setEstado(value.getEstado());
      setMascara(value.getMascara());
      setPredeterminado(value.getPredeterminado());
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

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "MONEDA_ID")
   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "MASCARA")
   public java.lang.String getMascara() {
      return mascara;
   }

   public void setMascara(java.lang.String mascara) {
      this.mascara = mascara;
   }

   @Column(name = "PREDETERMINADO")
   public java.lang.String getPredeterminado() {
      return predeterminado;
   }

   public void setPredeterminado(java.lang.String predeterminado) {
      this.predeterminado = predeterminado;
   }
	public String toString() {
		return ToStringer.toString((PlanCuentaIf)this);
	}
}
