package com.spirit.inventario.session.generated;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findTipoProductoDonacion()  throws GenericBusinessException;		
	public Collection findTipoProductoDonacionResto()  throws GenericBusinessException;
	public Collection findTipoProductoTarjetaAfiliacion(String tarjetatipoId)  throws GenericBusinessException;
	public Collection findTipoProductoTarjetaAfiliacionResto(String tarjetatipoId)  throws GenericBusinessException;	
	
   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.TipoProductoIf addTipoProducto(com.spirit.inventario.entity.TipoProductoIf model) throws GenericBusinessException;

   void saveTipoProducto(com.spirit.inventario.entity.TipoProductoIf model) throws GenericBusinessException;

   void deleteTipoProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.TipoProductoIf getTipoProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoProductoList() throws GenericBusinessException;

   Collection getTipoProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoProductoListSize() throws GenericBusinessException;
    java.util.Collection findTipoProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoProductoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoProductoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoProductoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTipoProductoByEstado(java.lang.String estado) throws GenericBusinessException;

}
