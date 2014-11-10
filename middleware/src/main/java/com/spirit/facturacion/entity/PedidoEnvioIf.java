package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PedidoEnvioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPedidoId();

   void setPedidoId(java.lang.Long pedidoId);

   java.lang.String getMetodoEnvio();

   void setMetodoEnvio(java.lang.String metodoEnvio);

   java.math.BigDecimal getValorEnvio();

   void setValorEnvio(java.math.BigDecimal valorEnvio);

   java.lang.String getNombresFacturacion();

   void setNombresFacturacion(java.lang.String nombresFacturacion);

   java.lang.String getApellidosFacturacion();

   void setApellidosFacturacion(java.lang.String apellidosFacturacion);

   java.lang.String getDireccionFacturacion();

   void setDireccionFacturacion(java.lang.String direccionFacturacion);

   java.lang.String getCiudadFacturacion();

   void setCiudadFacturacion(java.lang.String ciudadFacturacion);

   java.lang.String getRegionFacturacion();

   void setRegionFacturacion(java.lang.String regionFacturacion);

   java.lang.String getZipFacturacion();

   void setZipFacturacion(java.lang.String zipFacturacion);

   java.lang.String getCodigoPaisFacturacion();

   void setCodigoPaisFacturacion(java.lang.String codigoPaisFacturacion);

   java.lang.String getTelefonoFacturacion();

   void setTelefonoFacturacion(java.lang.String telefonoFacturacion);

   java.lang.String getCelularFacturacion();

   void setCelularFacturacion(java.lang.String celularFacturacion);

   java.lang.String getNombresEnvio();

   void setNombresEnvio(java.lang.String nombresEnvio);

   java.lang.String getApellidosEnvio();

   void setApellidosEnvio(java.lang.String apellidosEnvio);

   java.lang.String getDireccionEnvio();

   void setDireccionEnvio(java.lang.String direccionEnvio);

   java.lang.String getCiudadEnvio();

   void setCiudadEnvio(java.lang.String ciudadEnvio);

   java.lang.String getRegionEnvio();

   void setRegionEnvio(java.lang.String regionEnvio);

   java.lang.String getZipEnvio();

   void setZipEnvio(java.lang.String zipEnvio);

   java.lang.String getCodigoPaisEnvio();

   void setCodigoPaisEnvio(java.lang.String codigoPaisEnvio);

   java.lang.String getTelefonoEnvio();

   void setTelefonoEnvio(java.lang.String telefonoEnvio);

   java.lang.String getCelularEnvio();

   void setCelularEnvio(java.lang.String celularEnvio);

   java.lang.String getCorreoCliente();

   void setCorreoCliente(java.lang.String correoCliente);

   java.lang.String getNombresCliente();

   void setNombresCliente(java.lang.String nombresCliente);

   java.lang.String getApellidosCliente();

   void setApellidosCliente(java.lang.String apellidosCliente);

   java.lang.String getNombreEmpresaFacturacion();

   void setNombreEmpresaFacturacion(java.lang.String nombreEmpresaFacturacion);

   java.lang.String getNombreEmpresaEnvio();

   void setNombreEmpresaEnvio(java.lang.String nombreEmpresaEnvio);

   java.lang.String getOrdenExternaId();

   void setOrdenExternaId(java.lang.String ordenExternaId);

   java.lang.String getFechaActualizacion();

   void setFechaActualizacion(java.lang.String fechaActualizacion);

   java.lang.String getIdentificacionFundacion();

   void setIdentificacionFundacion(java.lang.String identificacionFundacion);

   java.math.BigDecimal getValorDescuento();

   void setValorDescuento(java.math.BigDecimal valorDescuento);

   java.math.BigDecimal getValorSubtotal();

   void setValorSubtotal(java.math.BigDecimal valorSubtotal);

   java.math.BigDecimal getValorTotal();

   void setValorTotal(java.math.BigDecimal valorTotal);

   java.math.BigDecimal getValorImpuesto();

   void setValorImpuesto(java.math.BigDecimal valorImpuesto);


}
