package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoRolSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.TipoRolIf addTipoRol(com.spirit.nomina.entity.TipoRolIf model) throws GenericBusinessException;

   void saveTipoRol(com.spirit.nomina.entity.TipoRolIf model) throws GenericBusinessException;

   void deleteTipoRol(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoRolByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.TipoRolIf getTipoRol(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoRolList() throws GenericBusinessException;

   Collection getTipoRolList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoRolListSize() throws GenericBusinessException;
    java.util.Collection findTipoRolById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoRolByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTipoRolByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoRolByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoRolByNemonico(java.lang.String nemonico) throws GenericBusinessException;
    java.util.Collection findTipoRolByRubroEventual(java.lang.String rubroEventual) throws GenericBusinessException;
    java.util.Collection findTipoRolByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findTipoRolByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findTipoRolByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;
    java.util.Collection findTipoRolByRubroProvisionado(java.lang.String rubroProvisionado) throws GenericBusinessException;
    java.util.Collection findTipoRolByFormaPago(java.lang.String formaPago) throws GenericBusinessException;

}
