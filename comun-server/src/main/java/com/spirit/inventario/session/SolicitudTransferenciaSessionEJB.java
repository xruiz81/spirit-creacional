package com.spirit.inventario.session;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.comun.querys.QueryHelperServerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.mdb.messages.bo.SolicitudTransferenciaMessageLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.MovimientoDetalleEJB;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoEJB;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleEJB;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.entity.SolicitudTransferenciaEJB;
import com.spirit.inventario.entity.SolicitudTransferenciaIf;
import com.spirit.inventario.helper.ConsultaStockOperativoData;
import com.spirit.inventario.session.generated._SolicitudTransferenciaSession;
import com.spirit.poscola.entity.PosColaIf;
import com.spirit.poscola.session.PosColaSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>SolicitudTransferenciaSession</code> session bean, which acts as
 * a facade to the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 * 
 */
@Stateless
public class SolicitudTransferenciaSessionEJB extends
		_SolicitudTransferenciaSession implements
		SolicitudTransferenciaSessionRemote, SolicitudTransferenciaSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB
	private UtilitariosSessionLocal utilitariosSessionLocal;

	@EJB
	private SolicitudTransferenciaDetalleSessionLocal solicitudTransferenciaDetalleSessionLocal;

	@EJB
	private SolicitudTransferenciaMessageLocal solicitudTransferenciaMessageLocal;

	@EJB
	private MovimientoSessionLocal movimientoSessionLocal;

	@EJB
	private ProductoSessionLocal productoSessionLocal;

	@EJB
	private QueryHelperServerLocal queryHelperServerLocal;

	@EJB
	private PosColaSessionLocal posColaSessionLocal;

	@EJB
	private BodegaSessionLocal bodegaSessionLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService
			.getLogger(SolicitudTransferenciaSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 * 
	 * @throws GenericBusinessException
	 **************************************************************************/

	private DecimalFormat formatoSerial4 = new DecimalFormat("0000000000");

	private String getCodigoSerial() {
		String queryString = "Select max(solicitud.id) from SolicitudTransferenciaEJB solicitud";
		Query query = manager.createQuery(queryString);
		Long lastId = null;
		try {
			lastId = (Long) query.getSingleResult();
			return formatoSerial4.format(lastId + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatoSerial4.format(0);
	}

	private String generarCodigo(String ingresado) {
		if (ingresado == null)
			ingresado = getCodigoSerial() + "-" + "STR";
		return ingresado;
	}

	public void generarSolicitudTransferencia(
			List<ConsultaStockOperativoData> consultaStockOperativoDataList,Long usuarioId)
			throws GenericBusinessException {
		ConsultaStockOperativoData consultaStockOperativoTMP = consultaStockOperativoDataList
		.get(0);
		SolicitudTransferenciaEJB solicitudTransferenciaEJB = new SolicitudTransferenciaEJB();

		BodegaIf bodegaIf = (BodegaIf) ((ArrayList) bodegaSessionLocal
				.findBodegaByCodigo("MATRIZ")).get(0);
		
		if (consultaStockOperativoTMP
				.getBodegaId().equals(bodegaIf.getId())) {
			System.out.println("A la bodega matriz no se puede hacer ST");
			return;
		}

		solicitudTransferenciaEJB.setBodegaDesdeId(bodegaIf.getId());
		solicitudTransferenciaEJB.setBodegaHaciaId(consultaStockOperativoTMP
				.getBodegaId());
		solicitudTransferenciaEJB.setEstado("P");
		solicitudTransferenciaEJB
				.setObservacion("GENERADA A PARTIR DE CONSULTA DE STOCK OPERATIVO");
		solicitudTransferenciaEJB.setUsuarioId(usuarioId);
		List<SolicitudTransferenciaDetalleIf> listaSolicitudTransferenciaDetalle = new ArrayList<SolicitudTransferenciaDetalleIf>();
		for (ConsultaStockOperativoData consultaStockOperativoData : consultaStockOperativoDataList) {
			SolicitudTransferenciaDetalleEJB solicitudTransferenciaDetalleEJB = new SolicitudTransferenciaDetalleEJB();
			solicitudTransferenciaDetalleEJB
					.setCantidad(consultaStockOperativoData.getCantidadSolicitada());
			
			solicitudTransferenciaDetalleEJB
					.setProductoId(consultaStockOperativoData.getProductoId());
			solicitudTransferenciaDetalleEJB.setLoteId(consultaStockOperativoData.getLoteId());
			listaSolicitudTransferenciaDetalle
					.add(solicitudTransferenciaDetalleEJB);
		}
		procesarSolicitudTransferencia(solicitudTransferenciaEJB,
				listaSolicitudTransferenciaDetalle);
	}

	public void procesarSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf,
			List<? super SolicitudTransferenciaDetalleIf> listaSolicitudTransferenciaDetalle)
			throws GenericBusinessException {

		solicitudTransferenciaIf.setFechaIngreso(utilitariosSessionLocal
				.getServerDateTimeStamp());
		solicitudTransferenciaIf
				.setCodigo(generarCodigo(solicitudTransferenciaIf.getCodigo()));
		solicitudTransferenciaIf = addSolicitudTransferencia(solicitudTransferenciaIf);

		List<SolicitudTransferenciaDetalleIf> listaDetalle = (List<SolicitudTransferenciaDetalleIf>) listaSolicitudTransferenciaDetalle;
		for (SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf : listaDetalle) {
			solicitudTransferenciaDetalleIf
					.setSolicitudTransferenciaId(solicitudTransferenciaIf
							.getPrimaryKey());
			solicitudTransferenciaDetalleSessionLocal
					.addSolicitudTransferenciaDetalle(solicitudTransferenciaDetalleIf);
		}

		if (solicitudTransferenciaIf.getEstado().equalsIgnoreCase("E")) {
			solicitudTransferenciaIf.setEstado("X");// ESTADO INTERMEDIO
			autorizarSolicitudTransferencia(solicitudTransferenciaIf);
		}
	}

	public void eliminarSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf)
			throws GenericBusinessException {
		if (solicitudTransferenciaIf.getEstado().equalsIgnoreCase("P")) {
			List<SolicitudTransferenciaDetalleIf> listaDetalle = (List) solicitudTransferenciaDetalleSessionLocal
					.findSolicitudTransferenciaDetalleBySolicitudTransferenciaId(solicitudTransferenciaIf
							.getId());
			for (SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf : listaDetalle) {
				solicitudTransferenciaDetalleSessionLocal
						.deleteSolicitudTransferenciaDetalle(solicitudTransferenciaDetalleIf
								.getId());
			}
			deleteSolicitudTransferencia(solicitudTransferenciaIf.getId());
		}
	}

	public void autorizarSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf)
			throws GenericBusinessException {
		if (!solicitudTransferenciaIf.getEstado().equalsIgnoreCase("E")) {
			solicitudTransferenciaIf.setFechaDocumento(utilitariosSessionLocal
					.getServerDateTimeStamp());
			solicitudTransferenciaIf.setEstado("E");
			saveSolicitudTransferencia(solicitudTransferenciaIf);
			PosColaIf YO = posColaSessionLocal.obtenerInfoColaYO();
			if (!YO.getTipoServer().equalsIgnoreCase("P")) {
				enviarMensajeSolicitudTransferencia(solicitudTransferenciaIf);
			}
		}
	}

	private void generarTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf, UsuarioIf usuario)
			throws GenericBusinessException {
		MovimientoEJB movimientoEJB = new MovimientoEJB();
		movimientoEJB.setBodegaId(solicitudTransferenciaIf.getBodegaDesdeId());
		movimientoEJB.setBodegarefId(solicitudTransferenciaIf
				.getBodegaHaciaId());
		movimientoEJB.setFechaCreacion(utilitariosSessionLocal
				.getServerDateTimeStamp());
		movimientoEJB.setEstado("A");
		movimientoEJB.setObservacion(solicitudTransferenciaIf.getObservacion());
		movimientoEJB.setTipodocumentoId(queryHelperServerLocal
				.getTipoDocumento("ETR").getId());
		movimientoEJB.setUsuarioId(usuario.getId());
		List<MovimientoDetalleIf> listaMovimientoDetalle = new ArrayList<MovimientoDetalleIf>();
		List<SolicitudTransferenciaDetalleIf> lista = (List<SolicitudTransferenciaDetalleIf>) solicitudTransferenciaDetalleSessionLocal
				.findSolicitudTransferenciaDetalleById(solicitudTransferenciaIf
						.getId());
		BigDecimal costoAcumulado = BigDecimal.ZERO;
		BigDecimal precioAcumulado = BigDecimal.ZERO;

		for (SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf : lista) {
			MovimientoDetalleEJB movimientoDetalleEJB = new MovimientoDetalleEJB();
			movimientoDetalleEJB.setCantidad(solicitudTransferenciaDetalleIf
					.getCantidad());
			movimientoDetalleEJB.setLoteId(solicitudTransferenciaDetalleIf
					.getLoteId());
			ProductoIf productoIf = productoSessionLocal
					.getProducto(solicitudTransferenciaDetalleIf
							.getProductoId());
			// productoIf.getCosto();
			// costoAcumulado=costoAcumulado.add(augend);
			// precioAcumulado=precioAcumulado.add(augend);
			// movimientoDetalleEJB.setCosto(costo)
			movimientoDetalleEJB.setDocumentoId(queryHelperServerLocal
					.getDocumento("ETRF").getId());
			// movimientoDetalleEJB.setPrecio(precio)
		}
		movimientoEJB.setCosto(costoAcumulado);
		movimientoEJB.setPrecio(precioAcumulado);
		movimientoSessionLocal.procesarMovimiento((MovimientoIf) movimientoEJB,
				listaMovimientoDetalle, usuario);
	}

	private void enviarMensajeSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf)
			throws GenericBusinessException {
		solicitudTransferenciaMessageLocal.setCabecera(
				solicitudTransferenciaIf, "solicitudTransferenciaId");
		List<SolicitudTransferenciaDetalleIf> lista = (List<SolicitudTransferenciaDetalleIf>) solicitudTransferenciaDetalleSessionLocal
				.findSolicitudTransferenciaDetalleBySolicitudTransferenciaId(solicitudTransferenciaIf
						.getId());

		for (SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf : lista) {
			solicitudTransferenciaMessageLocal
					.addDetail(solicitudTransferenciaDetalleIf);
		}
		try {
			solicitudTransferenciaMessageLocal.sendToPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection getSolicitudTransferenciaList(int startIndex,
			int endIndex, Map aMap) throws GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from SolicitudTransferenciaEJB " + objectName
				+ " where " + where;

		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
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

	public int getSolicitudTransferenciaListSize(Map aMap)
			throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(*) from SolicitudTransferenciaEJB "
				+ objectName + " where " + where;

		Query countQuery = manager.createQuery(queryString);
		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			countQuery.setParameter(propertyKey, property);
		}

		List countQueryResult = countQuery.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}

}
