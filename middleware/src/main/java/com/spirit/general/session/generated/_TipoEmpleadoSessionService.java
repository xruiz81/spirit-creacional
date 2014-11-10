package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoEmpleadoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoEmpleadoIf addTipoEmpleado(com.spirit.general.entity.TipoEmpleadoIf model) throws GenericBusinessException;

   void saveTipoEmpleado(com.spirit.general.entity.TipoEmpleadoIf model) throws GenericBusinessException;

   void deleteTipoEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoEmpleadoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoEmpleadoIf getTipoEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoEmpleadoList() throws GenericBusinessException;

   Collection getTipoEmpleadoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoEmpleadoListSize() throws GenericBusinessException;
    java.util.Collection findTipoEmpleadoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoEmpleadoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoEmpleadoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoEmpleadoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTipoEmpleadoByVendedor(java.lang.String vendedor) throws GenericBusinessException;
    java.util.Collection findTipoEmpleadoBySupervisor(java.lang.String supervisor) throws GenericBusinessException;
    java.util.Collection findTipoEmpleadoByAdministrador(java.lang.String administrador) throws GenericBusinessException;

}
