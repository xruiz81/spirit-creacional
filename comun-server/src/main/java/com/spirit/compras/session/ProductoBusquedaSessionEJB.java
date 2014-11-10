package com.spirit.compras.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.server.QueryBuilder;

@Stateless
public class ProductoBusquedaSessionEJB implements ProductoBusquedaSessionRemote,ProductoBusquedaSessionLocal {

	private static final String CODIGO_TIPO_PRODUCTO_AMBOS = "A";
	private static final String CODIGO_TIPO_PRODUCTO_PRODUCCION = "P";
	private static final String CODIGO_TIPO_PRODUCTO_MEDIOS = "M";

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getProductoList(int startIndex, int endIndex, String tipoReferencia, Map aMap, Long idReferencia, String tipoProducto, String servicioConsumo, String mmpg) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String codigoTipoProductoMedios=CODIGO_TIPO_PRODUCTO_MEDIOS, codigoTipoProductoProduccion=CODIGO_TIPO_PRODUCTO_PRODUCCION;
		String nombreGenerico = (String) aMap.get("nombreGenerico");
		String presentacion = (String) aMap.get("presentacion");
		aMap.remove("nombreGenerico");
		aMap.remove("presentacion");
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
		
		if (codigo!=null && !codigo.equals(""))
			whereParameters += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";

		// Si el tipo de referencia es igual a "R" entonces quiere decir que la búsqueda de productos
		// estará basada en un presupuesto u orden de medios.
		if (tipoReferencia.equals("R")) {
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
			
			String whereQueryBuilderString = "";
			if (whereQueryBuilder != null && !whereQueryBuilder.trim().equals(""))
				whereQueryBuilderString += whereQueryBuilder + " and ";

			//select distinct p.* from producto p, generico g where p.GENERICO_ID = g.ID and g.TIPOPRODUCTO_ID = 1
			if (presentacion!=null && !presentacion.equals("")){
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, " + referenceObject + " ro where " + whereQueryBuilderString +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilderString +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
			} else{
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilderString + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct " + objectName + " from ProductoEJB " + objectName + ", GenericoEJB g, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilderString + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
			}
		}

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
			if (whereQueryBuilder != null && !whereQueryBuilder.trim().equals("")) {
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct p from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct p from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + whereParameters;
			} else {
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct p from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct p from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + whereParameters;
			}
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
		aMap.remove("nombreGenerico");
		aMap.remove("presentacion");
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
		
		if (codigo!=null && !codigo.equals(""))
			whereParameters += " and (" + objectName + ".codigo like '" + codigo + "' or " + objectName + ".codigoBarras like '" + codigo + "')";

		// Si el tipo de referencia es igual a "R" entonces quiere decir que la búsqueda de productos
		// estará basada en un presupuesto u orden de medios.
		if (tipoReferencia.equals("R")) {
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
			
			String whereQueryBuilderString = "";
			if (whereQueryBuilder != null && !whereQueryBuilder.trim().equals(""))
				whereQueryBuilderString += whereQueryBuilder + " and ";

			//select distinct p.* from producto p, generico g where p.GENERICO_ID = g.ID and g.TIPOPRODUCTO_ID = 1
			if (presentacion!=null && !presentacion.equals("")) {
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, " + referenceObject + " ro where " + whereQueryBuilderString +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, PresentacionEJB pr, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilderString +  objectName + ".presentacionId = pr.id and " + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
			} else {
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilderString + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".id and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, OrdenCompraEJB oc, OrdenCompraDetalleEJB ocd where " + whereQueryBuilderString + objectName + ".genericoId = g.id and oc.id = ocd.ordencompraId and ocd.productoId = " + objectName + ".id and oc.id = " + idReferencia + whereParameters;
			}
		}

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
			if (whereQueryBuilder != null && !whereQueryBuilder.trim().equals("")) {
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + whereQueryBuilder + " and " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + whereParameters;
			} else {
				if (tipoProducto.equals(CODIGO_TIPO_PRODUCTO_AMBOS) == false)
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + where + whereParameters;
				else
					queryString = "select distinct count(*) from ProductoEJB " + objectName + ", GenericoEJB g, " + referenceObject + " ro where " + objectName + ".genericoId = g.id and ro." + referenceColumn + " = " + objectName + ".proveedorId and ro." + referenceColumnIdReferencia + " = " + idReferencia + whereParameters;
			}
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
		//log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}


}
