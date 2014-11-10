package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "FORMA_PAGO")
@Entity
public class FormaPagoEJB implements Serializable, FormaPagoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.Integer diasInicio;
   private java.lang.Integer diasFin;
   private java.math.BigDecimal descuentoVenta;
   private java.math.BigDecimal descuentoCartera;
   private java.math.BigDecimal interes;

   public FormaPagoEJB() {
   }

   public FormaPagoEJB(com.spirit.cartera.entity.FormaPagoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setDiasInicio(value.getDiasInicio());
      setDiasFin(value.getDiasFin());
      setDescuentoVenta(value.getDescuentoVenta());
      setDescuentoCartera(value.getDescuentoCartera());
      setInteres(value.getInteres());
   }

   public java.lang.Long create(com.spirit.cartera.entity.FormaPagoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setDiasInicio(value.getDiasInicio());
      setDiasFin(value.getDiasFin());
      setDescuentoVenta(value.getDescuentoVenta());
      setDescuentoCartera(value.getDescuentoCartera());
      setInteres(value.getInteres());
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

   @Column(name = "DIAS_INICIO")
   public java.lang.Integer getDiasInicio() {
      return diasInicio;
   }

   public void setDiasInicio(java.lang.Integer diasInicio) {
      this.diasInicio = diasInicio;
   }

   @Column(name = "DIAS_FIN")
   public java.lang.Integer getDiasFin() {
      return diasFin;
   }

   public void setDiasFin(java.lang.Integer diasFin) {
      this.diasFin = diasFin;
   }

   @Column(name = "DESCUENTO_VENTA")
   public java.math.BigDecimal getDescuentoVenta() {
      return descuentoVenta;
   }

   public void setDescuentoVenta(java.math.BigDecimal descuentoVenta) {
      this.descuentoVenta = descuentoVenta;
   }

   @Column(name = "DESCUENTO_CARTERA")
   public java.math.BigDecimal getDescuentoCartera() {
      return descuentoCartera;
   }

   public void setDescuentoCartera(java.math.BigDecimal descuentoCartera) {
      this.descuentoCartera = descuentoCartera;
   }

   @Column(name = "INTERES")
   public java.math.BigDecimal getInteres() {
      return interes;
   }

   public void setInteres(java.math.BigDecimal interes) {
      this.interes = interes;
   }
	public String toString() {
		return ToStringer.toString((FormaPagoIf)this);
	}
}
