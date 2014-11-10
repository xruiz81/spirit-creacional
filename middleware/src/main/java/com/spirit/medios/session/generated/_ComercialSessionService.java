package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ComercialSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.ComercialIf addComercial(com.spirit.medios.entity.ComercialIf model) throws GenericBusinessException;

   void saveComercial(com.spirit.medios.entity.ComercialIf model) throws GenericBusinessException;

   void deleteComercial(java.lang.Long id) throws GenericBusinessException;

   Collection findComercialByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.ComercialIf getComercial(java.lang.Long id) throws GenericBusinessException;

   Collection getComercialList() throws GenericBusinessException;

   Collection getComercialList(int startIndex, int endIndex) throws GenericBusinessException;

   int getComercialListSize() throws GenericBusinessException;
    java.util.Collection findComercialById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findComercialByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findComercialByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findComercialByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findComercialByCampanaId(java.lang.Long campanaId) throws GenericBusinessException;
    java.util.Collection findComercialByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findComercialByDerechoprogramaId(java.lang.Long derechoprogramaId) throws GenericBusinessException;
    java.util.Collection findComercialByVersion(java.lang.String version) throws GenericBusinessException;
    java.util.Collection findComercialByDuracion(java.lang.String duracion) throws GenericBusinessException;
    java.util.Collection findComercialByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findComercialByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findComercialByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) throws GenericBusinessException;

}
