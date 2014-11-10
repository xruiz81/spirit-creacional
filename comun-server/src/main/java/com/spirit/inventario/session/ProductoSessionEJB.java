package com.spirit.inventario.session;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.GenericoEJB;
import com.spirit.inventario.entity.ProductoEJB;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.session.generated._ProductoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.criteria.AND;
import com.truemesh.squiggle.criteria.MatchCriteria;
import com.truemesh.squiggle.criteria.OR;

/**
 * The <code>ProductoSession</code> session bean, which acts as a facade to
 * the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 * 
 */
@Stateless
public class ProductoSessionEJB extends _ProductoSession implements ProductoSessionRemote,
		ProductoSessionLocal {

	private static final String CODIGO_TIPO_PRODUCTO_AMBOS = "A";
	private static final String CODIGO_TIPO_PRODUCTO_PRODUCCION = "P";
	private static final String CODIGO_TIPO_PRODUCTO_MEDIOS = "M";

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	@EJB private ClienteOficinaSessionLocal clienteOficinaSessionLocal;
	@EJB private ClienteSessionLocal clienteSessionLocal;
	@EJB private JPAManagerLocal jpManagerLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(ProductoSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/
	private static BigDecimal generarDigitoVerificador(String codigo) {
		// VERIFICAR LONGITUD
		int longitud = codigo.length();
		BigDecimal sumaImpares = BigDecimal.ZERO;
		BigDecimal sumaPares = BigDecimal.ZERO;
		BigDecimal temp = null;

		for (int i = 1; i <= longitud; i = i + 2) {
			temp = new BigDecimal(codigo.substring(i, i + 1))
					.multiply(new BigDecimal(3));
			sumaImpares = sumaImpares.add(temp);
		}

		for (int i = 0; i < longitud; i = i + 2) {
			temp = new BigDecimal(codigo.substring(i, i + 1));
			sumaPares = sumaPares.add(temp);
		}

		BigDecimal sumaTotal = sumaImpares.add(sumaPares);

		return sumaTotal.setScale(-1, RoundingMode.UP).subtract(sumaTotal);
	}

	private String getSerial(String codigoParcial) {
		String queryString = "Select max(producto.codigoBarras) from ProductoEJB producto where producto.codigoBarras like '"+codigoParcial+"%'";
		Query query = manager.createQuery(queryString);
		try {
			String codigoMax = (String) query.getSingleResult();
			double d=Double.parseDouble(codigoMax.substring(8, 12))+1;			
			return formatoSerial3.format(d);
		} catch (Exception e) {
			//e.printStackTrace();
			return "0001";
		}
	}

	private DecimalFormat formatoSerial1 = new DecimalFormat("0");
	private DecimalFormat formatoSerial2 = new DecimalFormat("00");
	private DecimalFormat formatoSerial3 = new DecimalFormat("000");
	private DecimalFormat formatoSerial4 = new DecimalFormat("0000");

	public String generarCodigoBarras(ProductoIf productoIf, TipoProductoIf tipoProductoIf,LineaIf lineaIf) throws GenericBusinessException {
		String codigoBarras = "20";
		if (!tipoProductoIf.getCodigo().equals("NIN")) {
			ClienteOficinaIf clienteOficinaIf = clienteOficinaSessionLocal.getClienteOficina(productoIf.getProveedorId());
			if(clienteOficinaIf==null)
			{
				System.out.println("ERROR GRAVE!! "+productoIf.getProveedorId());
				System.out.println("ERROR GRAVE!! "+productoIf.getId());
				return null;

			}
			String codigoProveedorAuto = clienteOficinaIf.getCodigoProveedorAuto();
			if (codigoProveedorAuto == null) {
				codigoProveedorAuto = clienteSessionLocal.generarCodigoProveedor(tipoProductoIf.getEmpresaId());
			}
			clienteOficinaIf.setCodigoProveedorAuto(codigoProveedorAuto);
			manager.merge(clienteOficinaIf);

			codigoBarras += formatoSerial3.format(Double.parseDouble(clienteOficinaIf.getCodigoProveedorAuto()));
			codigoBarras += formatoSerial2.format(Double.parseDouble(tipoProductoIf.getCodigo()));
			codigoBarras += formatoSerial1.format(Double.parseDouble(lineaIf.getCodigo()));
			codigoBarras += formatoSerial4.format(Double.parseDouble(getSerial(codigoBarras)));
			codigoBarras += generarDigitoVerificador(codigoBarras);
		}
		return codigoBarras;
	}
	
	public String generarDigitoVerificadorCodigoBarras(String codigoBarras) throws GenericBusinessException {
		return String.valueOf(generarDigitoVerificador(codigoBarras).intValue());
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findProductoByQuery(int startIndex, int endIndex, Map aMap, Long idEmpresa, String servicioConsumo, String mmpg) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String nombreGenerico = (String) aMap.get("nombreGenerico");
		String presentacion = (String) aMap.get("presentacion");
		String modelo = (String) aMap.get("modelo");
		aMap.remove("nombreGenerico");
		aMap.remove("presentacion");
		aMap.remove("modelo");
		String codigo = (String) aMap.get("codigo");
		aMap.remove("codigo");
		
		String whereParameters = "";
		if (nombreGenerico!=null && !nombreGenerico.equals(""))
			whereParameters += " and g.nombre like '" + nombreGenerico + "%'";
		if (presentacion!=null && !presentacion.equals(""))
			whereParameters += " and pr.nombre like '" + presentacion + "%'";
		if (modelo!=null && !modelo.equals(""))
			whereParameters += " and m.nombre like '" + modelo + "%'";
		if (codigo!=null && !codigo.equals(""))
			whereParameters += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";
		
		String where = QueryBuilder.buildWhere(aMap, objectName);
		//String queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr where " 
		//+ where + " and " + objectName + ".genericoId = g.id and g.empresaId = " + idEmpresa + whereParameters;
		
		String queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g";
		
		if (presentacion!=null && !presentacion.equals(""))
			queryString += ", PresentacionEJB pr";
		if (modelo!=null && !modelo.equals(""))
			queryString += ", ModeloEJB m";
		
		queryString += " where ";
		if (where != null && !where.trim().equals(""))
			queryString += where;
		else
			queryString += " 1 = 1 ";
		
		queryString += whereParameters + " and " + objectName + ".genericoId = g.id and g.empresaId = " + idEmpresa ;
		
		//queryString += (" and "+objectProducto+".genericoId="+objectGenerico+".id");
		
		if (presentacion!=null && !presentacion.equals(""))
			queryString += (" and " + objectName + ".presentacionId=pr.id ");
		if (modelo!=null && !modelo.equals(""))
			queryString += (" and " + objectName + ".modeloId=m.id");
		//else
		//queryString += (" and (" + objectName + ".presentacionId=pr.id or " + objectName + ".presentacionId=null)");
		
		if (!servicioConsumo.equals("A")) {
			if (servicioConsumo.equals("C"))
				queryString += " and g.servicio = 'N'";
			else if (servicioConsumo.equals("S"))
				queryString += " and g.servicio = 'S'";
		}
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";
		
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
	public int getProductoByQueryListSize(Map aMap, Long idEmpresa, String servicioConsumo, String mmpg) {
		String objectName = "e";
		String nombreGenerico = (String) aMap.get("nombreGenerico");
		String presentacion = (String) aMap.get("presentacion");
		String modelo = (String) aMap.get("modelo");
		aMap.remove("nombreGenerico");
		aMap.remove("presentacion");
		aMap.remove("modelo");
		String codigo = (String) aMap.get("codigo");
		aMap.remove("codigo");
		
		String whereParameters = "";
		if (nombreGenerico!=null && !nombreGenerico.equals(""))
			whereParameters += " and g.nombre like '" + nombreGenerico + "%'";
		if (presentacion!=null && !presentacion.equals(""))
			whereParameters += " and pr.nombre like '" + presentacion + "%'";
		if (modelo!=null && !modelo.equals(""))
			whereParameters += " and m.nombre like '" + modelo + "%'";
		if (codigo!=null && !codigo.equals(""))
			whereParameters += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";
		
		String where = QueryBuilder.buildWhere(aMap, objectName);
		//String queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr where " 
		//+ where + " and " + objectName + ".genericoId = g.id and g.empresaId = " + idEmpresa + whereParameters;
		
		String queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g";
		
		if (presentacion!=null && !presentacion.equals(""))
			queryString += ", PresentacionEJB pr";
		if (modelo!=null && !modelo.equals(""))
			queryString += ", ModeloEJB m";
		
		queryString += " where ";
		if (where != null && !where.trim().equals(""))
			queryString += where;
		else
			queryString += " 1=1 ";
		
		queryString += whereParameters + " and " + objectName + ".genericoId = g.id and g.empresaId = " + idEmpresa ;
		
		//queryString += (" and "+objectProducto+".genericoId="+objectGenerico+".id");
		
		if (presentacion!=null && !presentacion.equals(""))
			queryString += (" and " + objectName + ".presentacionId=pr.id ");
		if (modelo!=null && !modelo.equals(""))
			queryString += (" and " + objectName + ".modeloId=m.id");
		//else
		//queryString += (" and (" + objectName + ".presentacionId=pr.id or " + objectName + ".presentacionId=null)");
		
		if (!servicioConsumo.equals("A") && !servicioConsumo.equals("")) {
			if (servicioConsumo.equals("C"))
				queryString += " and g.servicio = 'N'";
			else if (servicioConsumo.equals("S"))
				queryString += " and g.servicio = 'S'";
		}
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";
		
		System.out.println(queryString);
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

	public java.util.Collection findProductoByNombreGenerico(int startIndex,
			int endIndex, String nombreGenerico, String servicioConsumo,
			Long proveedorId, String mmpg) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String queryString = "select distinct p from ProductoEJB p, GenericoEJB g where p.genericoId = g.id and g.nombre like '"
				+ nombreGenerico + "%' and p.estado = 'A'";

		if (proveedorId != null)
			queryString += " and p.proveedorId = " + proveedorId;

		if (!servicioConsumo.equals("A")) {
			if (servicioConsumo.equals("C"))
				queryString += " and g.servicio = 'N'";
			else if (servicioConsumo.equals("S"))
				queryString += " and g.servicio = 'S'";
		}

		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	public int getProductoByNombreGenericoListSize(String nombreGenerico,
			String servicioConsumo, Long proveedorId, String mmpg)
			throws GenericBusinessException {
		String queryString = "select distinct count(*) from ProductoEJB p, GenericoEJB g where p.genericoId = g.id and g.nombre like '"
				+ nombreGenerico + "%' and p.estado = 'A' ";

		if (proveedorId != null)
			queryString += " and p.proveedorId = " + proveedorId;

		if (!servicioConsumo.equals("A")) {
			if (servicioConsumo.equals("C"))
				queryString += " and g.servicio = 'N'";
			else if (servicioConsumo.equals("S"))
				queryString += " and g.servicio = 'S'";
		}

		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";

		Query query = manager.createQuery(queryString);
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getProductoList(int startIndex, int endIndex, String tipoReferencia, Map aMap, Long idReferencia, String tipoProducto, String servicioConsumo, String mmpg) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		String codigoTipoProductoMedios=CODIGO_TIPO_PRODUCTO_MEDIOS, codigoTipoProductoProduccion=CODIGO_TIPO_PRODUCTO_PRODUCCION;
		String nombreGenerico = (String) aMap.get("nombreGenerico");
		String presentacion = (String) aMap.get("presentacion");
		String modelo = (String) aMap.get("modelo");
		aMap.remove("nombreGenerico");
		aMap.remove("presentacion");
		aMap.remove("modelo");
		String codigo = (String) aMap.get("codigo");
		aMap.remove("codigo");
		String objectName = "p";
		String referenceObject = "";
		String referenceColumn = "";
		String referenceColumnIdReferencia = "";
		String where = "";
		String queryString = "";
		String whereQueryBuilder = QueryBuilder.buildWhere(aMap, objectName);
		String whereParameters = "";
		
		if (nombreGenerico!=null && !nombreGenerico.equals(""))
			whereParameters += " and g.nombre like '" + nombreGenerico + "%' ";
		if (presentacion!=null && !presentacion.equals(""))
			whereParameters += " and pr.nombre like '" + presentacion + "%' ";
		if (modelo!=null && !modelo.equals(""))
			whereParameters += " and m.nombre like '" + modelo + "%' ";
		if (codigo!=null && !codigo.equals(""))
			whereParameters += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";
		
		// Si el tipo de referencia es igual a "R" entonces quiere decir que la búsqueda de productos
		// estará basada en un presupuesto u orden de medios.
		/*if (tipoReferencia.equals("R")) {
		 // Si tipoProducto==consumo entonces buscamos productos de consumo
		  if (tipoProducto.equals(codigoTipoProductoProduccion)) {
		  where += " and g.mediosProduccion = 'P'";
		  referenceObject = "PresupuestoDetalleEJB";
		  referenceColumn = "productoId";
		  referenceColumnIdReferencia = "presupuestoId";
		  }
		  
		  // Si tipoProducto==servicio entonces buscamos productos de servicio
		   if (tipoProducto.equals(codigoTipoProductoMedios)) {
		   where += " and g.mediosProduccion = 'M'";
		   referenceObject = "OrdenMedioEJB";
		   referenceColumn = "productoProveedorId";
		   referenceColumnIdReferencia = "id";
		   }
		   
		   //select distinct p.* from producto p, generico g where p.GENERICO_ID = g.ID and g.TIPOPRODUCTO_ID = 1
		    if (presentacion!=null && !presentacion.equals("")){
		    if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
		    queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, " + referenceObject + " ro where " + whereQueryBuilder + " and " +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
		    else
		    queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilder + " and " +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
		    } else{
		    if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
		    queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
		    else
		    queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
		    }
		    }*/
		
		// Si el tipo de referencia es igual a "P" entonces quiere decir que la búsqueda de productos estará basada en un proveedor.
		if (tipoReferencia.equals("P")) {
			referenceObject = "ClienteOficinaEJB";
			referenceColumn = "id";
			referenceColumnIdReferencia = "id";
			
			// Si tipoProducto==consumo entonces buscamos productos de consumo
			if (tipoProducto.equals(codigoTipoProductoProduccion))
				where += " and g.mediosProduccion = 'P'";
			
			// Si tipoProducto==servicio entonces buscamos productos de servicio
			if (tipoProducto.equals(codigoTipoProductoMedios))
				where += " and g.mediosProduccion = 'M'";
			
			//select distinct p.* from producto p, generico g where p.GENERICO_ID = g.ID and g.TIPOPRODUCTO_ID = 1
			queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro";
			
			if (presentacion!=null && !presentacion.equals(""))
				queryString += ", PresentacionEJB pr";
			if (modelo!=null && !modelo.equals(""))
				queryString += ", ModeloEJB m";
			
			queryString += " where ";
			if (whereQueryBuilder != null && !whereQueryBuilder.trim().equals(""))
				queryString += whereQueryBuilder + " and ";
			
			if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
				queryString += objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
			else
				queryString += objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + whereParameters;
		}
		
		if (!servicioConsumo.equals("A")) {
			if (servicioConsumo.equals("C"))
				queryString += " and g.servicio = 'N'";
			else if (servicioConsumo.equals("S"))
				queryString += " and g.servicio = 'S'";
		}
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";
		
		if (presentacion!=null && !presentacion.equals(""))
			queryString += (" and " + objectName + ".presentacionId=pr.id ");
		if (modelo!=null && !modelo.equals(""))
			queryString += (" and " + objectName + ".modeloId=m.id");
		
		Query query = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		
		System.out.println(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getProductoListSize(String tipoReferencia, Map aMap, Long idReferencia, String tipoProducto, String servicioConsumo, String mmpg) {
		String codigoTipoProductoProduccion=CODIGO_TIPO_PRODUCTO_PRODUCCION, codigoTipoProductoMedios=CODIGO_TIPO_PRODUCTO_MEDIOS;
		String nombreGenerico = (String) aMap.get("nombreGenerico");
		String presentacion = (String) aMap.get("presentacion");
		String modelo = (String) aMap.get("modelo");
		aMap.remove("nombreGenerico");
		aMap.remove("presentacion");
		aMap.remove("modelo");
		String codigo = (String) aMap.get("codigo");
		aMap.remove("codigo");
		String objectName = "p";
		String referenceObject = "";
		String referenceColumn = "";
		String referenceColumnIdReferencia = "";
		String where = "";
		String queryString = "";
		String whereQueryBuilder = QueryBuilder.buildWhere(aMap, objectName);
		String whereParameters = "";
		
		if (nombreGenerico!=null && !nombreGenerico.equals(""))
			whereParameters += " and g.nombre like '" + nombreGenerico + "%'";
		if (presentacion!=null && !presentacion.equals(""))
			whereParameters += " and pr.nombre like '" + presentacion + "%'";
		if (modelo!=null && !modelo.equals(""))
			whereParameters += " and m.nombre like '" + modelo + "%'";
		if (codigo!=null && !codigo.equals(""))
			whereParameters += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";
		
		// Si el tipo de referencia es igual a "R" entonces quiere decir que la búsqueda de productos
		// estará basada en un presupuesto u orden de medios.
		/*if (tipoReferencia.equals("R")) {
		 // Si tipoProducto==consumo entonces buscamos productos de consumo
		  if (tipoProducto.equals(codigoTipoProductoProduccion)) {
		  where += " and g.mediosProduccion = 'P'";
		  referenceObject = "PresupuestoDetalleEJB";
		  referenceColumn = "productoId";
		  referenceColumnIdReferencia = "presupuestoId";
		  }
		  
		  // Si tipoProducto==servicio entonces buscamos productos de servicio
		   if (tipoProducto.equals(codigoTipoProductoMedios)) {
		   where += " and g.mediosProduccion = 'M'";
		   referenceObject = "OrdenMedioEJB";
		   referenceColumn = "productoProveedorId";
		   referenceColumnIdReferencia = "id";
		   }
		   
		   //select distinct p.* from producto p, generico g where p.GENERICO_ID = g.ID and g.TIPOPRODUCTO_ID = 1
		    if (presentacion!=null && !presentacion.equals("")) {
		    if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
		    queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, " + referenceObject + " ro where " + whereQueryBuilder + " and " +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
		    else
		    queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilder + " and " +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
		    } else {
		    if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
		    queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
		    else
		    queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
		    }
		    }*/
		
		// Si el tipo de referencia es igual a "P" entonces quiere decir que la búsqueda de productos estará basada en un proveedor.
		if (tipoReferencia.equals("P")) {
			referenceObject = "ClienteOficinaEJB";
			referenceColumn = "id";
			referenceColumnIdReferencia = "id";
			
			// Si tipoProducto==consumo entonces buscamos productos de consumo
			if (tipoProducto.equals(codigoTipoProductoProduccion))
				where += " and g.mediosProduccion = 'P'";
			
			// Si tipoProducto==servicio entonces buscamos productos de servicio
			if (tipoProducto.equals(codigoTipoProductoMedios))
				where += " and g.mediosProduccion = 'M'";
			
			queryString = "select distinct count (*) from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro";
			
			if (presentacion!=null && !presentacion.equals(""))
				queryString += ", PresentacionEJB pr";
			if (modelo!=null && !modelo.equals(""))
				queryString += ", ModeloEJB m";
			
			queryString += " where ";
			if (whereQueryBuilder != null && !whereQueryBuilder.trim().equals(""))
				queryString += whereQueryBuilder + " and ";
	
			if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
				queryString += objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
			else
				queryString += objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + whereParameters;
		}
		
		if (!servicioConsumo.equals("A")) {
			if (servicioConsumo.equals("C"))
				queryString += " and g.servicio = 'N'";
			else if (servicioConsumo.equals("S"))
				queryString += " and g.servicio = 'S'";
		}
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";
		
		if (presentacion!=null && !presentacion.equals(""))
			queryString += (" and " + objectName + ".presentacionId=pr.id ");
		if (modelo!=null && !modelo.equals(""))
			queryString += (" and " + objectName + ".modeloId=m.id");
		
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findProductoByQuery(int startIndex,
			int endIndex, Map aMap, String mmpg) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String codigo = (String) aMap.get("codigo");
		aMap.remove("codigo");
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct from ProductoEJB " + objectName;
		
		queryString += " where ";
		if (where != null && !where.trim().equals(""))
			queryString += where;
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";
		
		if (codigo!=null && !codigo.equals(""))
			queryString += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";
		
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
	public int getProductoByQueryListSize(Map aMap, String mmpg) {
		String objectName = "e";
		String codigo = (String) aMap.get("codigo");
		aMap.remove("codigo");
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct count(*) from ProductoEJB " + objectName;
		
		queryString += " where ";
		if (where != null && !where.trim().equals(""))
			queryString += where;
		
		if (mmpg.toUpperCase().contains("M"))
			queryString += " and g.mediosProduccion <> 'M'";
		if (mmpg.toUpperCase().contains("P"))
			queryString += " and g.mediosProduccion <> 'P'";
		if (mmpg.toUpperCase().contains("G"))
			queryString += " and g.mediosProduccion <> 'G'";
		if (mmpg.toUpperCase().contains("O"))
			queryString += " and g.mediosProduccion <> 'O'";
		
		if (codigo!=null && !codigo.equals(""))
			queryString += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";
		
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ProductoEJB registrarProducto(ProductoIf modelProducto) {
		ProductoEJB producto = new ProductoEJB();

		producto.setAceptadevolucion(modelProducto.getAceptadevolucion());
		producto.setAceptapromocion(modelProducto.getAceptapromocion());
		producto.setCambioprecio(modelProducto.getCambioprecio());
		producto.setCodigo(modelProducto.getCodigo());
		producto.setCodigoBarras(modelProducto.getCodigoBarras());

		if (modelProducto.getCosto() != null) {
			producto.setCosto(utilitariosLocal.redondeoValor(modelProducto
					.getCosto()));
		} else {
			producto.setCosto(BigDecimal.valueOf(Double.parseDouble("0")));
		}

		producto.setEstado(modelProducto.getEstado());
		producto.setFechaCreacion(modelProducto.getFechaCreacion());
		producto.setGenericoId(modelProducto.getGenericoId());
		producto.setId(modelProducto.getId());

		if (modelProducto.getMargenminimo() != null) {
			producto.setMargenminimo(utilitariosLocal
					.redondeoValor(modelProducto.getMargenminimo()));
		} else {
			producto.setMargenminimo(BigDecimal
					.valueOf(Double.parseDouble("0")));
		}

		producto.setOrigenProducto(modelProducto.getOrigenProducto());
		producto.setPermiteventa(modelProducto.getPermiteventa());
		producto.setPresentacionId(modelProducto.getPresentacionId());
		producto.setProveedorId(modelProducto.getProveedorId());

		if (modelProducto.getRebate() != null) {
			producto.setRebate(utilitariosLocal.redondeoValor(modelProducto
					.getRebate()));
		} else {
			producto.setRebate(BigDecimal.valueOf(Double.parseDouble("0")));
		}

		producto.setSubproveedor(modelProducto.getSubproveedor());
		producto.setPesoBruto(modelProducto.getPesoBruto());
		producto.setPesoNeto(modelProducto.getPesoNeto());
		producto.setColorId(modelProducto.getColorId());
		producto.setMarcaId(modelProducto.getMarcaId());
		producto.setModeloId(modelProducto.getModeloId());
		producto.setGenerarCodigoBarras(modelProducto.getGenerarCodigoBarras());

		return producto;
	}

	public String getNombreProductoByProductoId(Long productoId) throws GenericBusinessException{
		try {
			String query = "select g.nombre from ProductoEJB p , GenericoEJB g " +
					"where p.genericoId = g.id and p.id = "+productoId;
			Query countQuery = manager.createQuery(query);
			List countQueryResult = countQuery.getResultList();
			String nombreProducto = (String) countQueryResult.get(0);
			return nombreProducto;
		} catch (Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en la consulta del nombre del Producto !!");
		}
	}
	
	public String findDescripcionUnoProductoByCodigoBarra(String codigoBarra,int tipoDescripcion) throws GenericBusinessException{

		String query = "";
		
		if ( tipoDescripcion == 0 )
			query = "select g.nombre,m.nombre,c.nombre,ps.nombre " +
				" from ProductoEJB p, GenericoEJB g,PresentacionEJB ps, ModeloEJB m,ColorEJB c " +
				" where p.genericoId = g.id and p.presentacionId = ps.id " +
				" and p.modeloId = m.id and p.colorId = c.id " +
				" and p.codigoBarras = :codigoBarras ";
		else 
			query = "select distinct g.nombre" +
			" from ProductoEJB p, GenericoEJB g " +
			" where p.genericoId = g.id and p.codigoBarras = :codigoBarras ";
		
		try{
			Query countQuery = manager.createQuery(query);
			countQuery.setParameter("codigoBarras", codigoBarra);
			List countQueryResult = countQuery.getResultList();
			if ( countQueryResult.size() > 0 ){
				if ( tipoDescripcion == 0 ){
					Object[] fila = (Object[]) countQueryResult.get(0);
					String nombreGenerico = (String)fila[0];
					System.out.println(" Descripcion 0: ----"+nombreGenerico);
					String nombreMarca = (String)fila[1];
					System.out.println(" Descripcion 1: ----"+nombreMarca);
					String nombreColor = (String)fila[2];
					System.out.println(" Descripcion 2: ----"+nombreColor);
					String nombrePresentacion = (String)fila[3];
					System.out.println(" Descripcion 3: ----"+nombrePresentacion);
					String respuesta = nombreMarca+" - "+nombreColor+" ("+nombrePresentacion+")"; 
					System.out.println(" Descripcion : ----"+respuesta);
					return respuesta;
				}else if ( tipoDescripcion == 1 ){
					return (String)countQueryResult.get(0);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		throw new GenericBusinessException("Error al consultar producto con Codigo de Barras : "+codigoBarra);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findProductoByEmpresaIdAndByQuery(Long idEmpresa, Map aMap) throws GenericBusinessException {
		String objectName = "p";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct p from ProductoEJB " + objectName + ", GenericoEJB g where " + where;
		if (!where.equals("") && where!=null)
			queryString += " and ";
		queryString += "g.id = p.genericoId and g.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findProductoByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		String objectName = "p";
		String queryString = "select distinct p from ProductoEJB " + objectName + ", GenericoEJB g where g.id = p.genericoId and g.empresaId = " + idEmpresa;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findProductoByCriterioMap(Map<String,Object> aMap)throws GenericBusinessException {
		String strCriterio = (String) aMap.get("strCriterio");
		String strEstado = (String) aMap.get("estado");
		Long strProveedorId = (Long) aMap.get("proveedorId"); 
		SelectQuery select = new SelectQuery();
		Table productoTable = new Table(ProductoEJB.class);
		Table genericoTable = new Table(GenericoEJB.class);
		select.addObject(productoTable);
		select.addJoin(productoTable, "genericoId", genericoTable, "id");
		MatchCriteria codigo = new MatchCriteria(productoTable, "codigo", MatchCriteria.LIKE, strCriterio);
		MatchCriteria codigoBarras = new MatchCriteria(productoTable, "codigoBarras", MatchCriteria.LIKE, strCriterio);
		MatchCriteria nombre = new MatchCriteria(genericoTable, "nombre", MatchCriteria.LIKE, strCriterio);
		MatchCriteria estado = new MatchCriteria(productoTable, "estado", MatchCriteria.EQUALS, strEstado);
		MatchCriteria proveedorId = new MatchCriteria(productoTable, "proveedorId", MatchCriteria.EQUALS, strProveedorId);
		select.addCriteria(new AND(new AND(new OR(new OR(codigo, codigoBarras), nombre), estado), proveedorId));
		return jpManagerLocal.executeQueryList(select.getQueryString(), null);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findProductoByCriterioMap(int startIndex, int endIndex, Map<String,Object> aMap)throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String strCriterio = (String) aMap.get("strCriterio");
		String strEstado = (String) aMap.get("estado");
		Long strProveedorId = (Long) aMap.get("proveedorId"); 
		String queryString = "select distinct p from ProductoEJB p, GenericoEJB g where p.genericoId = g.id and p.estado = '" + strEstado + "' and p.proveedorId = " + strProveedorId + " and (p.codigo like '" + strCriterio + "' or p.codigoBarras like '" + strCriterio + "' or g.nombre like '" + strCriterio + "')"; 
		Query query = manager.createQuery(queryString);
		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return (query.getResultList());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int findProductoByCriterioMapSize(Map<String,Object> aMap)throws GenericBusinessException {
		String strCriterio = (String) aMap.get("strCriterio");
		String strEstado = (String) aMap.get("estado");
		Long strProveedorId = (Long) aMap.get("proveedorId"); 
		String queryString = "select distinct count(*) from ProductoEJB p, GenericoEJB g where p.genericoId = g.id and p.estado = '" + strEstado + "' and p.proveedorId = " + strProveedorId + " and (p.codigo like '" + strCriterio + "' or p.codigoBarras like '" + strCriterio + "' or g.nombre like '" + strCriterio + "')"; 
		Query query = manager.createQuery(queryString);
		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}
}