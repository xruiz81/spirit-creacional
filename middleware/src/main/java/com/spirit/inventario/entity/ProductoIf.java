package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ProductoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getGenericoId();

   void setGenericoId(java.lang.Long genericoId);

   java.lang.Long getPresentacionId();

   void setPresentacionId(java.lang.Long presentacionId);

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);

   java.lang.String getOrigenProducto();

   void setOrigenProducto(java.lang.String origenProducto);

   java.lang.String getCodigoBarras();

   void setCodigoBarras(java.lang.String codigoBarras);

   java.math.BigDecimal getCosto();

   void setCosto(java.math.BigDecimal costo);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.math.BigDecimal getRebate();

   void setRebate(java.math.BigDecimal rebate);

   java.lang.String getAceptapromocion();

   void setAceptapromocion(java.lang.String aceptapromocion);

   java.lang.String getPermiteventa();

   void setPermiteventa(java.lang.String permiteventa);

   java.lang.String getAceptadevolucion();

   void setAceptadevolucion(java.lang.String aceptadevolucion);

   java.lang.String getCambioprecio();

   void setCambioprecio(java.lang.String cambioprecio);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.math.BigDecimal getMargenminimo();

   void setMargenminimo(java.math.BigDecimal margenminimo);

   java.lang.String getSubproveedor();

   void setSubproveedor(java.lang.String subproveedor);

   java.math.BigDecimal getCostoLifo();

   void setCostoLifo(java.math.BigDecimal costoLifo);

   java.math.BigDecimal getCostoFifo();

   void setCostoFifo(java.math.BigDecimal costoFifo);

   java.math.BigDecimal getPesoBruto();

   void setPesoBruto(java.math.BigDecimal pesoBruto);

   java.math.BigDecimal getPesoNeto();

   void setPesoNeto(java.math.BigDecimal pesoNeto);

   java.lang.Long getColorId();

   void setColorId(java.lang.Long colorId);

   java.lang.Long getMarcaId();

   void setMarcaId(java.lang.Long marcaId);

   java.lang.Long getModeloId();

   void setModeloId(java.lang.Long modeloId);

   java.lang.String getGenerarCodigoBarras();

   void setGenerarCodigoBarras(java.lang.String generarCodigoBarras);


}
