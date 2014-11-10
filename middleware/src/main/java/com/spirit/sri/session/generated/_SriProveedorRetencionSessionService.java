package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriProveedorRetencionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriProveedorRetencionIf addSriProveedorRetencion(com.spirit.sri.entity.SriProveedorRetencionIf model) throws GenericBusinessException;

   void saveSriProveedorRetencion(com.spirit.sri.entity.SriProveedorRetencionIf model) throws GenericBusinessException;

   void deleteSriProveedorRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findSriProveedorRetencionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriProveedorRetencionIf getSriProveedorRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findSriProveedorRetencionByQueryAndEmpresaId(Long empresaId) throws com.spirit.exception.GenericBusinessException; 
   
   Collection getSriProveedorRetencionList() throws GenericBusinessException;

   Collection getSriProveedorRetencionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriProveedorRetencionListSize() throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByTipoPersona(java.lang.String tipoPersona) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByLlevaContabilidad(java.lang.String llevaContabilidad) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByBienServicio(java.lang.String bienServicio) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByRetefuente(java.math.BigDecimal retefuente) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByReteiva(java.math.BigDecimal reteiva) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByIdCuentaRetefuente(java.lang.Long idCuentaRetefuente) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByIdCuentaReteiva(java.lang.Long idCuentaReteiva) throws GenericBusinessException;
    java.util.Collection findSriProveedorRetencionByProfesional(java.lang.String profesional) throws GenericBusinessException;

}
