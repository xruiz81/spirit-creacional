package com.spirit.inventario.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PRODUCTO")
@Entity
public class ProductoEJB implements Serializable, ProductoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long genericoId;
   private java.lang.Long presentacionId;
   private java.lang.Long proveedorId;
   private java.lang.String origenProducto;
   private java.lang.String codigoBarras;
   private java.math.BigDecimal costo;
   private java.sql.Timestamp fechaCreacion;
   private java.math.BigDecimal rebate;
   private java.lang.String aceptapromocion;
   private java.lang.String permiteventa;
   private java.lang.String aceptadevolucion;
   private java.lang.String cambioprecio;
   private java.lang.String estado;
   private java.lang.String codigo;
   private java.math.BigDecimal margenminimo;
   private java.lang.String subproveedor;
   private java.math.BigDecimal costoLifo;
   private java.math.BigDecimal costoFifo;
   private java.math.BigDecimal pesoBruto;
   private java.math.BigDecimal pesoNeto;
   private java.lang.Long colorId;
   private java.lang.Long marcaId;
   private java.lang.Long modeloId;
   private java.lang.String generarCodigoBarras;

   public ProductoEJB() {
   }

   public ProductoEJB(com.spirit.inventario.entity.ProductoIf value) {
      setId(value.getId());
      setGenericoId(value.getGenericoId());
      setPresentacionId(value.getPresentacionId());
      setProveedorId(value.getProveedorId());
      setOrigenProducto(value.getOrigenProducto());
      setCodigoBarras(value.getCodigoBarras());
      setCosto(value.getCosto());
      setFechaCreacion(value.getFechaCreacion());
      setRebate(value.getRebate());
      setAceptapromocion(value.getAceptapromocion());
      setPermiteventa(value.getPermiteventa());
      setAceptadevolucion(value.getAceptadevolucion());
      setCambioprecio(value.getCambioprecio());
      setEstado(value.getEstado());
      setCodigo(value.getCodigo());
      setMargenminimo(value.getMargenminimo());
      setSubproveedor(value.getSubproveedor());
      setCostoLifo(value.getCostoLifo());
      setCostoFifo(value.getCostoFifo());
      setPesoBruto(value.getPesoBruto());
      setPesoNeto(value.getPesoNeto());
      setColorId(value.getColorId());
      setMarcaId(value.getMarcaId());
      setModeloId(value.getModeloId());
      setGenerarCodigoBarras(value.getGenerarCodigoBarras());
   }

   public java.lang.Long create(com.spirit.inventario.entity.ProductoIf value) {
      setId(value.getId());
      setGenericoId(value.getGenericoId());
      setPresentacionId(value.getPresentacionId());
      setProveedorId(value.getProveedorId());
      setOrigenProducto(value.getOrigenProducto());
      setCodigoBarras(value.getCodigoBarras());
      setCosto(value.getCosto());
      setFechaCreacion(value.getFechaCreacion());
      setRebate(value.getRebate());
      setAceptapromocion(value.getAceptapromocion());
      setPermiteventa(value.getPermiteventa());
      setAceptadevolucion(value.getAceptadevolucion());
      setCambioprecio(value.getCambioprecio());
      setEstado(value.getEstado());
      setCodigo(value.getCodigo());
      setMargenminimo(value.getMargenminimo());
      setSubproveedor(value.getSubproveedor());
      setCostoLifo(value.getCostoLifo());
      setCostoFifo(value.getCostoFifo());
      setPesoBruto(value.getPesoBruto());
      setPesoNeto(value.getPesoNeto());
      setColorId(value.getColorId());
      setMarcaId(value.getMarcaId());
      setModeloId(value.getModeloId());
      setGenerarCodigoBarras(value.getGenerarCodigoBarras());
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

   @Column(name = "GENERICO_ID")
   public java.lang.Long getGenericoId() {
      return genericoId;
   }

   public void setGenericoId(java.lang.Long genericoId) {
      this.genericoId = genericoId;
   }

   @Column(name = "PRESENTACION_ID")
   public java.lang.Long getPresentacionId() {
      return presentacionId;
   }

   public void setPresentacionId(java.lang.Long presentacionId) {
      this.presentacionId = presentacionId;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   @Column(name = "ORIGEN_PRODUCTO")
   public java.lang.String getOrigenProducto() {
      return origenProducto;
   }

   public void setOrigenProducto(java.lang.String origenProducto) {
      this.origenProducto = origenProducto;
   }

   @Column(name = "CODIGO_BARRAS")
   public java.lang.String getCodigoBarras() {
      return codigoBarras;
   }

   public void setCodigoBarras(java.lang.String codigoBarras) {
      this.codigoBarras = codigoBarras;
   }

   @Column(name = "COSTO")
   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "REBATE")
   public java.math.BigDecimal getRebate() {
      return rebate;
   }

   public void setRebate(java.math.BigDecimal rebate) {
      this.rebate = rebate;
   }

   @Column(name = "ACEPTAPROMOCION")
   public java.lang.String getAceptapromocion() {
      return aceptapromocion;
   }

   public void setAceptapromocion(java.lang.String aceptapromocion) {
      this.aceptapromocion = aceptapromocion;
   }

   @Column(name = "PERMITEVENTA")
   public java.lang.String getPermiteventa() {
      return permiteventa;
   }

   public void setPermiteventa(java.lang.String permiteventa) {
      this.permiteventa = permiteventa;
   }

   @Column(name = "ACEPTADEVOLUCION")
   public java.lang.String getAceptadevolucion() {
      return aceptadevolucion;
   }

   public void setAceptadevolucion(java.lang.String aceptadevolucion) {
      this.aceptadevolucion = aceptadevolucion;
   }

   @Column(name = "CAMBIOPRECIO")
   public java.lang.String getCambioprecio() {
      return cambioprecio;
   }

   public void setCambioprecio(java.lang.String cambioprecio) {
      this.cambioprecio = cambioprecio;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "MARGENMINIMO")
   public java.math.BigDecimal getMargenminimo() {
      return margenminimo;
   }

   public void setMargenminimo(java.math.BigDecimal margenminimo) {
      this.margenminimo = margenminimo;
   }

   @Column(name = "SUBPROVEEDOR")
   public java.lang.String getSubproveedor() {
      return subproveedor;
   }

   public void setSubproveedor(java.lang.String subproveedor) {
      this.subproveedor = subproveedor;
   }

   @Column(name = "COSTO_LIFO")
   public java.math.BigDecimal getCostoLifo() {
      return costoLifo;
   }

   public void setCostoLifo(java.math.BigDecimal costoLifo) {
      this.costoLifo = costoLifo;
   }

   @Column(name = "COSTO_FIFO")
   public java.math.BigDecimal getCostoFifo() {
      return costoFifo;
   }

   public void setCostoFifo(java.math.BigDecimal costoFifo) {
      this.costoFifo = costoFifo;
   }

   @Column(name = "PESO_BRUTO")
   public java.math.BigDecimal getPesoBruto() {
      return pesoBruto;
   }

   public void setPesoBruto(java.math.BigDecimal pesoBruto) {
      this.pesoBruto = pesoBruto;
   }

   @Column(name = "PESO_NETO")
   public java.math.BigDecimal getPesoNeto() {
      return pesoNeto;
   }

   public void setPesoNeto(java.math.BigDecimal pesoNeto) {
      this.pesoNeto = pesoNeto;
   }

   @Column(name = "COLOR_ID")
   public java.lang.Long getColorId() {
      return colorId;
   }

   public void setColorId(java.lang.Long colorId) {
      this.colorId = colorId;
   }

   @Column(name = "MARCA_ID")
   public java.lang.Long getMarcaId() {
      return marcaId;
   }

   public void setMarcaId(java.lang.Long marcaId) {
      this.marcaId = marcaId;
   }

   @Column(name = "MODELO_ID")
   public java.lang.Long getModeloId() {
      return modeloId;
   }

   public void setModeloId(java.lang.Long modeloId) {
      this.modeloId = modeloId;
   }

   @Column(name = "GENERAR_CODIGO_BARRAS")
   public java.lang.String getGenerarCodigoBarras() {
      return generarCodigoBarras;
   }

   public void setGenerarCodigoBarras(java.lang.String generarCodigoBarras) {
      this.generarCodigoBarras = generarCodigoBarras;
   }
	public String toString() {
		return ToStringer.toString((ProductoIf)this);
	}
}
