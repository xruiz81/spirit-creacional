package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _UsuarioDocumentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.UsuarioDocumentoIf addUsuarioDocumento(com.spirit.general.entity.UsuarioDocumentoIf model) throws GenericBusinessException;

   void saveUsuarioDocumento(com.spirit.general.entity.UsuarioDocumentoIf model) throws GenericBusinessException;

   void deleteUsuarioDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection findUsuarioDocumentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.UsuarioDocumentoIf getUsuarioDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection getUsuarioDocumentoList() throws GenericBusinessException;

   Collection getUsuarioDocumentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getUsuarioDocumentoListSize() throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoByPermisoImpresion(java.lang.String permisoImpresion) throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoByPermisoRegistro(java.lang.String permisoRegistro) throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoByPermisoBorrado(java.lang.String permisoBorrado) throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoByPermisoAutorizar(java.lang.String permisoAutorizar) throws GenericBusinessException;
    java.util.Collection findUsuarioDocumentoByEstado(java.lang.String estado) throws GenericBusinessException;

}
