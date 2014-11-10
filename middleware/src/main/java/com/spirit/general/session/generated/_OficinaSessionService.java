package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OficinaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.OficinaIf addOficina(com.spirit.general.entity.OficinaIf model) throws GenericBusinessException;

   void saveOficina(com.spirit.general.entity.OficinaIf model) throws GenericBusinessException;

   void deleteOficina(java.lang.Long id) throws GenericBusinessException;

   Collection findOficinaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.OficinaIf getOficina(java.lang.Long id) throws GenericBusinessException;

   Collection getOficinaList() throws GenericBusinessException;

   Collection getOficinaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOficinaListSize() throws GenericBusinessException;
    java.util.Collection findOficinaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOficinaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findOficinaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findOficinaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findOficinaByCiudadId(java.lang.Long ciudadId) throws GenericBusinessException;
    java.util.Collection findOficinaByAdministradorId(java.lang.Long administradorId) throws GenericBusinessException;
    java.util.Collection findOficinaByDireccion(java.lang.String direccion) throws GenericBusinessException;
    java.util.Collection findOficinaByTelefono(java.lang.String telefono) throws GenericBusinessException;
    java.util.Collection findOficinaByFax(java.lang.String fax) throws GenericBusinessException;
    java.util.Collection findOficinaByPreimpresoSerie(java.lang.String preimpresoSerie) throws GenericBusinessException;
    java.util.Collection findOficinaByServidorId(java.lang.Long servidorId) throws GenericBusinessException;

}
