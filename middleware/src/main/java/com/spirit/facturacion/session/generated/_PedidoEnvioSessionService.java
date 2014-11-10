package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PedidoEnvioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PedidoEnvioIf addPedidoEnvio(com.spirit.facturacion.entity.PedidoEnvioIf model) throws GenericBusinessException;

   void savePedidoEnvio(com.spirit.facturacion.entity.PedidoEnvioIf model) throws GenericBusinessException;

   void deletePedidoEnvio(java.lang.Long id) throws GenericBusinessException;

   Collection findPedidoEnvioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PedidoEnvioIf getPedidoEnvio(java.lang.Long id) throws GenericBusinessException;

   Collection getPedidoEnvioList() throws GenericBusinessException;

   Collection getPedidoEnvioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPedidoEnvioListSize() throws GenericBusinessException;
    java.util.Collection findPedidoEnvioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByPedidoId(java.lang.Long pedidoId) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByMetodoEnvio(java.lang.String metodoEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByValorEnvio(java.math.BigDecimal valorEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByNombresFacturacion(java.lang.String nombresFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByApellidosFacturacion(java.lang.String apellidosFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByDireccionFacturacion(java.lang.String direccionFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByCiudadFacturacion(java.lang.String ciudadFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByRegionFacturacion(java.lang.String regionFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByZipFacturacion(java.lang.String zipFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByCodigoPaisFacturacion(java.lang.String codigoPaisFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByTelefonoFacturacion(java.lang.String telefonoFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByCelularFacturacion(java.lang.String celularFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByNombresEnvio(java.lang.String nombresEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByApellidosEnvio(java.lang.String apellidosEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByDireccionEnvio(java.lang.String direccionEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByCiudadEnvio(java.lang.String ciudadEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByRegionEnvio(java.lang.String regionEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByZipEnvio(java.lang.String zipEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByCodigoPaisEnvio(java.lang.String codigoPaisEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByTelefonoEnvio(java.lang.String telefonoEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByCelularEnvio(java.lang.String celularEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByCorreoCliente(java.lang.String correoCliente) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByNombresCliente(java.lang.String nombresCliente) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByApellidosCliente(java.lang.String apellidosCliente) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByNombreEmpresaFacturacion(java.lang.String nombreEmpresaFacturacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByNombreEmpresaEnvio(java.lang.String nombreEmpresaEnvio) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByOrdenExternaId(java.lang.String ordenExternaId) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByFechaActualizacion(java.lang.String fechaActualizacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByIdentificacionFundacion(java.lang.String identificacionFundacion) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByValorDescuento(java.math.BigDecimal valorDescuento) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByValorSubtotal(java.math.BigDecimal valorSubtotal) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByValorTotal(java.math.BigDecimal valorTotal) throws GenericBusinessException;
    java.util.Collection findPedidoEnvioByValorImpuesto(java.math.BigDecimal valorImpuesto) throws GenericBusinessException;

}
