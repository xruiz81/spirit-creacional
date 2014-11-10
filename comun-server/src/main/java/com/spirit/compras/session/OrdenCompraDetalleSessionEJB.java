package com.spirit.compras.session;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

import javax.ejb.*;
import javax.persistence.Query;

import com.spirit.compras.session.generated._OrdenCompraDetalleSession;
import com.spirit.compras.session.OrdenCompraDetalleSessionLocal;
import com.spirit.compras.session.OrdenCompraDetalleSessionRemote;
import com.spirit.compras.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class OrdenCompraDetalleSessionEJB extends _OrdenCompraDetalleSession implements OrdenCompraDetalleSessionRemote,OrdenCompraDetalleSessionLocal  {
	
	private static final String AGRUPAR_POR_CLIENTE_PRODUCTO_TMEDIOS_MEDIOS = "CPTM";
	private static final String AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO = "CTMP";
	private static final String AGRUPAR_POR_TMEDIOS_MEDIOS_CLIENTE_PRODUCTO = "TMCP";

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenCompraByQueryByProductoAndByFechas(Map<String,Long> mapaOrdenesCompraDetalle, Timestamp fechaInicio, Timestamp fechaFin, Long empresaId, String agruparBy) {
		
		String datosRequeridos = "";
		if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
			datosRequeridos = "cl.nombreLegal, cl.id, prov.nombreLegal, prov.id, pc.nombre, pc.id, tp.nombre, tp.id, pr.fecha, ocd.valor, ocd.cantidad, ocd.descuento, pr.porcentajeComisionAgencia, cl.identificacion";
		}else{
			datosRequeridos = "clo.descripcion, clo.id, provOfi.descripcion, provOfi.id, pc.nombre, pc.id, tp.nombre, tp.id, pr.fecha, ocd.valor, ocd.cantidad, ocd.descuento, pr.porcentajeComisionAgencia";
		}
		
		String queryString = "select distinct " + datosRequeridos + " " +
				"from OrdenCompraDetalleEJB ocd, OrdenCompraEJB oc, SolicitudCompraEJB sc, PresupuestoEJB pr, PresupuestoProductoEJB pp, " +
				"OrdenTrabajoDetalleEJB otd, OrdenTrabajoEJB ot, ClienteOficinaEJB clo, ClienteEJB cl, ProductoClienteEJB pc, MarcaProductoEJB mp, OficinaEJB ofi, " +
				"ClienteOficinaEJB provOfi, ClienteEJB prov, TipoProveedorEJB tp " +
				"where ocd.ordencompraId = oc.id and oc.solicitudcompraId = sc.id and sc.tipoReferencia = 'P' and sc.referencia = pr.codigo and pr.ordentrabajodetId = otd.id " +
				"and otd.ordenId = ot.id and ot.clienteoficinaId = clo.id and clo.clienteId = cl.id and pp.presupuestoId = pr.id and pp.productoClienteId = pc.id " +
				"and pc.marcaProductoId = mp.id and ot.oficinaId = ofi.id and provOfi.clienteId = prov.id and oc.proveedorId = provOfi.id and prov.tipoproveedorId = tp.id " +
				"and ofi.empresaId = " + empresaId + " and tp.tipo = 'M' and pr.fecha between :fechaInicio and :fechaFin";
		
		if(mapaOrdenesCompraDetalle.get("clienteOficinaId") != null){
			queryString += " and clo.id = " + mapaOrdenesCompraDetalle.get("clienteOficinaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("clienteId") != null){
			queryString += " and cl.id = " + mapaOrdenesCompraDetalle.get("clienteId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("proveedorOficinaId") != null){
			queryString += " and oc.proveedorId = " + mapaOrdenesCompraDetalle.get("proveedorOficinaId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("proveedorId") != null){
			queryString += " and oc.proveedorId in (select orc.proveedorId from OrdenCompraEJB orc, ClienteOficinaEJB clof, ClienteEJB cli " +
					"where orc.proveedorId = clof.id and clof.clienteId = cli.id and cli.id = " + mapaOrdenesCompraDetalle.get("proveedorId") + " )";
		}
		if(mapaOrdenesCompraDetalle.get("productoClienteId") != null){
			queryString += " and pc.id = " + mapaOrdenesCompraDetalle.get("productoClienteId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("marcaProductoId") != null){
			queryString += " and mp.id = " + mapaOrdenesCompraDetalle.get("marcaProductoId") + "";
		}
		if(mapaOrdenesCompraDetalle.get("tipoProveedorId") != null){
			queryString += " and oc.proveedorId in (select clofi.id from ClienteOficinaEJB clofi, ClienteEJB clie " +
					"where clofi.clienteId = clie.id and clie.tipoproveedorId = " + mapaOrdenesCompraDetalle.get("tipoProveedorId") + " )";
		}
		
		if (agruparBy.compareTo(AGRUPAR_POR_CLIENTE_TMEDIOS_MEDIOS_PRODUCTO) == 0){
			queryString = queryString + " order by cl.nombreLegal, tp.nombre, prov.nombreLegal, pc.nombre";
		}else{
			queryString = queryString + " order by clo.descripcion, pc.nombre, tp.nombre, provOfi.descripcion";
		}
		
		
		//queryString += " order by oc.codigo asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio);
		query.setParameter("fechaFin",fechaFin);
		return query.getResultList();
	}

}
