package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "FACTURASTIPOPRODUCTODETALLE")
@Entity
public class FacturastipoproductodetalleEJB implements Serializable, FacturastipoproductodetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String modelo;
   private java.lang.String color;
   private java.lang.String talla;
   private java.lang.String tipo;
   private java.lang.Long producto;
   private java.sql.Timestamp fecha;
   private java.math.BigDecimal cant;
   private java.math.BigDecimal dev;
   private java.math.BigDecimal cantfinal;
   private java.math.BigDecimal preciouni;
   private java.math.BigDecimal valorsinivaventas;
   private java.math.BigDecimal descuentoventas;
   private java.math.BigDecimal ivaventas;
   private java.math.BigDecimal totalventas;
   private java.math.BigDecimal valorsinivadev;
   private java.math.BigDecimal ivadev;
   private java.math.BigDecimal totaldev;
   private java.lang.Long modeloId;
   private java.lang.Long colorId;
   private java.lang.Long tipoproducto;
   private java.lang.Long tallaId;

   public FacturastipoproductodetalleEJB() {
   }

   public FacturastipoproductodetalleEJB(com.spirit.pos.entity.FacturastipoproductodetalleIf value) {
      setId(value.getId());
      setModelo(value.getModelo());
      setColor(value.getColor());
      setTalla(value.getTalla());
      setTipo(value.getTipo());
      setProducto(value.getProducto());
      setFecha(value.getFecha());
      setCant(value.getCant());
      setDev(value.getDev());
      setCantfinal(value.getCantfinal());
      setPreciouni(value.getPreciouni());
      setValorsinivaventas(value.getValorsinivaventas());
      setDescuentoventas(value.getDescuentoventas());
      setIvaventas(value.getIvaventas());
      setTotalventas(value.getTotalventas());
      setValorsinivadev(value.getValorsinivadev());
      setIvadev(value.getIvadev());
      setTotaldev(value.getTotaldev());
      setModeloId(value.getModeloId());
      setColorId(value.getColorId());
      setTipoproducto(value.getTipoproducto());
      setTallaId(value.getTallaId());
   }

   public java.lang.Long create(com.spirit.pos.entity.FacturastipoproductodetalleIf value) {
      setId(value.getId());
      setModelo(value.getModelo());
      setColor(value.getColor());
      setTalla(value.getTalla());
      setTipo(value.getTipo());
      setProducto(value.getProducto());
      setFecha(value.getFecha());
      setCant(value.getCant());
      setDev(value.getDev());
      setCantfinal(value.getCantfinal());
      setPreciouni(value.getPreciouni());
      setValorsinivaventas(value.getValorsinivaventas());
      setDescuentoventas(value.getDescuentoventas());
      setIvaventas(value.getIvaventas());
      setTotalventas(value.getTotalventas());
      setValorsinivadev(value.getValorsinivadev());
      setIvadev(value.getIvadev());
      setTotaldev(value.getTotaldev());
      setModeloId(value.getModeloId());
      setColorId(value.getColorId());
      setTipoproducto(value.getTipoproducto());
      setTallaId(value.getTallaId());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "id")
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

   @Column(name = "MODELO")
   public java.lang.String getModelo() {
      return modelo;
   }

   public void setModelo(java.lang.String modelo) {
      this.modelo = modelo;
   }

   @Column(name = "COLOR")
   public java.lang.String getColor() {
      return color;
   }

   public void setColor(java.lang.String color) {
      this.color = color;
   }

   @Column(name = "TALLA")
   public java.lang.String getTalla() {
      return talla;
   }

   public void setTalla(java.lang.String talla) {
      this.talla = talla;
   }

   @Column(name = "tipo")
   public java.lang.String getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.String tipo) {
      this.tipo = tipo;
   }

   @Column(name = "producto")
   public java.lang.Long getProducto() {
      return producto;
   }

   public void setProducto(java.lang.Long producto) {
      this.producto = producto;
   }

   @Column(name = "fecha")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "cant")
   public java.math.BigDecimal getCant() {
      return cant;
   }

   public void setCant(java.math.BigDecimal cant) {
      this.cant = cant;
   }

   @Column(name = "dev")
   public java.math.BigDecimal getDev() {
      return dev;
   }

   public void setDev(java.math.BigDecimal dev) {
      this.dev = dev;
   }

   @Column(name = "cantFinal")
   public java.math.BigDecimal getCantfinal() {
      return cantfinal;
   }

   public void setCantfinal(java.math.BigDecimal cantfinal) {
      this.cantfinal = cantfinal;
   }

   @Column(name = "preciouni")
   public java.math.BigDecimal getPreciouni() {
      return preciouni;
   }

   public void setPreciouni(java.math.BigDecimal preciouni) {
      this.preciouni = preciouni;
   }

   @Column(name = "valorsinivaVentas")
   public java.math.BigDecimal getValorsinivaventas() {
      return valorsinivaventas;
   }

   public void setValorsinivaventas(java.math.BigDecimal valorsinivaventas) {
      this.valorsinivaventas = valorsinivaventas;
   }

   @Column(name = "descuentoVentas")
   public java.math.BigDecimal getDescuentoventas() {
      return descuentoventas;
   }

   public void setDescuentoventas(java.math.BigDecimal descuentoventas) {
      this.descuentoventas = descuentoventas;
   }

   @Column(name = "ivaVentas")
   public java.math.BigDecimal getIvaventas() {
      return ivaventas;
   }

   public void setIvaventas(java.math.BigDecimal ivaventas) {
      this.ivaventas = ivaventas;
   }

   @Column(name = "totalVentas")
   public java.math.BigDecimal getTotalventas() {
      return totalventas;
   }

   public void setTotalventas(java.math.BigDecimal totalventas) {
      this.totalventas = totalventas;
   }

   @Column(name = "valorsinivaDev")
   public java.math.BigDecimal getValorsinivadev() {
      return valorsinivadev;
   }

   public void setValorsinivadev(java.math.BigDecimal valorsinivadev) {
      this.valorsinivadev = valorsinivadev;
   }

   @Column(name = "ivaDev")
   public java.math.BigDecimal getIvadev() {
      return ivadev;
   }

   public void setIvadev(java.math.BigDecimal ivadev) {
      this.ivadev = ivadev;
   }

   @Column(name = "totalDev")
   public java.math.BigDecimal getTotaldev() {
      return totaldev;
   }

   public void setTotaldev(java.math.BigDecimal totaldev) {
      this.totaldev = totaldev;
   }

   @Column(name = "modelo_id")
   public java.lang.Long getModeloId() {
      return modeloId;
   }

   public void setModeloId(java.lang.Long modeloId) {
      this.modeloId = modeloId;
   }

   @Column(name = "color_id")
   public java.lang.Long getColorId() {
      return colorId;
   }

   public void setColorId(java.lang.Long colorId) {
      this.colorId = colorId;
   }

   @Column(name = "tipoProducto")
   public java.lang.Long getTipoproducto() {
      return tipoproducto;
   }

   public void setTipoproducto(java.lang.Long tipoproducto) {
      this.tipoproducto = tipoproducto;
   }

   @Column(name = "talla_id")
   public java.lang.Long getTallaId() {
      return tallaId;
   }

   public void setTallaId(java.lang.Long tallaId) {
      this.tallaId = tallaId;
   }
	public String toString() {
		return ToStringer.toString((FacturastipoproductodetalleIf)this);
	}
}
