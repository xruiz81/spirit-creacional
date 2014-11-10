package com.spirit.inventario.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.LineaIf;
import com.spirit.inventario.entity.ProductoEJB;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.session.generated._ProductoSessionService;

/**
 * The <code>ProductoSessionService</code> bean exposes the business methods
 * in the interface.
 * 
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface ProductoSessionService extends _ProductoSessionService{

	java.util.Collection findProductoByQuery(int startIndex, int endIndex,
			Map aMap, Long idEmpresa, String servicioConsumo, String mmpg)
			throws com.spirit.exception.GenericBusinessException;

	int getProductoByQueryListSize(Map aMap, Long idEmpresa,
			String servicioConsumo, String mmpg)
			throws com.spirit.exception.GenericBusinessException;

	Collection findProductoByNombreGenerico(int startIndex, int endIndex,
			String nombreGenerico, String servicioConsumo, Long proveedorId,
			String mmpg) throws com.spirit.exception.GenericBusinessException;

	int getProductoByNombreGenericoListSize(String nombreGenerico,
			String servicioConsumo, Long proveedorId, String mmpg)
			throws com.spirit.exception.GenericBusinessException;

	Collection getProductoList(int startIndex, int endIndex,
			String tipoReferencia, Map aMap, Long idReferencia,
			String tipoProducto, String servicioConsumo, String mmpg)
			throws com.spirit.exception.GenericBusinessException;

	int getProductoListSize(String tipoReferencia, Map aMap, Long idReferencia,
			String tipoProducto, String servicioConsumo, String mmpg)
			throws com.spirit.exception.GenericBusinessException;

	java.util.Collection findProductoByQuery(int startIndex, int endIndex,
			Map aMap, String mmpg)
			throws com.spirit.exception.GenericBusinessException;

	int getProductoByQueryListSize(Map aMap, String mmpg)
			throws com.spirit.exception.GenericBusinessException;

	public ProductoEJB registrarProducto(ProductoIf modelProducto)
			throws com.spirit.exception.GenericBusinessException;

	public String generarCodigoBarras(ProductoIf productoIf,
			TipoProductoIf tipoProductoIf,LineaIf lineaIf) throws GenericBusinessException;
	
	public String generarDigitoVerificadorCodigoBarras(String codigoBarras) throws GenericBusinessException;
	
	public String getNombreProductoByProductoId(Long productoId) throws GenericBusinessException;
	
	public String findDescripcionUnoProductoByCodigoBarra(String codigoBarra,int tipoDescripcion) throws GenericBusinessException;
	public java.util.Collection findProductoByEmpresaId(Long idEmpresa) throws GenericBusinessException;
	public java.util.Collection findProductoByEmpresaIdAndByQuery(Long idEmpresa, Map aMap) throws GenericBusinessException;
	public java.util.Collection findProductoByCriterioMap(Map<String, Object> aMap)throws GenericBusinessException;
	public java.util.Collection findProductoByCriterioMap(int startIndex, int endIndex, Map<String,Object> aMap)throws GenericBusinessException;
	public int findProductoByCriterioMapSize(Map<String,Object> aMap)throws GenericBusinessException;
}
