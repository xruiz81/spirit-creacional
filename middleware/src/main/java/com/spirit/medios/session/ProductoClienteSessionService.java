package com.spirit.medios.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._ProductoClienteSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ProductoClienteSessionService extends _ProductoClienteSessionService{

	public Collection findProductoClienteByQueryAndByClienteId(Map aMap, Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public int findProductoClienteByQueryAndByClienteIdSize(Map aMap, Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public int findProductoClienteByQueryAndByClienteIdAndMarcaProductoIdSize(Map aMap, Long idClienteOficina,Long idMarcaProducto) throws com.spirit.exception.GenericBusinessException;
	public Collection findProductoClienteByQueryAndByClienteId(int startIndex,int endIndex,Map aMap, Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public Collection findProductoClienteByQueryAndByClienteIdAndMarcaProductoId(int startIndex,int endIndex,Map aMap, Long idClienteOficina,Long idMarcaProducto) throws com.spirit.exception.GenericBusinessException;
	
	public Collection getProductoClienteList(int startIndex, int endIndex, Long idClienteOficina)	throws com.spirit.exception.GenericBusinessException;
	public Collection getProductoClienteList(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	public Collection getProductoClienteList(int startIndex, int endIndex, Map aMap, Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public int getProductoClienteListSize(Long idClienteOficina)throws com.spirit.exception.GenericBusinessException;
	public int getProductoClienteListSize(Map aMap)throws com.spirit.exception.GenericBusinessException;
	public int getProductoClienteListSize(Map aMap , Long idClienetOficina)throws com.spirit.exception.GenericBusinessException;
	public Collection findProductoClienteByQuery(int startIndex,int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	public void procesarProductoClienteColeccion(MarcaProductoIf marcaProducto, List<ProductoClienteIf> productoClienteColeccion, List<ProductoClienteIf> productoClienteEliminadoColeccion) throws com.spirit.exception.GenericBusinessException;
	public String getMaximoCodigoProductoCliente(Long clienteId) throws com.spirit.exception.GenericBusinessException;
	public String getMaximoCodigoMarcaProducto(Long clienteId) throws com.spirit.exception.GenericBusinessException;
	public void eliminarMarcaProducto(MarcaProductoIf marcaProducto) throws com.spirit.exception.GenericBusinessException;
	public Collection findProductoClienteByCampanaId(Long idCampana) throws com.spirit.exception.GenericBusinessException;
	public Collection findProductoClienteByOrdenTrabajoId(Long idOrdenTrabajo) throws com.spirit.exception.GenericBusinessException;
	
	public Collection findProductoClienteByEmpresaId(Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
}
