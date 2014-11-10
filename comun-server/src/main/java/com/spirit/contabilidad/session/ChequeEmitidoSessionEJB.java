package com.spirit.contabilidad.session;

import java.sql.Date;
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

import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.client.SpiritConstants;
import com.spirit.contabilidad.entity.ChequeEmitidoEJB;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.entity.ChequeTransaccionEJB;
import com.spirit.contabilidad.entity.ChequeTransaccionIf;
import com.spirit.contabilidad.handler.ChequeDatos;
import com.spirit.contabilidad.handler.EstadoChequeEmitido;
import com.spirit.contabilidad.handler.OrigenCheque;
import com.spirit.contabilidad.session.generated._ChequeEmitidoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.session.CuentaBancariaSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.util.DeepCopy;

/**
 * The <code>ChequeEmitidoSession</code> session bean, which acts as a facade
 * to the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 * 
 */
@Stateless
public class ChequeEmitidoSessionEJB extends _ChequeEmitidoSession implements ChequeEmitidoSessionRemote,
		ChequeEmitidoSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB private CuentaBancariaSessionLocal cuentaBancariaLocal;
	@EJB private ChequeTransaccionSessionLocal chequeTransaccionLocal;
	@EJB private UtilitariosSessionLocal utilitiesSessionLocal;

	@Resource
	private SessionContext ctx;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService
			.getLogger(ChequeEmitidoSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	public void procesarChequeEmitido(ChequeDatos chequeDatos)
			throws GenericBusinessException {
		try {
			ChequeEmitidoIf chequeEmitidoIf = chequeDatos.getChequeEmitido();
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("cuentaBancariaId", chequeEmitidoIf.getCuentaBancariaId());
			mapa.put("numero", chequeEmitidoIf.getNumero());
			Collection<ChequeEmitidoIf> cheques = findChequeEmitidoByQuery(mapa);
			if (cheques == null || cheques.size() == 0) {
				ChequeEmitidoIf chequeEmitidoGuardadoIf = addChequeEmitido(chequeEmitidoIf);
				for (Long transaccionId : chequeDatos.getTransaccionesIds()) {
					ChequeTransaccionIf chequeTransaccion = registrarChequeTransaccion(
							chequeEmitidoGuardadoIf.getId(), transaccionId,
							chequeDatos.getOrigen());
					chequeTransaccionLocal
							.addChequeTransaccion(chequeTransaccion);
				}
			} else {
				CuentaBancariaIf cuentaBancaria = cuentaBancariaLocal
						.getCuentaBancaria(chequeEmitidoIf
								.getCuentaBancariaId());
				throw new GenericBusinessException("Cheque No. \""
						+ chequeEmitidoIf.getNumero() + "\" de la Cuenta: "
						+ cuentaBancaria.getCuenta() + " ya existe !!");
			}
		} catch (GenericBusinessException e) {
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			throw new GenericBusinessException(
					"Error al procesar cheques emitidos !!");
		}
	}

	public void actualizarChequeEmitido(Long transaccionId,
			Long tipoDocumentoId, Map<String, ChequeDatos> mapachequesEmitidos,
			String origen,Map<String,ChequeDatos> mapaChequesEliminados) throws GenericBusinessException {
		try {
			
			//Eliminacion de cheques
			if ( mapaChequesEliminados != null ){
				for ( String numeroCheque : mapaChequesEliminados.keySet() ){
					ChequeDatos cdEliminado = mapaChequesEliminados.get(numeroCheque);
					ChequeEmitidoIf ceTmpEliminado = cdEliminado.getChequeEmitido();
					ChequeEmitidoIf chequeExistenteEliminado = buscarChequeCompletoEmitido(ceTmpEliminado);
					
					//Comparo los cheques a eliminar con los cheques nuevos a crear para ver si tienen
					//el mismo id y numero, si es asi, no se hace la eliminacion
					ChequeDatos cd = mapachequesEmitidos.get(numeroCheque);
					if ( cd != null ){
						ChequeEmitidoIf ceTmp = cd.getChequeEmitido();
						ChequeEmitidoIf chequeExistente = buscarChequeCompletoEmitido(ceTmp);
						if ( chequeExistente!=null && 
							 chequeExistenteEliminado.getNumero().equals(chequeExistente.getNumero()) && 
							 chequeExistenteEliminado.getId().equals(chequeExistente.getId()) ){
							continue;
						}
					}
					
					if ( chequeExistenteEliminado != null ){
						
						if ( EstadoChequeEmitido.EMITIDO.getLetra().equals(chequeExistenteEliminado.getEstado()) ){
							Collection<ChequeTransaccionIf> transacciones = chequeTransaccionLocal.findChequeTransaccionByChequeEmitidoId(chequeExistenteEliminado.getId());
							for ( ChequeTransaccionIf ct : transacciones ){
								chequeTransaccionLocal.deleteChequeTransaccion(ct.getId());
							}
							deleteChequeEmitido(chequeExistenteEliminado.getId());
						
						} else {
							throw new GenericBusinessException("Cheque con No. "+
								chequeExistenteEliminado.getNumero()+" no esta en estado "+
								EstadoChequeEmitido.EMITIDO+ " para poder ser eliminado !!");
						}
					}					
				}
			}
			
			for ( String numeroCheque : mapachequesEmitidos.keySet() ){
				ChequeDatos cd = mapachequesEmitidos.get(numeroCheque);
				ChequeEmitidoIf ceTmp = cd.getChequeEmitido();
				
				if (ceTmp.getValor().doubleValue() <= 0D) {
					throw new GenericBusinessException(
						"Valor de cheque número " + ceTmp.getNumero()+ "tiene que se mayor que cero");
				}
				
				Collection<ChequeEmitidoIf> chequesExistentes = buscarChequeEmitido(ceTmp);
				
				if ( chequesExistentes.size() == 1 ){
					ChequeEmitidoIf ce = chequesExistentes.iterator().next();
					if ( EstadoChequeEmitido.EMITIDO.getLetra().equals( ce.getEstado() ) ){
						verificarOrigenCheque(transaccionId, origen, ce);
						ce.setValor(ceTmp.getValor());
						ce.setCuentaBancariaId(ceTmp.getCuentaBancariaId());
						ce.setFechaEmision(ceTmp.getFechaEmision());
					} else {
						verificarOrigenCheque(transaccionId, origen, ce);
					}
					
				} else if (chequesExistentes.size() == 0) {
					procesarChequeEmitido(cd);
				} else if (chequesExistentes.size() > 1) {
					CuentaBancariaIf cuentaBancaria = cuentaBancariaLocal
						.getCuentaBancaria(ceTmp.getCuentaBancariaId());
					throw new GenericBusinessException("Cheque con numero " + ceTmp.getNumero()+
						" de la Cuenta Bancaria "+ cuentaBancaria.getCuenta()+ " tiene esta duplicado !!");
				}				
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al actualizar cheques emitidos !!");
		}

	}

	private void verificarOrigenCheque(
			Long transaccionId, String origen, ChequeEmitidoIf ce)
			throws GenericBusinessException {
		boolean igualReferencia = true;
		Collection<ChequeTransaccionIf> cts = chequeTransaccionLocal
			.findChequeTransaccionByChequeEmitidoId(ce.getId());
		//if ( !ce.getOrigen().equals(origen) )
		//	throw new GenericBusinessException("Cheque con No. "+ce.getNumero()+" ya existe !!");
		for ( ChequeTransaccionIf ct : cts ){
			if ( ct.getTransaccionId().compareTo(transaccionId) != 0 )
				igualReferencia = false;
		}
		if ( !igualReferencia )
			throw new GenericBusinessException("Cheque con No."+ce.getNumero()+
				" pertenece a otra transaccion !!");
	}

	public void anularChequesEmitidos(Long transaccionIdExistente,
			Long tipoDocumentoId, Long transaccionIdNueva)
			throws GenericBusinessException {
		// transaccionIdNueva se lo usa para cuando se anulan asientos creados
		// desde el panel asientos
		// este nuevo id es el id de la tabla log_cartera.
		try {
			Map<String, Object> mapaChequesEmitidosExistentes = new HashMap<String, Object>();
			mapaChequesEmitidosExistentes.put("tipoDocumentoId",tipoDocumentoId);
			mapaChequesEmitidosExistentes.put("transaccionId",transaccionIdExistente);
			Collection<ChequeEmitidoIf> chequesEmitidos = findChequeEmitidoByQuery(mapaChequesEmitidosExistentes);

			for (ChequeEmitidoIf chequeEmitidoIf : chequesEmitidos) {
				EstadoChequeEmitido estadoCheque = getEstadoChequeEmitido(chequeEmitidoIf.getEstado());
				if (estadoCheque == EstadoChequeEmitido.EMITIDO) {
					chequeEmitidoIf.setEstado(getLetraEstadoChequeEmitido(EstadoChequeEmitido.ANULADO));
					if (transaccionIdNueva != null)
						chequeEmitidoIf.setTransaccionId(transaccionIdNueva);
				} else {
					CuentaBancariaIf cuentaBancaria = cuentaBancariaLocal
						.getCuentaBancaria(chequeEmitidoIf.getCuentaBancariaId());
					throw new GenericBusinessException(
						"Cheque No. "+ chequeEmitidoIf.getNumero()+ " de la Cuenta "+
						cuentaBancaria.getCuenta()+ " esta " + estadoCheque + " !!\nNo se puede modificar !!");
				}
			}
		} catch (GenericBusinessException e) {
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			throw new GenericBusinessException(
					"Error al actualizar cheques emitidos !!");
		}

	}

	public void anularChequesEmitidos(String numeroCheque,
			Long cuentaBancariaId, Long transaccionIdNueva)
			throws GenericBusinessException {
		// transaccionIdNueva se lo usa para cuando se anulan asientos creados
		// desde el panel asientos
		// este nuevo id es el id de la tabla log_cartera.
		try {
			Map<String, Object> mapaChequesEmitidosExistentes = new HashMap<String, Object>();
			mapaChequesEmitidosExistentes.put("cuentaBancariaId",
					cuentaBancariaId);
			mapaChequesEmitidosExistentes.put("numero", numeroCheque);
			Collection<ChequeEmitidoIf> chequesEmitidos = findChequeEmitidoByQuery(mapaChequesEmitidosExistentes);
			for (ChequeEmitidoIf chequeEmitidoIf : chequesEmitidos) {
				EstadoChequeEmitido estadoCheque = getEstadoChequeEmitido(chequeEmitidoIf
						.getEstado());
				if (estadoCheque == EstadoChequeEmitido.EMITIDO) {
					chequeEmitidoIf
							.setEstado(getLetraEstadoChequeEmitido(EstadoChequeEmitido.ANULADO));
					if (transaccionIdNueva != null && chequeEmitidoIf.getOrigen().equals("A"))
						chequeEmitidoIf.setTransaccionId(transaccionIdNueva);
				} else if (estadoCheque != EstadoChequeEmitido.ANULADO) {
					CuentaBancariaIf cuentaBancaria = cuentaBancariaLocal
							.getCuentaBancaria(chequeEmitidoIf
									.getCuentaBancariaId());
					throw new GenericBusinessException(
							"Cheque No. "
									+ chequeEmitidoIf.getNumero()
									+ " de la Cuenta "
									+ cuentaBancaria.getCuenta()
									+ " ya ha sido cobrado !!\nNo se puede modificar !!");
				}
			}
		} catch (GenericBusinessException e) {
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			throw new GenericBusinessException(
					"Error al actualizar cheques emitidos !!");
		}

	}

	public Collection<Object[]> getChequesEmitidosDesdeAsientosByQueryByFechaInicioByFechaFin(
			Map<String, Object> mapa, Date fechaInicio, Date fechaFin)
			throws GenericBusinessException {

		String where = QueryBuilder.buildWhere(mapa, "e");
		String queryString = "";
		queryString = "select e,a.numero from ChequeEmitidoEJB e,ChequeTransaccionEJB ct,AsientoEJB a  where "
				+ " e.id = ct.chequeEmitidoId and ct.transaccionId = a.id "
				+ " and ct.origen = 'A' " + " and e.numero <> '0' and" + where;
		queryString += " and ( e.fechaCobro is null or e.fechaCobro > :fechaFin ) ";
		queryString += " and e.fechaEmision <= :fechaFin ";
		queryString += " and e.estado <> :estadoAnulado ";
		queryString += " order by e.id";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaFin", fechaFin);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setParameter("estadoAnulado", EstadoChequeEmitido.ANULADO
				.getLetra());

		List<Object[]> lista = query.getResultList();
		return filtrarLista(lista);

	}

	public Collection<Object[]> getChequesEmitidosDesdeNominaByQueryByFechaInicioByFechaFin(
			Map<String, Object> mapa, Date fechaInicio, Date fechaFin)
			throws GenericBusinessException {

		String where = QueryBuilder.buildWhere(mapa, "e");
		String queryString = "";
		queryString = "select e,a.numero from ChequeEmitidoEJB e,ChequeTransaccionEJB ct,AsientoEJB a ,RolPagoEJB rp, AsientoDetalleEJB ad  "
				+ "where e.id = ct.chequeEmitidoId and ct.transaccionId = rp.id and rp.id = a.transaccionId "
				+ " and a.id = ad.asientoId and ad.referencia = e.numero"
				+ " and ct.origen = 'N' " + " and e.numero <> '0' and" + where;
		queryString += " and ( e.fechaCobro is null or e.fechaCobro > :fechaFin ) ";
		queryString += " and e.fechaEmision <= :fechaFin ";
		queryString += " and e.estado <> :estadoAnulado ";
		queryString += " order by e.id";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaFin", fechaFin);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setParameter("estadoAnulado", EstadoChequeEmitido.ANULADO
				.getLetra());

		List<Object[]> lista = query.getResultList();
		return filtrarLista(lista);

	}

	public Collection<Object[]> getChequesEmitidosDesdeCarteraByQueryByFechaInicioByFechaFin(
			Map<String, Object> mapa, Date fechaInicio, Date fechaFin) {

		String where = QueryBuilder.buildWhere(mapa, "e");
		String queryString = "select e,c.codigo from ChequeEmitidoEJB e,ChequeTransaccionEJB ct,CarteraEJB c  where "
				+ " e.id = ct.chequeEmitidoId and ct.transaccionId = c.id "
				+ " and ( ct.origen = 'C' )"
				+ " and e.numero <> '0' and"
				+ where;
		// if ( fechaInicio != null ){
		// queryString += " and e.fechaEmision >= :fechaInicio ";
		// queryString += " and ( e.fechaCobro is null or e.fechaCobro >
		// :fechaInicio ) ";
		queryString += " and ( e.fechaCobro is null or e.fechaCobro > :fechaFin ) ";
		// }
		// if ( fechaInicio != null )
		// queryString += " and e.fechaEmision >= :fechaInicio ";
		queryString += " and e.fechaEmision <= :fechaFin ";
		queryString += " and e.estado <> :estadoAnulado ";
		queryString += " order by e.id";
		Query query = manager.createQuery(queryString);
		// if ( fechaInicio != null )
		// query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setParameter("estadoAnulado", EstadoChequeEmitido.ANULADO
				.getLetra());
		List<Object[]> lista = query.getResultList();
		return filtrarLista(lista);
	}

	public Collection<ChequeEmitidoIf> getChequesEmitidosSinCarteraSinAsientoByQueryByFechaInicioByFechaFin(
			Map<String, Object> mapa, Date fechaInicio, Date fechaFin) {

		String where = QueryBuilder.buildWhere(mapa, "e");
		String queryString = "select e from ChequeEmitidoEJB e where "
				+ " e.numero <> '0' and" + where;
		queryString += " and ( e.fechaCobro is null or e.fechaCobro > :fechaFin ) ";
		// if ( fechaInicio != null )
		// queryString += " and e.fechaEmision >= :fechaInicio ";
		queryString += " and e.fechaEmision <= :fechaFin ";
		queryString += " and e.estado <> :estadoAnulado ";
		queryString += " and e.tipoDocumentoId is null and e.transaccionId is null and e.origen is null ";
		queryString += " order by e.id";
		Query query = manager.createQuery(queryString);
		// if ( fechaInicio != null )
		// query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setParameter("estadoAnulado", EstadoChequeEmitido.ANULADO
				.getLetra());
		List<ChequeEmitidoIf> lista = query.getResultList();
		return lista;
	}

	private List<Object[]> filtrarLista(List<Object[]> lista) {
		Long idAnterior = null;
		Object[] oAnterior = null;
		for (Iterator<Object[]> itLista = lista.iterator(); itLista.hasNext();) {
			Object[] o = itLista.next();
			if (idAnterior != null
					&& idAnterior.equals(((ChequeEmitidoIf) o[0]).getId())) {
				oAnterior[1] = oAnterior[1] + "," + ((String) o[1]);
				itLista.remove();
			}
			oAnterior = o;
			idAnterior = ((ChequeEmitidoIf) o[0]).getId();
		}
		return lista;
	}

	public Collection<ChequeEmitidoIf> getChequesEmitidosQueryByFechaInicioByFechaFin(
			Map<String, Object> mapa, Date fechaInicio, Date fechaFin) {

		String where = QueryBuilder.buildWhere(mapa, "e");
		String queryString = "select e,c.codigo from ChequeEmitidoEJB ew here "
				+ where;
		if (fechaInicio != null) {
			// queryString += " and e.fechaEmision >= :fechaInicio ";
			queryString += " and ( e.fechaCobro is null or e.fechaCobro > :fechaInicio ) ";
		}
		queryString += " and e.fechaEmision <= :fechaFin ";
		queryString += " order by e.id";
		Query query = manager.createQuery(queryString);
		if (fechaInicio != null)
			query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();

	}

	public Collection<ChequeEmitidoIf> getChequesAnuladosByQueryByFechaInicioByFechaFin(
			Map<String, Object> mapa, Date fechaInicio,Date fechaFin)
			throws GenericBusinessException {
		
		String where = QueryBuilder.buildWhere(mapa, "e");
		String queryString = "";
		queryString = "select e from ChequeEmitidoEJB e where "
				+ " ( e.origen = 'A' or e.origen = 'N' or e.origen = 'C' )"
				+ " and e.numero <> '0' and" + where
				+ " and e.fechaEmision >= :fechaInicio "
				+ " and e.fechaEmision <= :fechaFin " + " order by e.id";
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		return query.getResultList();
		
	}

	public String getLetraEstadoChequeEmitido(EstadoChequeEmitido estado)
			throws GenericBusinessException {
		if (estado == EstadoChequeEmitido.EMITIDO
				|| estado == EstadoChequeEmitido.COBRADO
				|| estado == EstadoChequeEmitido.ANULADO)
			return estado.toString().substring(0, 1);
		throw new GenericBusinessException("Estado de Cheque no considerado !!");
	}

	public EstadoChequeEmitido getEstadoChequeEmitido(String letra)
			throws GenericBusinessException {
		if (letra
				.equals(getLetraEstadoChequeEmitido(EstadoChequeEmitido.EMITIDO)))
			return EstadoChequeEmitido.EMITIDO;
		else if (letra
				.equals(getLetraEstadoChequeEmitido(EstadoChequeEmitido.COBRADO)))
			return EstadoChequeEmitido.COBRADO;
		else if (letra
				.equals(getLetraEstadoChequeEmitido(EstadoChequeEmitido.ANULADO)))
			return EstadoChequeEmitido.ANULADO;
		throw new GenericBusinessException(
				"Letra de Estado de Cheque no considerada !!");
	}

	public Collection findChequeEmitidoByQueryByFechaInicioAndByFechaFin(
			Map aMap, Date fechaInicio, Date fechaFin)
			throws GenericBusinessException {
		String objectName = "ch";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from ChequeEmitidoEJB " + objectName + " where "
				+ where
				+ " and ch.fechaEmision between :fechaInicio and :fechaFin";
		String orderByPart = "";
		orderByPart += " order by ch.numero";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		return query.getResultList();
	}

	public ChequeEmitidoEJB registrarChequeEmitido(
			ChequeEmitidoIf chequeEmitidoIf) {
		ChequeEmitidoEJB chequeEmitido = new ChequeEmitidoEJB();
		chequeEmitido.setBeneficiario(chequeEmitidoIf.getBeneficiario());
		chequeEmitido
				.setCuentaBancariaId(chequeEmitidoIf.getCuentaBancariaId());
		chequeEmitido.setDetalle(chequeEmitidoIf.getDetalle());
		chequeEmitido.setEstado(chequeEmitidoIf.getEstado());
		chequeEmitido.setFechaEmision(chequeEmitidoIf.getFechaEmision());
		chequeEmitido.setId(chequeEmitidoIf.getId());
		chequeEmitido.setNumero(chequeEmitidoIf.getNumero());
		chequeEmitido.setTipoDocumentoId(chequeEmitidoIf.getTipoDocumentoId());
		chequeEmitido.setTransaccionId(chequeEmitidoIf.getTransaccionId());
		chequeEmitido.setValor(chequeEmitidoIf.getValor());
		chequeEmitido.setFechaCobro(chequeEmitidoIf.getFechaCobro());
		chequeEmitido.setOrigen(chequeEmitidoIf.getOrigen());
		return chequeEmitido;
	}

	public ChequeTransaccionEJB registrarChequeTransaccion(
			Long chequeEmitidoId, Long transaccionId, String estado)
			throws GenericBusinessException {
		ChequeTransaccionEJB chequeTransaccion = new ChequeTransaccionEJB();
		if (chequeEmitidoId == null)
			throw new GenericBusinessException(
					"Id de Cheque para registro de Transaccion de cheque no existe !!");
		chequeTransaccion.setChequeEmitidoId(chequeEmitidoId);
		if (transaccionId == null)
			throw new GenericBusinessException(
					"Id de Transacción para registro de Transaccion de cheque no existe !!");
		chequeTransaccion.setTransaccionId(transaccionId);
		if (estado == null || "".equals(estado))
			throw new GenericBusinessException(
					"Estado para registro de Transaccion de cheque no existe !!");
		chequeTransaccion.setOrigen(estado);
		return chequeTransaccion;
	}

	public void cambiarEstadoCheques(
			List<ChequeEmitidoIf> chequesSeleccionadosColeccion)
			throws GenericBusinessException {
		for (ChequeEmitidoIf chequeEmitidoIf : chequesSeleccionadosColeccion) {
			ChequeEmitidoEJB chequeEmitido = registrarChequeEmitido(chequeEmitidoIf);
			manager.merge(chequeEmitido);
		}
	}
	
	private ChequeEmitidoIf buscarChequeCompletoEmitido(ChequeEmitidoIf chequeEmitido) throws GenericBusinessException{
		try{
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("numero", chequeEmitido.getNumero());
			mapa.put("cuentaBancariaId", chequeEmitido.getCuentaBancariaId());
			mapa.put("origen", chequeEmitido.getOrigen());
			Collection<ChequeEmitidoIf> cheques = findChequeEmitidoByQuery(mapa);
			if ( cheques.size() == 1 )
				return cheques.iterator().next();
			else if (cheques.size() > 1)
				throw new GenericBusinessException("Existe mas de un cheque con No "+chequeEmitido.getNumero());
			return null;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error búsqueda de Cheque Emitido por Número de cheque !!");
		}
		
	}
	
	private Collection<ChequeEmitidoIf> buscarChequeEmitido(ChequeEmitidoIf chequeEmitido) throws GenericBusinessException{
		try{
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("numero", chequeEmitido.getNumero());
			mapa.put("cuentaBancariaId", chequeEmitido.getCuentaBancariaId());
			return findChequeEmitidoByQuery(mapa);
			
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error búsqueda de Cheque Emitido por Número de cheque !!");
		}
		
	}
	
	private Collection<ChequeEmitidoIf> buscarChequeEmitidoByNumero(String origen, String numero) throws GenericBusinessException{
		/*String numero = (String) mapa.get("numero");
		Long tipoDocumentoId = (Long) mapa.get("tipoDocumentoId");
		Long transaccionId = (Long) mapa.get("transaccionId");
		Long cuentaBancariaId = (Long) mapa.get("cuentaBancariaId");*/
		try{
			String queryString = "select distinct ce from ChequeEmitidoEJB ce ,ChequeTransaccionEJB ct, AsientoEJB a, AsientoDetalleEJB ad " +
					" where ce.id = ct.chequeEmitidoId " +
					" and a.id = ad.asientoId " +
					" and ce.numero = :numero ";
			if ( OrigenCheque.ASIENTO.getLetra().equals( origen ) ||
				 OrigenCheque.NOMINA.getLetra().equals( origen )	){
				queryString += " and ce.numero = ad.referencia " ;
			}
			
			if ( !OrigenCheque.ASIENTO.getLetra().equals( origen ) ){
				queryString += " and ct.transaccionId = a.transaccionId";
			}
			
			Query query = manager.createQuery(queryString);
			query.setParameter("numero", numero);
			
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error búsqueda de Cheque Emitido por Número de cheque !!");
		}
		
	}

	@SuppressWarnings("unchecked")
	public void processIssuedCheck(WalletData walletData, WalletDetailData walletDetailData, boolean update) throws GenericBusinessException {
		try {
			boolean processIssuedCheck = false;
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("cuentaBancariaId", walletDetailData.getCheckAccount().getId());
			queryMap.put("numero", walletDetailData.getCheckNumber());
			queryMap.put("estado", SpiritConstants.getCanceledCheck().substring(0,1));
			Iterator<ChequeEmitidoIf> issuedChecksIterator = findChequeEmitidoByQuery(queryMap).iterator();
			if (!issuedChecksIterator.hasNext()) {
				//issuedCheck = (ChequeEmitidoEJB) issuedChecksIterator.next();
				//if (issuedCheck.getTipoDocumentoId() != SpiritConstants.getNullValue() && issuedCheck.getTipoDocumentoId().compareTo(walletData.getDocumentType().getId()) == 0 && issuedCheck.getTransaccionId().compareTo(walletData.getWalletId()) == 0 && update)
					processIssuedCheck = true;
			//} else
				//processIssuedCheck = true;
			
			if (processIssuedCheck) {
				ChequeEmitidoEJB issuedCheck = new ChequeEmitidoEJB();
				issuedCheck.setBeneficiario(walletData.getBusinessOperator().getRazonSocial());
				issuedCheck.setDetalle(walletData.getComment());
				issuedCheck.setCuentaBancariaId(walletDetailData.getCheckAccount().getId());
				//if (issuedCheck.getEstado() == SpiritConstants.getNullValue() || !issuedCheck.getEstado().equals(SpiritConstants.getCanceledCheck().substring(0,1)))
				issuedCheck.setEstado(SpiritConstants.getIssuedCheck().substring(0,1));
				issuedCheck.setFechaEmision(utilitiesSessionLocal.fromUtilDateToSqlDate(walletData.getEmissionDate()));
				issuedCheck.setNumero(walletDetailData.getCheckNumber());
				issuedCheck.setOrigen(SpiritConstants.getIssuedCheckWalletOrigin().substring(0, 1));
				issuedCheck.setTipoDocumentoId(walletData.getDocumentType().getId());
				issuedCheck.setTransaccionId(walletData.getWalletId());
				issuedCheck.setValor(walletDetailData.getValue());
				//if (!update)
				manager.persist(issuedCheck);
				//else
					//manager.merge(issuedCheck);
				ChequeTransaccionIf transactionCheck = registrarChequeTransaccion(issuedCheck.getId(), walletData.getWalletId(), issuedCheck.getOrigen());
				chequeTransaccionLocal.addChequeTransaccion(transactionCheck);
			}/* else {
				throw new GenericBusinessException("No se han procesado los datos de uno o más cheques emitidos\n" 
						+ "Cheque # " + walletDetailData.getCheckNumber() + "; " + walletDetailData.getCheckBank().getNombre() + " Cta. # " + walletDetailData.getCheckAccount().getCuenta() + " fue emitido anteriormente\n"
						+ "Verifique los datos del cheque en cuestión");*/
			}
		} catch (GenericBusinessException e) {
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			throw new GenericBusinessException("Se ha producido un error general al procesar los datos de uno o más cheques emitidos");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteIssuedCheck(WalletData walletData, WalletDetailData walletDetailData, boolean deleteTransactionCheck) throws GenericBusinessException {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("cuentaBancariaId", walletDetailData.getCheckAccount().getId());
		queryMap.put("numero", walletDetailData.getCheckNumber());
		queryMap.put("transaccionId", walletData.getWalletId());
		queryMap.put("origen", SpiritConstants.getIssuedCheckWalletOrigin().substring(0,1));
		Iterator<ChequeEmitidoIf> issuedCheckIterator = findChequeEmitidoByQuery(queryMap).iterator();
		if (issuedCheckIterator.hasNext()) {
			ChequeEmitidoIf issuedCheck = issuedCheckIterator.next();
			if (deleteTransactionCheck) {
				Iterator<ChequeTransaccionIf> transactionCheckIterator = chequeTransaccionLocal.findChequeTransaccionByChequeEmitidoId(issuedCheck.getId()).iterator();
				while (transactionCheckIterator.hasNext()) {
					ChequeTransaccionIf transactionCheck = transactionCheckIterator.next();
					manager.remove(transactionCheck);
				}
			}
			manager.remove(issuedCheck);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteIssuedCheck(ChequeEmitidoIf issuedCheck, boolean deleteTransactionCheck) throws GenericBusinessException {
		if (deleteTransactionCheck) {
			Iterator<ChequeTransaccionIf> transactionCheckIterator = chequeTransaccionLocal.findChequeTransaccionByChequeEmitidoId(issuedCheck.getId()).iterator();
			while (transactionCheckIterator.hasNext()) {
				ChequeTransaccionIf transactionCheck = transactionCheckIterator.next();
				manager.remove(transactionCheck);
			}
		}
		manager.remove(issuedCheck);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object[]> findChequesEmitidosConciliacionBancaria(Long cuentaBancariaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException {
		//select distinct a.NUMERO, ce.FECHA_EMISION, ce.BENEFICIARIO, ce.NUMERO, ce.VALOR from CHEQUE_EMITIDO ce, CHEQUE_TRANSACCION ct, ASIENTO a where ce.ID = ct.CHEQUE_EMITIDO_ID and ce.ORIGEN = 'A' and ct.TRANSACCION_ID = a.ID and ct.ORIGEN = 'A' and ce.CUENTA_BANCARIA_ID = 40 order by ce.NUMERO
		String select = "select distinct a.numero, ce.fechaEmision, ce.beneficiario, ce.numero, ce.valor, a.tipoDocumentoId, a.transaccionId";
		String from = "from ChequeEmitidoEJB ce, ChequeTransaccionEJB ct, AsientoEJB a";
		//String where = "where ce.id = ct.chequeEmitidoId and ce.origen = 'A' and ct.transaccionId = a.id and ct.origen = 'A' and ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision >= :fechaInicio and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro > :fechaFin)";
		String where = "where ce.id = ct.chequeEmitidoId and ce.origen = 'A' and ct.transaccionId = a.id and ct.origen = 'A' and ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro > :fechaFin) and ce.numero <> '0'";
		String groupBy = "group by a.numero, ce.numero";
		String orderBy = "order by ce.numero, a.numero";
		String queryString = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
		Query query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("cuentaBancariaId", cuentaBancariaId);
		Collection<Object[]> chequesEmitidosConciliacionBancaria = (Collection<Object[]>) DeepCopy.copy(query.getResultList());
		//select distinct c.CODIGO, ce.FECHA_EMISION, ce.BENEFICIARIO, ce.NUMERO, ce.VALOR from CHEQUE_EMITIDO ce, CHEQUE_TRANSACCION ct, CARTERA c where ce.ID = ct.CHEQUE_EMITIDO_ID and ce.ORIGEN = 'C' and ct.TRANSACCION_ID = c.ID and ct.ORIGEN = 'C' and ce.CUENTA_BANCARIA_ID = 41 order by ce.NUMERO
		select = "select distinct c.codigo, ce.fechaEmision, ce.beneficiario, ce.numero, c.valor, c.tipodocumentoId, ct.transaccionId";
		from = "from ChequeEmitidoEJB ce, ChequeTransaccionEJB ct, CarteraEJB c";
		//where = "where ce.id = ct.chequeEmitidoId and ce.origen = 'C' and ct.transaccionId = c.id and ct.origen = 'C' and ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision >= :fechaInicio and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro > :fechaFin)";
		where = "where ce.id = ct.chequeEmitidoId and ce.origen = 'C' and ct.transaccionId = c.id and ct.origen = 'C' and ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro > :fechaFin) and ce.numero <> '0'";
		groupBy = "group by c.codigo, ce.numero";
		orderBy = "order by ce.numero, c.codigo";
		queryString = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
		query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("cuentaBancariaId", cuentaBancariaId);
		chequesEmitidosConciliacionBancaria.addAll((Collection<Object[]>) DeepCopy.copy(query.getResultList()));		
		//select distinct a.NUMERO, ce.FECHA_EMISION, ce.BENEFICIARIO, ce.NUMERO, ce.VALOR from CHEQUE_EMITIDO ce, CHEQUE_TRANSACCION ct, ROL_PAGO rp, ASIENTO a, ASIENTO_DETALLE ad where ce.ID = ct.CHEQUE_EMITIDO_ID and ce.ORIGEN = 'N' and ct.TRANSACCION_ID = rp.ID and rp.ID = a.TRANSACCION_ID and a.ID = ad.ASIENTO_ID and ad.REFERENCIA = ce.NUMERO and ct.ORIGEN = 'N' and ce.CUENTA_BANCARIA_ID = 41 order by ce.NUMERO
		select = "select distinct a.numero, ce.fechaEmision, ce.beneficiario, ce.numero, ce.valor, a.tipoDocumentoId, a.transaccionId";
		from = "from ChequeEmitidoEJB ce, ChequeTransaccionEJB ct, RolPagoEJB rp, AsientoEJB a, AsientoDetalleEJB ad";
		//where = "where ce.id = ct.chequeEmitidoId and ce.origen = 'N' and ct.transaccionId = rp.id and rp.id = a.transaccionId and a.id = ad.asientoId and ad.referencia = ce.numero and ct.origen = 'N' and ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision >= :fechaInicio and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro > :fechaFin)";
		where = "where ce.id = ct.chequeEmitidoId and ce.origen = 'N' and ct.transaccionId = rp.id and rp.id = a.transaccionId and a.id = ad.asientoId and ad.referencia = ce.numero and ct.origen = 'N' and ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro > :fechaFin) and ce.numero <> '0'";
		groupBy = "group by a.numero, ce.numero";
		orderBy = "order by ce.numero, a.numero";
		queryString = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
		query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("cuentaBancariaId", cuentaBancariaId);
		chequesEmitidosConciliacionBancaria.addAll((Collection<Object[]>) DeepCopy.copy(query.getResultList()));
		//select distinct a.NUMERO, ce.FECHA_EMISION, ce.BENEFICIARIO, ce.NUMERO, ce.VALOR from CHEQUE_EMITIDO ce, CHEQUE_TRANSACCION ct, ROL_PAGO rp, ASIENTO a, ASIENTO_DETALLE ad where ce.ID = ct.CHEQUE_EMITIDO_ID and ce.ORIGEN = 'N' and ct.TRANSACCION_ID = rp.ID and rp.ID = a.TRANSACCION_ID and a.ID = ad.ASIENTO_ID and ad.REFERENCIA = ce.NUMERO and ct.ORIGEN = 'N' and ce.CUENTA_BANCARIA_ID = 41 order by ce.NUMERO
		select = "select distinct ce.origen, ce.fechaEmision, ce.beneficiario, ce.numero, ce.valor, ce.tipoDocumentoId, ce.transaccionId";
		from = "from ChequeEmitidoEJB ce";
		//where = "where ce.id = ct.chequeEmitidoId and ce.origen = 'N' and ct.transaccionId = rp.id and rp.id = a.transaccionId and a.id = ad.asientoId and ad.referencia = ce.numero and ct.origen = 'N' and ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision >= :fechaInicio and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro > :fechaFin)";
		where = "where ce.cuentaBancariaId = :cuentaBancariaId and ce.fechaEmision <= :fechaFin and ce.estado <> 'A' and (ce.fechaCobro is null or ce.fechaCobro < :fechaFin) and ce.numero <> '0'";
		groupBy = "group by ce.numero";
		orderBy = "order by ce.numero";
		queryString = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
		query = manager.createQuery(queryString);
		//query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", new java.sql.Date(2008-1900, 7, 31));
		query.setParameter("cuentaBancariaId", cuentaBancariaId);
		chequesEmitidosConciliacionBancaria.addAll((Collection<Object[]>) DeepCopy.copy(query.getResultList()));
		return chequesEmitidosConciliacionBancaria;
	}
}
