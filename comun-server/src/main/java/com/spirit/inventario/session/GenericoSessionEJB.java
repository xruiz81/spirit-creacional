package com.spirit.inventario.session;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.LineaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoEJB;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.LoteEJB;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.ProductoEJB;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.session.generated._GenericoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>GenericoSession</code> session bean, which acts as a facade to
 * the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 * 
 */
@Stateless
public class GenericoSessionEJB extends _GenericoSession implements GenericoSessionRemote,
		GenericoSessionLocal {

	@PersistenceContext(unitName = "spirit") EntityManager manager;

	@EJB
	private ProductoSessionLocal productoLocal;

	@EJB
	private UtilitariosSessionLocal utilitariosLocal;

	@EJB
	private TipoProductoSessionLocal tipoProductoSessionLocal;
	
	@EJB
	private LineaSessionLocal lineaSessionLocal;
	
	@EJB
	private LoteSessionLocal loteSessionLocal;
	
	@EJB
	private EmpresaSessionLocal empresaSessionLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(GenericoSessionEJB.class);
	private DecimalFormat formatoSerial = new DecimalFormat("0000000000");

	@Resource
	private SessionContext ctx;

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getGenericoList(int startIndex, int endIndex, Map aMap)
			throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from GenericoEJB " + objectName + " where "
				+ where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getGenericoListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from GenericoEJB " + objectName
				+ " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}

	
	public void fixSecuences() throws GenericBusinessException {

		String queryString="SELECT e FROM hibernate_sequences e";
		Query query=manager.createQuery(queryString);
				
		List<Object[]> listaSecueqnce = query.getResultList();
		String maxIDquery="Select (max(ID))/20+1 from %s";
		String updateSequence="UPDATE hibernate_sequences SET SEQUENCE_NEXT_HI_VALUE=(SELECT (MAX(ID)/20)+1 FROM %s) where SEQUENCE_NAME='%s";
		String maxId="";
		String tableName="";
		for (Object[] result : listaSecueqnce) {
			tableName=(String)result[0];
			maxId=(String)manager.createQuery(String.format(maxIDquery,tableName)).getSingleResult();
			manager.createQuery(String.format(updateSequence, tableName,tableName)).executeUpdate();
		}
	}

	public void regenerarCodigoBarras() throws GenericBusinessException {

		String queryString="SELECT producto from ProductoEJB producto," +
				"GenericoEJB generico where " +
				"generico.id=producto.genericoId and " +
				"producto.estado='X'";
		
		Query query=manager.createQuery(queryString);
				
		List<ProductoIf> listaProducto = query.getResultList();
				
		TipoProductoIf tipoProductoIf=null;
		GenericoIf genericoIf=null;
		String codigoBarras="";
		for (ProductoIf productoIf : listaProducto) {
			genericoIf=getGenerico(productoIf.getGenericoId());
			tipoProductoIf=tipoProductoSessionLocal.getTipoProducto(genericoIf.getTipoproductoId());
			productoIf.setCodigoBarras(null);
			manager.merge(productoIf);
			codigoBarras=productoLocal.generarCodigoBarras(
					productoIf, 
					tipoProductoIf,
					lineaSessionLocal.getLinea(genericoIf.getLineaId()));
			productoIf.setEstado("A");
			productoIf.setCodigoBarras(codigoBarras);
			manager.merge(productoIf);
		}
	}

	public void procesarGenerico(GenericoIf model, List<ProductoIf> modelProductoList) throws GenericBusinessException {
		try {
			EmpresaIf empresa = empresaSessionLocal.getEmpresa(model.getEmpresaId());
			model.setCodigo(empresa.getCodigo() + "-");
			model.setCodigo(model.getCodigo() + formatoSerial.format(getMaximoCodigoGenerico(model)));
			model.setReferencia(model.getCodigo());
			GenericoIf generico = registrarGenerico(model);
			manager.persist(generico);
			for (ProductoIf modelProducto : modelProductoList) {
				modelProducto.setGenericoId(generico.getPrimaryKey());
				String codigo = getMaximoCodigoProducto(generico.getEmpresaId());
				Long codigoProducto = 1L;
				if (!codigo.equals("[null]")) {
					codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
					codigoProducto = Long.parseLong(codigo) + 1L;
				}
				modelProducto.setCodigo(formatoSerial.format(codigoProducto));
				ProductoIf producto = productoLocal.registrarProducto(modelProducto);
				if (generico.getLlevaInventario().equals("S") && producto.getGenerarCodigoBarras().equals("S")) {
					String codigoBarras = productoLocal.generarCodigoBarras(modelProducto, tipoProductoSessionLocal
							.getTipoProducto(generico.getTipoproductoId()),lineaSessionLocal.getLinea(generico.getLineaId()));
					producto.setCodigoBarras(codigoBarras);
				}
				if (producto.getId() == null)
					manager.persist(producto);
				else
					manager.merge(producto);
				if (generico.getUsaLote().equals("S") && !productoTieneLote(producto.getPrimaryKey())) {
					LoteIf lote = (LoteIf) new LoteEJB();
					codigo = getMaximoCodigoLote(generico.getEmpresaId());
					Long codigoLote = 1L;
					if (!codigoLote.equals("[null]")) {
						codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
						codigoLote = Long.parseLong(codigo) + 1L;
					}
					lote.setCodigo(formatoSerial.format(codigoLote));
					lote.setEstado("A");
					lote.setFechaCreacion(utilitariosLocal.fromUtilDateToTimestamp(utilitariosLocal.dateHoy()));
					lote.setFechaElaboracion(utilitariosLocal.fromUtilDateToTimestamp(utilitariosLocal.dateHoy()));
					lote.setProductoId(producto.getPrimaryKey());
					manager.persist(lote);
				}
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en GenericoSesssionEJB", e);
			throw new GenericBusinessException(
					"Se ha producido un error al guardar la información en el Generico");
		}
	}
	
	private int getMaximoCodigoGenerico(GenericoIf generico) {
		String queryString = "select max(codigo) from GenericoEJB g where g.codigo like '" + generico.getCodigo() + "%'";
		Query query = manager.createQuery(queryString);
		String maxCodigoGenerico = query.getResultList().toString();
		int codigoGenerico = 1;
		if (!maxCodigoGenerico.equals("[null]")) {
			maxCodigoGenerico = maxCodigoGenerico.substring(1, maxCodigoGenerico.length()).replaceAll("]", "");
			codigoGenerico = Integer.parseInt(maxCodigoGenerico.split(generico.getCodigo())[1]) + 1;
		}
		return codigoGenerico;
	}
	
	@SuppressWarnings("unchecked")
	private boolean productoTieneLote(Long productoId) throws GenericBusinessException {
		Iterator<LoteIf> it = loteSessionLocal.findLoteByProductoId(productoId).iterator();
		if (it.hasNext())
			return true;
		return false;
	}
	
	private String getMaximoCodigoLote(Long empresaId) {
		//select max(l.CODIGO) from LOTE l, PRODUCTO p, GENERICO g where l.PRODUCTO_ID = p.ID and p.GENERICO_ID = g.ID and g.EMPRESA_ID = 2
		String queryString = "select max(l.codigo) from LoteEJB l, ProductoEJB p, GenericoEJB g where l.productoId = p.id and p.genericoId = g.id and g.empresaId = :empresaId";
		Query query = manager.createQuery(queryString);
		query.setParameter("empresaId", empresaId);
		return query.getResultList().toString();
	}

	public void actualizarGenerico(GenericoIf model,
			List<ProductoIf> modelProductoList,
			List<ProductoIf> detalleProductoRemovidoList)
			throws GenericBusinessException {
		try {
			GenericoIf generico = registrarGenerico(model);
			manager.merge(generico);

			for (ProductoIf modelProductoRemovido : detalleProductoRemovidoList) {
				try {
					ProductoEJB data = manager.find(ProductoEJB.class,
							modelProductoRemovido.getId());
					manager.remove(data);
				} catch (Exception e) {
					ctx.setRollbackOnly();
					e.printStackTrace();
					log
							.error(
									"Error al eliminar información en Generico Producto.",
									e);
					throw new GenericBusinessException(
							"Error al eliminar en Generico el Producto: "
									+ modelProductoRemovido.getCodigo());
				}
			}
			manager.flush();
			for (ProductoIf modelProducto : modelProductoList) {
				modelProducto.setGenericoId(generico.getPrimaryKey());
				if (modelProducto.getPrimaryKey() == null) {
					String codigo = getMaximoCodigoProducto(generico
							.getEmpresaId());
					Long codigoProducto = 1L;
					if (!codigo.equals("[null]")) {
						codigo = codigo.substring(1, codigo.length())
								.replaceAll("]", "");
						codigoProducto = Long.parseLong(codigo) + 1L;
					}
					modelProducto.setCodigo(formatoSerial
							.format(codigoProducto));
				}
				ProductoIf producto = productoLocal
						.registrarProducto(modelProducto);
				if (generico.getLlevaInventario().equals("S") && producto.getGenerarCodigoBarras().equals("S") && (producto.getCodigoBarras() == null || producto.getCodigoBarras().equals(""))) {
					String codigoBarras = productoLocal.generarCodigoBarras(
							modelProducto, tipoProductoSessionLocal
							.getTipoProducto(generico.getTipoproductoId()),lineaSessionLocal.getLinea(generico.getLineaId()));
					producto.setCodigoBarras(codigoBarras);
				}
				if (producto.getId() == null)
					manager.persist(producto);
				else
					manager.merge(producto);
				if (generico.getUsaLote().equals("S") && !productoTieneLote(producto.getPrimaryKey())) {
					LoteIf lote = (LoteIf) new LoteEJB();
					String codigo = getMaximoCodigoLote(generico.getEmpresaId());
					Long codigoLote = 1L;
					if (!codigoLote.equals("[null]")) {
						codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
						codigoLote = Long.parseLong(codigo) + 1L;
					}
					lote.setCodigo(formatoSerial.format(codigoLote));
					lote.setEstado("A");
					lote.setFechaCreacion(utilitariosLocal.fromUtilDateToTimestamp(utilitariosLocal.dateHoy()));
					lote.setFechaElaboracion(utilitariosLocal.fromUtilDateToTimestamp(utilitariosLocal.dateHoy()));
					lote.setProductoId(producto.getPrimaryKey());
					manager.persist(lote);
				}
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al actualizar información en GenericoSesssionEJB",
					e);
			throw new GenericBusinessException(
					"Se ha producido un error al actualizar la información en el Generico");
		}
	}

	public void eliminarGenerico(Long genericoId)
			throws GenericBusinessException {
		try {
			GenericoEJB data = manager.find(GenericoEJB.class, genericoId);
			Collection<ProductoIf> modelProductoList = productoLocal
					.findProductoByGenericoId(genericoId);

			for (ProductoIf modelProducto : modelProductoList) {
				manager.remove(modelProducto);
			}
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en GenericoSesssionEJB.",
					e);
			e.printStackTrace();
			throw new GenericBusinessException(
					"Se ha producido un error al eliminar el Generico");
		}
	}

	private String getMaximoCodigoProducto(Long idEmpresa) {
		String queryString = "select max(p.codigo) from GenericoEJB g, ProductoEJB p where g.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList().toString();
	}

	private GenericoEJB registrarGenerico(GenericoIf model) {
		GenericoEJB generico = new GenericoEJB();
		generico.setAbreviado(model.getAbreviado());
		generico.setAfectastock(model.getAfectastock());
		generico.setCambioDescripcion(model.getCambioDescripcion());
		generico.setCobraIce(model.getCobraIce());
		generico.setCobraIva(model.getCobraIva());
		generico.setCobraIvaCliente(model.getCobraIvaCliente());
		generico.setCodigo(model.getCodigo());
		generico.setEmpresaId(model.getEmpresaId());
		generico.setEstado(model.getEstado());
		generico.setMediosProduccion(model.getMediosProduccion());
		generico.setFamiliaGenericoId(model.getFamiliaGenericoId());
		generico.setFechaCreacion(model.getFechaCreacion());
		generico.setIce(utilitariosLocal.redondeoValor(model.getIce()));
		generico.setId(model.getId());
		generico.setIva(utilitariosLocal.redondeoValor(model.getIva()));
		generico.setLineaId(model.getLineaId());
		generico.setMedidaId(model.getMedidaId());
		generico.setNombre(model.getNombre());
		generico.setNombreGenerico(model.getNombreGenerico());
		generico.setOtroImpuesto(utilitariosLocal.redondeoValor(model.getOtroImpuesto()));
		generico.setPartidaArancelaria(model.getPartidaArancelaria());
		generico.setReferencia(model.getReferencia());
		generico.setSerie(model.getSerie());
		generico.setServicio(model.getServicio());
		generico.setTipoproductoId(model.getTipoproductoId());
		generico.setUsaLote(model.getUsaLote());
		generico.setLlevaInventario(model.getLlevaInventario());
		generico.setAceptaDescuento(model.getAceptaDescuento());
		return generico;
	}
	
	public Collection findGenericoPautaRegular(Long clienteId, String tipoProductoCodigo, Long empresaId, String cobraIvaProveedor, String cobraIvaCliente) throws GenericBusinessException {
		Collection genericos = null;
		Map queryMap = new HashMap();
		String queryString = "select g from GenericoEJB g, TipoProductoEJB tp, PautaGenericoClienteEJB pgc where g.id = pgc.genericoId and pgc.tipoProductoId = tp.id and pgc.clienteId = :clienteId and tp.codigo = :tipoProductoCodigo and g.cobraIva = :cobraIvaProveedor and g.cobraIvaCliente = :cobraIvaCliente";
		Query query = manager.createQuery(queryString);
		query.setParameter("clienteId", clienteId);
		query.setParameter("tipoProductoCodigo", tipoProductoCodigo);
		query.setParameter("cobraIvaProveedor", cobraIvaProveedor);
		query.setParameter("cobraIvaCliente", cobraIvaCliente);
		genericos = query.getResultList();
		if (genericos == null || genericos.size() <= 0) {
			queryMap = new HashMap();
			queryMap.put("empresaId", empresaId);
			queryMap.put("codigo", tipoProductoCodigo);
			Iterator<TipoProductoIf> it = tipoProductoSessionLocal.findTipoProductoByQuery(queryMap).iterator();
			if (it.hasNext()) {
				queryMap = new HashMap();
				TipoProductoIf tipoProducto = it.next();
				queryMap.put("tipoProductoId", tipoProducto.getId());
				queryMap.put("cobraIvaProveedor", cobraIvaProveedor);
				queryMap.put("cobraIvaCliente", cobraIvaCliente);
				genericos = findGenericoPautaRegularPorDefecto(queryMap);
			}
		}
		return genericos;
	}
	
	private Collection findGenericoPautaRegularPorDefecto(Map queryMap) {
		String queryString = "select g from GenericoEJB g where g.id not in (select pgc.genericoId from PautaGenericoClienteEJB pgc) and g.cobraIva = :cobraIvaProveedor and g.cobraIvaCliente = :cobraIvaCliente and g.tipoproductoId = :tipoProductoId";
		Query query = manager.createQuery(queryString);
		query.setParameter("tipoProductoId", (Long) queryMap.get("tipoProductoId"));
		query.setParameter("cobraIvaProveedor", (String) queryMap.get("cobraIvaProveedor"));
		query.setParameter("cobraIvaCliente", (String) queryMap.get("cobraIvaCliente"));
		return query.getResultList();
	}
	
	public Collection findGenericoByProductoId(Long productoId) throws GenericBusinessException {
		//SELECT DISTINCT G.* FROM GENERICO G, PRODUCTO P WHERE G.`ID` = P.`GENERICO_ID` AND P.ID = 380
		String queryString = "select distinct g from GenericoEJB g, ProductoEJB p where g.id = p.genericoId and p.id = :productoId";
		Query query = manager.createQuery(queryString);
		query.setParameter("productoId", productoId);
		return query.getResultList();
	}
}
