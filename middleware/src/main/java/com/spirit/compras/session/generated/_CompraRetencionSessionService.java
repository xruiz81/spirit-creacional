package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CompraRetencionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	public java.util.Collection getCompraRetencionByQueryList2(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa)  throws GenericBusinessException;
	public int getCompraRetencionListSize(Map aMap, java.lang.Long idEmpresa) throws GenericBusinessException; 
 
   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.CompraRetencionIf addCompraRetencion(com.spirit.compras.entity.CompraRetencionIf model) throws GenericBusinessException;

   void saveCompraRetencion(com.spirit.compras.entity.CompraRetencionIf model) throws GenericBusinessException;

   void deleteCompraRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findCompraRetencionByQuery(Map aMap) throws GenericBusinessException;

   com.spirit.compras.entity.CompraRetencionIf getCompraRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection getCompraRetencionList() throws GenericBusinessException;

   Collection getCompraRetencionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCompraRetencionListSize() throws GenericBusinessException;
    java.util.Collection findCompraRetencionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByEstablecimiento(java.lang.String establecimiento) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByPuntoEmision(java.lang.String puntoEmision) throws GenericBusinessException;
    java.util.Collection findCompraRetencionBySecuencial(java.lang.String secuencial) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByAutorizacion(java.lang.String autorizacion) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByFechaEmision(java.sql.Date fechaEmision) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByCompraId(java.lang.Long compraId) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByEjercicioFiscal(java.lang.String ejercicioFiscal) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByBaseImponible(java.math.BigDecimal baseImponible) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByImpuesto(java.lang.String impuesto) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByValorRetenido(java.math.BigDecimal valorRetenido) throws GenericBusinessException;
    java.util.Collection findCompraRetencionByCodigoImpuesto(java.lang.String codigoImpuesto) throws GenericBusinessException;

}
