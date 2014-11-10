package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PrecioHistoricoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PrecioHistoricoIf addPrecioHistorico(com.spirit.facturacion.entity.PrecioHistoricoIf model) throws GenericBusinessException;

   void savePrecioHistorico(com.spirit.facturacion.entity.PrecioHistoricoIf model) throws GenericBusinessException;

   void deletePrecioHistorico(java.lang.Long id) throws GenericBusinessException;

   Collection findPrecioHistoricoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PrecioHistoricoIf getPrecioHistorico(java.lang.Long id) throws GenericBusinessException;

   Collection getPrecioHistoricoList() throws GenericBusinessException;

   Collection getPrecioHistoricoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPrecioHistoricoListSize() throws GenericBusinessException;
    java.util.Collection findPrecioHistoricoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPrecioHistoricoByPrecioId(java.lang.Long precioId) throws GenericBusinessException;
    java.util.Collection findPrecioHistoricoByFechaini(java.sql.Date fechaini) throws GenericBusinessException;
    java.util.Collection findPrecioHistoricoByFechafin(java.sql.Date fechafin) throws GenericBusinessException;
    java.util.Collection findPrecioHistoricoByPrecioHis(java.math.BigDecimal precioHis) throws GenericBusinessException;
    java.util.Collection findPrecioHistoricoByPrecio(java.math.BigDecimal precio) throws GenericBusinessException;

}
