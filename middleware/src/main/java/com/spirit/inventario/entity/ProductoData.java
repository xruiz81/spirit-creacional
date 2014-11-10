package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ProductoData implements ProductoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long genericoId;

   public java.lang.Long getGenericoId() {
      return genericoId;
   }

   public void setGenericoId(java.lang.Long genericoId) {
      this.genericoId = genericoId;
   }

   private java.lang.Long presentacionId;

   public java.lang.Long getPresentacionId() {
      return presentacionId;
   }

   public void setPresentacionId(java.lang.Long presentacionId) {
      this.presentacionId = presentacionId;
   }

   private java.lang.Long proveedorId;

   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   private java.lang.String origenProducto;

   public java.lang.String getOrigenProducto() {
      return origenProducto;
   }

   public void setOrigenProducto(java.lang.String origenProducto) {
      this.origenProducto = origenProducto;
   }

   private java.lang.String codigoBarras;

   public java.lang.String getCodigoBarras() {
      return codigoBarras;
   }

   public void setCodigoBarras(java.lang.String codigoBarras) {
      this.codigoBarras = codigoBarras;
   }

   private java.math.BigDecimal costo;

   public java.math.BigDecimal getCosto() {
      return costo;
   }

   public void setCosto(java.math.BigDecimal costo) {
      this.costo = costo;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.math.BigDecimal rebate;

   public java.math.BigDecimal getRebate() {
      return rebate;
   }

   public void setRebate(java.math.BigDecimal rebate) {
      this.rebate = rebate;
   }

   private java.lang.String aceptapromocion;

   public java.lang.String getAceptapromocion() {
      return aceptapromocion;
   }

   public void setAceptapromocion(java.lang.String aceptapromocion) {
      this.aceptapromocion = aceptapromocion;
   }

   private java.lang.String permiteventa;

   public java.lang.String getPermiteventa() {
      return permiteventa;
   }

   public void setPermiteventa(java.lang.String permiteventa) {
      this.permiteventa = permiteventa;
   }

   private java.lang.String aceptadevolucion;

   public java.lang.String getAceptadevolucion() {
      return aceptadevolucion;
   }

   public void setAceptadevolucion(java.lang.String aceptadevolucion) {
      this.aceptadevolucion = aceptadevolucion;
   }

   private java.lang.String cambioprecio;

   public java.lang.String getCambioprecio() {
      return cambioprecio;
   }

   public void setCambioprecio(java.lang.String cambioprecio) {
      this.cambioprecio = cambioprecio;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.math.BigDecimal margenminimo;

   public java.math.BigDecimal getMargenminimo() {
      return margenminimo;
   }

   public void setMargenminimo(java.math.BigDecimal margenminimo) {
      this.margenminimo = margenminimo;
   }

   private java.lang.String subproveedor;

   public java.lang.String getSubproveedor() {
      return subproveedor;
   }

   public void setSubproveedor(java.lang.String subproveedor) {
      this.subproveedor = subproveedor;
   }

   private java.math.BigDecimal costoLifo;

   public java.math.BigDecimal getCostoLifo() {
      return costoLifo;
   }

   public void setCostoLifo(java.math.BigDecimal costoLifo) {
      this.costoLifo = costoLifo;
   }

   private java.math.BigDecimal costoFifo;

   public java.math.BigDecimal getCostoFifo() {
      return costoFifo;
   }

   public void setCostoFifo(java.math.BigDecimal costoFifo) {
      this.costoFifo = costoFifo;
   }

   private java.math.BigDecimal pesoBruto;

   public java.math.BigDecimal getPesoBruto() {
      return pesoBruto;
   }

   public void setPesoBruto(java.math.BigDecimal pesoBruto) {
      this.pesoBruto = pesoBruto;
   }

   private java.math.BigDecimal pesoNeto;

   public java.math.BigDecimal getPesoNeto() {
      return pesoNeto;
   }

   public void setPesoNeto(java.math.BigDecimal pesoNeto) {
      this.pesoNeto = pesoNeto;
   }

   private java.lang.Long colorId;

   public java.lang.Long getColorId() {
      return colorId;
   }

   public void setColorId(java.lang.Long colorId) {
      this.colorId = colorId;
   }

   private java.lang.Long marcaId;

   public java.lang.Long getMarcaId() {
      return marcaId;
   }

   public void setMarcaId(java.lang.Long marcaId) {
      this.marcaId = marcaId;
   }

   private java.lang.Long modeloId;

   public java.lang.Long getModeloId() {
      return modeloId;
   }

   public void setModeloId(java.lang.Long modeloId) {
      this.modeloId = modeloId;
   }

   private java.lang.String generarCodigoBarras;

   public java.lang.String getGenerarCodigoBarras() {
      return generarCodigoBarras;
   }

   public void setGenerarCodigoBarras(java.lang.String generarCodigoBarras) {
      this.generarCodigoBarras = generarCodigoBarras;
   }
   public ProductoData() {
   }

   public ProductoData(com.spirit.inventario.entity.ProductoIf value) {
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
		return ToStringer.toString((ProductoIf)this);
	}
}
