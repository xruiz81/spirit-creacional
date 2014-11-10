package com.spirit.nomina.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RolPagoDocumentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.RolPagoDocumentoIf addRolPagoDocumento(com.spirit.nomina.entity.RolPagoDocumentoIf model) throws GenericBusinessException;

   void saveRolPagoDocumento(com.spirit.nomina.entity.RolPagoDocumentoIf model) throws GenericBusinessException;

   void deleteRolPagoDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection findRolPagoDocumentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.RolPagoDocumentoIf getRolPagoDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection getRolPagoDocumentoList() throws GenericBusinessException;

   Collection getRolPagoDocumentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRolPagoDocumentoListSize() throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByTipoRolId(java.lang.Long tipoRolId) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByTipoContratoId(java.lang.Long tipoContratoId) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByCreacionUsuarioId(java.lang.Long creacionUsuarioId) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByFechaActualizacion(java.sql.Date fechaActualizacion) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByActualizacionUsuarioId(java.lang.Long actualizacionUsuarioId) throws GenericBusinessException;
    java.util.Collection findRolPagoDocumentoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
