package com.spirit.nomina.session;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.TipoRolFormaPago;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.nomina.handler.NominaUtilesLocal;
import com.spirit.nomina.session.generated._RolPagoDetalleSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.util.FindQuery;
import com.spirit.util.Utilitarios;

/**
 * The <code>RolPagoDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:21 $
 *
 */
@Stateless
public class RolPagoDetalleSessionEJB extends _RolPagoDetalleSession implements RolPagoDetalleSessionRemote,RolPagoDetalleSessionLocal  {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	private @EJB NominaUtilesLocal utilesLocal;
	
	private @EJB UtilitariosSessionLocal utilitariosLocal;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(RolPagoDetalleSessionEJB.class);

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findRolPagoDetalleByRubroCodigoEmpleadoId(Long contratoId,String codigoRubro) throws GenericBusinessException {
		String objectName = "cd";
		String queryString = 
			"select distinct rd, r from RolPagoEJB r,RolPagoDetalleEJB rd,ContratoEJB c, RubroEJB ru " +
			"where r.id = rd.rolpagoId and rd.rubroId=ru.id and rd.contratoId=c.id " +
			" and c.id = :contratoId and ru.codigo= :codigoRubro " +
			"order by r.anio,r.mes asc";						
		//String queryString = "select distinct cd, c from ClienteOficinaEJB co, CarteraEJB c, CarteraDetalleEJB " + objectName + ", DocumentoEJB d, TipoDocumentoEJB td where co.id = " + idCliente + " and co.id = c.clienteoficinaId and c.tipo = '" + tipoCartera + "' and c.id = cd.carteraId and cd.saldo > 0.0 and cd.documentoId = d.id and d.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + " and td.signocartera = '" + signoCartera + "' and d.tipodocumentoId = " + idTipoDocumento + " order by c.preimpreso asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("contratoId", contratoId);
		query.setParameter("codigoRubro", codigoRubro);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findRolPagoDetalleByRolpagoIdByContratoId(Long rolpagoId,Long contratoId)
	throws com.spirit.exception.GenericBusinessException 
	{
		String queryString = "select rpd from RolPagoEJB rp,RolPagoDetalleEJB rpd,RubroEJB r where " +
		" rp.id = rpd.rolpagoId and rpd.rubroId = r.id and " +
		" rpd.rolpagoId = :rolpagoId and rpd.contratoId = :contratoId and ";
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by rpd.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		query.setParameter("rolpagoId", rolpagoId);
		query.setParameter("contratoId", contratoId);
		return query.getResultList();
	}

	//public Collection findRolPagoDetalleByRopagoIdByContratoIdByRubro(Long rolpagoId,Long contratoId,Map<String, Object> mapaRolPagoDetalle,Map<String, Object> mapaRubro)
	public Collection findRolPagoDetalleByQueryByRubroMap(Map<String, Object> mapaRolPagoDetalle,Map<String, Object> mapaRubro,String... estadosRolPagoDetalle)
	throws com.spirit.exception.GenericBusinessException 
	{
		String rubroNombre = "r";
		String rolPagoDetalleNombre = "rpd";
		String whereRubro = QueryBuilder.buildWhere(mapaRubro, rubroNombre);
		String whereRolPagoDetalle = QueryBuilder.buildWhere(mapaRolPagoDetalle, rolPagoDetalleNombre);;
		String queryString = "select rpd from RolPagoEJB rp,RolPagoDetalleEJB rpd,RubroEJB r where " +
		" rp.id = rpd.rolpagoId and rpd.rubroId = r.id and ";// +
		//" rpd.rolpagoId = :rolpagoId and rpd.contratoId = :contratoId and ";
		queryString += (whereRolPagoDetalle+" and ");
		queryString += (whereRubro);
		if ( estadosRolPagoDetalle!=null && estadosRolPagoDetalle.length > 0 ){
				queryString += " and (";
			for (String estado : estadosRolPagoDetalle){
				queryString += (" rpd.estado = '"+estado+"' or"  );
			}
			queryString = queryString.substring(0, queryString.length()-3);
			queryString += ") "; 
		}
		queryString += " order by rpd.id";

		Query query = manager.createQuery(queryString);
		//query.setParameter("rolpagoId", rolpagoId);
		//query.setParameter("contratoId", contratoId);

		Iterator it = mapaRubro.keySet().iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapaRubro.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		
		it = mapaRolPagoDetalle.keySet().iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapaRolPagoDetalle.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();
	}

	public java.util.Collection findRolPagoDetalleContratoIdByMapByEstadosNormal(Map<String,Object> mapaRolPagoDetalle,
		Map<String,Object> mapaContrato,Boolean tipoProvision,TipoRolIf tipoRolIf,String... estados) throws GenericBusinessException {
		String rolPagoDetalleName = "e";
		String contratoName = "c";
		String queryString = "";
		if (!tipoProvision){
			String whereRolPagoDetalle = QueryBuilder.buildWhere(mapaRolPagoDetalle, rolPagoDetalleName);
			
			queryString = "select distinct e.contratoId from RolPagoDetalleEJB e,RubroEJB r,ContratoEJB c " +
			" where e.rubroId = r.id and e.contratoId = c.id "+
			" and not r.tipoRubro = :tipoRubroProvision " +
			" and not r.tipoRubro = :tipoRubroAnticipo " +
			" and "+whereRolPagoDetalle;
			
		} else {
			//String whereContrato = QueryBuilder.buildWhere(mapaContrato, contratoName);
			queryString = "select distinct c.id " +
				"from TipoRolEJB tr,RolPagoEJB rp,RolPagoDetalleEJB e,ContratoEJB c " +
				" where tr.id = rp.tiporolId and rp.id = e.rolpagoId and e.contratoId = c.id " +
				" and tr.id = :tipoRolId";
			TipoRolFormaPago formaPago = TipoRolFormaPago.getTipoRolPagoByLetra(tipoRolIf.getFormaPago());
			if ( formaPago == TipoRolFormaPago.PERIODO ){
				queryString += " and e.rolpagoId = :rolpagoId ";
			}
		}
		
		
		if ( estados!=null && estados.length > 0 ){
			boolean agregarEstados = true;
			String sEstados = " and (";
			for ( int i = 0 ; i < estados.length ; i++ ){
				String estado = estados[i];
				if ( estado == null ){
					agregarEstados = false;
					break;
				}
				sEstados += (" e.estado = '"+estado+"' or"  );
			}
			sEstados = sEstados.substring(0, sEstados.length()-3);
			sEstados += ")";
			if ( agregarEstados )
				queryString += sEstados;
			
		}
		
		Query query = null;
		
		if ( mapaContrato.size() > 0 )
			queryString += " and ";
		query = FindQuery.findQueryByDates(mapaContrato, contratoName, queryString, "", manager);
		
		TipoRolFormaPago formaPago = TipoRolFormaPago.getTipoRolPagoByLetra(tipoRolIf.getFormaPago());
		if ( formaPago == TipoRolFormaPago.PERIODO ){
			Long rolPagoId = (Long) mapaRolPagoDetalle.get("rolpagoId");
			if ( rolPagoId == null )
				throw new GenericBusinessException("Identificacion de Rol de Pago no Definido !!");
			query.setParameter("rolpagoId", rolPagoId);
		}
		
		if (!tipoProvision  ){
			//Para mapa de rolpagodetalle
			Set keys = mapaRolPagoDetalle.keySet();
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = mapaRolPagoDetalle.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			query.setParameter("tipoRubroProvision", TipoRubro.PROVISION.getLetra());
			query.setParameter("tipoRubroAnticipo", TipoRubro.ANTICIPO.getLetra());
		} else {
			query.setParameter("tipoRolId", tipoRolIf.getId());
		}	
		
		//Para mapa de contrato
		Set keys = mapaContrato.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapaContrato.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
	
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findRolPagoDetalleByQueryNormal(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select e from RolPagoDetalleEJB e,RubroEJB r " +
			" where e.rubroId = r.id and "+ where+ " and not r.tipoRubro = 'P' " +
			" and not r.tipoRubro = 'A' " + //SIN anticipos
			" and e.rubroEventualId is null ";
		//String queryString = "select e from RolPagoDetalleEJB e" +
		//" where "+ where;
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
	public java.util.Collection findRolPagoDetalleContratoIdByQuery(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e.contratoId from RolPagoDetalleEJB " + objectName + " where "
		+ where;
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
	public Collection<Map<String, Object>> findRolPagoDetalleEventualesByRolPagoByEstado(RolPagoIf rolPagoIf,String estado) throws GenericBusinessException{
		Long rolPagoId = rolPagoIf.getId();
		String queryString = "select e from RolPagoDetalleEJB e where e.rolpagoId = :rolpagoId ";
		if ( estado !=null )
			queryString += " and e.estado = :estado "; 
		queryString += " and e.rubroEventualId is not null";
		queryString += " order by e.id";
		Query query = manager.createQuery(queryString);
		query.setParameter("rolpagoId", rolPagoId);
		if ( estado !=null )
			query.setParameter("estado", estado);
		List<RolPagoDetalleIf> lista =  query.getResultList();
		Collection<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		if ( lista.size() == 0 )
			return resultado;
		Iterator<RolPagoDetalleIf> itDetalle = lista.iterator();
		Map<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		while( itDetalle.hasNext() ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			RolPagoDetalleIf rolPagoDetalle = itDetalle.next();
			
			Collection<RolPagoDetalleIf> detalles = new ArrayList<RolPagoDetalleIf>();
			detalles.add(rolPagoDetalle);
			mapa.put("detallesRolPago", detalles);
			
			mapa.put("contratoId", rolPagoDetalle.getContratoId());
			
			utilesLocal.verificarEmpleadoEnMapa(rolPagoDetalle.getContratoId(), mapaContratoIdNombreEmpleado);
			mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado.get(rolPagoDetalle.getContratoId()));
			
			//RubroEventualIf rubroEventualIf = rubroEventualLocal.getRubroEventual(rolPagoDetalle.getRubroEventualId());
			mapa.put("descripcion", rolPagoDetalle.getObservacion());
			
			mapa.put("total",utilitariosLocal.redondeoValor(rolPagoDetalle.getValor().doubleValue()));
			resultado.add(mapa);
		}
		return resultado;
	}
	
	public Collection<RolPagoDetalleIf> findRolPagoDetalleEventualesByMap(Map<String,Object> mapa) throws GenericBusinessException{
		String objectName = "e";
		String where = QueryBuilder.buildWhere(mapa, objectName);
		String queryString = "select e from RolPagoDetalleEJB " + objectName + " where "+ where;
		queryString +=" and e.rubroEventualId is not null";
		Query query = manager.createQuery(queryString);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();
	}
	
	public Collection<RolPagoDetalleIf> findRolPagoDetalleEventualesByMapByEstados(Map<String,Object> mapa,String... estados) throws GenericBusinessException{
		String objectName = "e";
		Long idTipoRolCobro = (Long) mapa.get("tipoRolIdCobro");
		if ( idTipoRolCobro == null )
			throw new GenericBusinessException("Tipo de Rol de Cobro no establecido para consulta !!");
		mapa.remove("tipoRolIdCobro");
		String mesCobro = (String) mapa.get("mesCobro");
		if ( mesCobro == null )
			throw new GenericBusinessException("Mes de Cobro no establecido para consulta !!");
		mapa.remove("mesCobro");
		String anioCobro = (String) mapa.get("anioCobro");
		if ( anioCobro == null )
			throw new GenericBusinessException("Año de Rol de Cobro no establecido para consulta !!");
		mapa.remove("anioCobro");
		Long tipoContratoId = (Long) mapa.get("tipoContratoId");
		if ( tipoContratoId == null )
			throw new GenericBusinessException("Tipo de contrato no establecido para consulta !!");
		mapa.remove("tipoContratoId");
		
		Long rolpagoId = (Long) mapa.get("rolpagoId");
		if ( rolpagoId != null )
			mapa.remove("rolpagoId");
		
		String where = QueryBuilder.buildWhere(mapa, objectName);
		int mesInteger = Integer.parseInt(mesCobro)-1;
		int anioInteger = Integer.parseInt(anioCobro);
		Date fechaInicio = new Date(new GregorianCalendar(anioInteger,mesInteger,1).getTimeInMillis());
		Date fechaFin = new Date(utilitariosLocal.getCalendarFinMes(mesInteger, anioInteger).getTimeInMillis());
		
		/*
		String queryString = "select e from RolPagoDetalleEJB e,RubroEventualEJB re " +  
							 " where e.rubroEventualId = re.id and "+ where+
							 " and  re.tipoRolIdCobro = :tipoRolIdCobro "+
							 " and to_char(re.fechaCobro,'mm') = :mes and to_char(re.fechaCobro,'yyyy') = :anio ";
		*/
		String queryString = "select e from RolPagoEJB rp,RolPagoDetalleEJB e,RubroEventualEJB re " +  
		 " where rp.id = e.rolpagoId and e.rubroEventualId = re.id and "+ where+
		 " and  re.tipoRolIdCobro = :tipoRolIdCobro "+
		 " and  rp.tipocontratoId = :tipoContratoId "+
		 " and re.fechaCobro >= :fechaInicio and re.fechaCobro <= :fechaFin ";
		queryString +=" and e.rubroEventualId is not null";
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for (String estado : estados){
				queryString += (" e.estado = '"+estado+"' or"  );
			}
			queryString = queryString.substring(0, queryString.length()-3);
			queryString += ")"; 
		}
		
		Query query = manager.createQuery(queryString);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setParameter("tipoRolIdCobro", idTipoRolCobro);
		//query.setParameter("mes", mesCobro);
		query.setParameter("tipoContratoId", tipoContratoId);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		return query.getResultList();
	}
	
	public Collection<RolPagoDetalleIf> findRolPagoDetalleEventualesByMapByEstados(Map<String,Object> mapa,Long rubroId,String... estados) throws GenericBusinessException{
		String objectName = "e";
		String where = QueryBuilder.buildWhere(mapa, objectName);
		String queryString = "select e from RolPagoDetalleEJB e,RubroEventualEJB re,RubroEJB r "+ 
			" where e.rubroEventualId = re.id and re.rubroId = r.id and "+ where;
			
		if ( rubroId != null )
			queryString += " and r.id = :rubroId";
		
		queryString +=" and e.rubroEventualId is not null";
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for (String estado : estados){
				queryString += (" e.estado = '"+estado+"' or"  );
			}
			queryString = queryString.substring(0, queryString.length()-3);
			queryString += ")"; 
		}
		
		Query query = manager.createQuery(queryString);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		if ( rubroId != null )
			query.setParameter("rubroId", rubroId);
		
		return query.getResultList();
	}
	
	public java.util.Collection<RolPagoDetalleIf> findRolPagoDetalleByQueryByEstados(Map<String,Object> aMap,String... estados) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from RolPagoDetalleEJB " + objectName + " where "
		+ where;
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for (String estado : estados){
				queryString += (" e.estado = '"+estado+"' or"  );
			}
			queryString = queryString.substring(0, queryString.length()-3);
			queryString += ")"; 
		}
		
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
	
	public Collection<Map<String, Object>> crearColeccionAnticiposRolPagoDetalleByQueryByEstados(Map aMap,String... estados) throws GenericBusinessException {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select e from RolPagoDetalleEJB e,RubroEJB r where " +
				" e.rubroId = r.id and r.tipoRubro = 'A' and "
		+ where;
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for (String estado : estados){
				queryString += (" e.estado = '"+estado+"' or"  );
			}
			queryString = queryString.substring(0, queryString.length()-3);
			queryString += ")"; 
		}
		
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		
		List<RolPagoDetalleIf> lista =  query.getResultList();
		
		Collection<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		if ( lista.size() == 0 )
			return resultado;
		Iterator<RolPagoDetalleIf> itDetalle = lista.iterator();
		Map<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		while( itDetalle.hasNext() ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			RolPagoDetalleIf rolPagoDetalle = itDetalle.next();
			
			Collection<RolPagoDetalleIf> detalles = new ArrayList<RolPagoDetalleIf>();
			detalles.add(rolPagoDetalle);
			mapa.put("detallesRolPago", detalles);
			
			mapa.put("contratoId", rolPagoDetalle.getContratoId());
			
			utilesLocal.verificarEmpleadoEnMapa(rolPagoDetalle.getContratoId(), mapaContratoIdNombreEmpleado);
			mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado.get(rolPagoDetalle.getContratoId()));
			
			//RubroEventualIf rubroEventualIf = rubroEventualLocal.getRubroEventual(rolPagoDetalle.getRubroEventualId());
			mapa.put("descripcion", rolPagoDetalle.getObservacion());
			
			mapa.put("total",utilitariosLocal.redondeoValor(rolPagoDetalle.getValor().doubleValue()));
			resultado.add(mapa);
		}
		return resultado;

	}
	
	public Collection<Map<String, Object>> crearColeccionAnticiposRolPagoDetalleByQueryByEstadosConEventuales(Map aMap,String... estados) throws GenericBusinessException {
		String objectName = "e";
		
		Long idTipoRolPago = (Long) aMap.get("tipoRolIdPago");
		if ( idTipoRolPago == null )
			throw new GenericBusinessException("Tipo de Rol de pago no establecido para consulta !!");
		aMap.remove("tipoRolIdPago");
		String mesPago = (String) aMap.get("mesPago");
		if ( mesPago == null )
			throw new GenericBusinessException("Mes de Pago no establecido para consulta !!");
		aMap.remove("mesPago");
		String anioPago = (String) aMap.get("anioPago");
		if ( anioPago == null )
			throw new GenericBusinessException("Año de Rol de Pago no establecido para consulta !!");
		aMap.remove("anioPago");
		
		Long rolpagoId = (Long) aMap.get("rolpagoId");
		if ( rolpagoId != null )
			aMap.remove("rolpagoId");
		
		int mesInteger = Integer.parseInt(mesPago)-1;
		int anioInteger = Integer.parseInt(anioPago);
		Date fechaInicio = new Date(new GregorianCalendar(anioInteger,mesInteger,1).getTimeInMillis());
		Date fechaFin = new Date(utilitariosLocal.getCalendarFinMes(mesInteger, anioInteger).getTimeInMillis());
		
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		//Se incluye los pagos individuales que son agrupados
		String queryString = "select e,r.id,r.nombre " +
		" from RolPagoDetalleEJB e,RubroEventualEJB re,RubroEJB r where " +
		" e.rubroEventualId = re.id and re.rubroId = r.id  and e.rubroId is null and "+
		" r.pagoIndividual = 'N' and e.tipoPagoId is not null ";
		if ( aMap.size() > 0 )
			queryString += (" and "+where);
		queryString += (" and re.tipoRolIdPago = :tipoRolIdPago ");
		queryString += (" and re.fechaPago >= :fechaInicio and re.fechaPago <= :fechaFin ");
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for (String estado : estados){
				queryString += (" e.estado = '"+estado+"' or"  );
			}
			queryString = queryString.substring(0, queryString.length()-3);
			queryString += ")"; 
		}
		queryString += " order by e.id ";
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setParameter("tipoRolIdPago", idTipoRolPago);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		List<Object[]> lista =  query.getResultList();
		Iterator<Object[]> itDetalle = lista.iterator();
		
		//Map<String,Map<String,Object>> mapaResultado =  new HashMap<String,Map<String,Object>>();
		Collection<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		Map<Long,Map<Long,Map<Long,Map<String,Map<String, Object>>>>> mapaGeneralPorRubro = 
			new HashMap<Long,Map<Long,Map<Long,Map<String,Map<String, Object>>>>>();
		while( itDetalle.hasNext() ){
			Object[] fila = itDetalle.next();
			ingresarMapaRubroEventual(resultado, fila,mapaGeneralPorRubro);
		}
		
		
		//Se incluye los pago que NO son agrupados.
		
		//queryString = "select e,r.id,r.nombre,em.nombres||' '||em.apellidos " +
		queryString = "select e,r.id,r.nombre,em.nombres,em.apellidos " +
		" from RolPagoDetalleEJB e,RubroEventualEJB re,RubroEJB r,ContratoEJB c, EmpleadoEJB em where " +
		" e.rubroEventualId = re.id and re.rubroId = r.id  and e.contratoId = c.id and c.empleadoId = em.id and " +
		" e.rubroId is null and e.tipoPagoId is not null and "+
		" r.pagoIndividual = 'S' ";
		if ( aMap.size() > 0 )
			queryString += (" and "+where);
		queryString += (" and  re.tipoRolIdPago = :tipoRolIdPago "+
		" and re.fechaPago >= :fechaInicio and re.fechaPago <= :fechaFin ");
		
		if ( estados!=null && estados.length > 0 ){
			queryString += " and (";
			for (String estado : estados){
				queryString += (" e.estado = '"+estado+"' or"  );
			}
			queryString = queryString.substring(0, queryString.length()-3);
			queryString += ")"; 
		}
		queryString += " order by e.id ";
		//queryString += " group by r.id,r.nombre,e.tipoPagoId,e.cuentaBancariaId,e.preimpreso ";
		query = manager.createQuery(queryString);

		keys = aMap.keySet();
		it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		query.setParameter("tipoRolIdPago", idTipoRolPago);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		
		lista =  query.getResultList();
		List<Object[]> listaNueva = new ArrayList<Object[]>();
		for ( Object[] fila : lista ){
			Object rolDetalle = (Object)fila[0];
			Object rubroId = (Object)fila[1];
			Object nombreRubro = (Object)fila[2];
			String nombresEmpleado = (String)fila[3];
			String apellidosEmpleado = (String)fila[4];
			listaNueva.add(new Object[]{rolDetalle,rubroId,nombreRubro,nombresEmpleado+" "+apellidosEmpleado});
			lista = listaNueva;
		}
		//Modificacion de la lista para unir los nombres y los apellidos.
		
		
		itDetalle = lista.iterator();
		//Map<String,Map<String,Object>> mapaMapasCreadosPorBenficiario = new HashMap<String,Map<String,Object>>();
		Map<String,Map<Long,Map<Long,Map<String,Map<String, Object>>>>> mapaGeneralPorBeneficiario = 
			new HashMap<String,Map<Long,Map<Long,Map<String,Map<String, Object>>>>>();
		while( itDetalle.hasNext() ){
			agruparPorBeneficiario(itDetalle, resultado,mapaGeneralPorBeneficiario);
		}
		
		return resultado;
	}

	private void agruparPorBeneficiario(Iterator<Object[]> itDetalle,
			Collection<Map<String, Object>> resultado,
			Map<String,Map<Long,Map<Long,Map<String,Map<String, Object>>>>> mapaGeneral) throws GenericBusinessException {
		
		Object[] fila = itDetalle.next();
		RolPagoDetalleIf rpd = (RolPagoDetalleIf) fila[0];
		Long rubroId = (Long) fila[1];
		String nombreRubroEventual = (String) fila[2];
		String nombreEmpleado = (String) fila[3];
		Long tipoPagoId = rpd.getTipoPagoId();
		Long cuentaBancariaId = rpd.getCuentaBancariaId();
		String preimpreso = rpd.getPreimpreso();
		
		boolean crearNuevoRegistro = true;
		//Map<String, Object> mapa = buscarBeneficiario(resultado, nombreEmpleado);
		//Map<String, Object> mapa = null;
		/*Map<String, Object> mapaSinPreimpreso = mapaMapasCreados.get("sinPreimpreso");
		for ( Map<String, Object> m : resultado ){
			crearNuevoRegistro = true;
			String nombreEmpleadoRegistrado = (String) m.get("nombreEmpleado");
			String preimpresoRegistrado = (String) m.get("preimpreso");
			Long cuentaBancariaRegistrada = (Long) m.get("cuentaBancariaId");
			if ( nombreEmpleado.equals(nombreEmpleadoRegistrado) &&
				 (preimpreso != null? preimpreso.equals(preimpresoRegistrado):false) &&
				 cuentaBancariaId.equals(cuentaBancariaRegistrada) 
			){
				//mapa = m;
				sumarTotalPorBeneficiario(m,rpd);
				crearNuevoRegistro = false;
				break;
			} else if ( mapaSinPreimpreso!= null && preimpreso == null ){
				sumarTotalPorBeneficiario(m,rpd);
				crearNuevoRegistro = false;
				break;
			}
		}*/
		Map<String,Map<String, Object>> mapaPorCuentaBancaria = buscarMapaPorBeneficiarioExistenteByRubroIdByTipoPagoByCuentaBancaria(
				mapaGeneral,nombreEmpleado,tipoPagoId, cuentaBancariaId);
			Map<String,Object> mapaPorPreimpreso = null;
			if ( mapaPorCuentaBancaria != null ){
				if ( cuentaBancariaId != null ){
					mapaPorPreimpreso  = mapaPorCuentaBancaria.get(preimpreso);
					if ( mapaPorPreimpreso != null ){
						sumarTotalEnMapaRubroEventual(mapaPorPreimpreso,rpd);
						crearNuevoRegistro = false;
					}
				} else {
					mapaPorPreimpreso = mapaPorCuentaBancaria.get(null);
					sumarTotalEnMapaRubroEventual(mapaPorPreimpreso,rpd);
					crearNuevoRegistro = false;
				}
			}
		if ( crearNuevoRegistro ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("rubroId", rubroId);
			mapa.put("nombreRubroEventual", nombreRubroEventual);
			mapa.put("tipoPagoId", tipoPagoId);
			mapa.put("cuentaBancariaId", cuentaBancariaId);
			mapa.put("preimpreso", preimpreso);
			mapa.put("nombreEmpleado", nombreEmpleado);
			mapa.put("total", Utilitarios.redondeoValor(rpd.getValor().doubleValue()) );
			Collection<RolPagoDetalleIf> listaRubroEventuales = new ArrayList<RolPagoDetalleIf>();
			listaRubroEventuales.add(rpd);
			mapa.put("detallesRolPago",listaRubroEventuales);
			
			Map<String,Map<String,Object>> mpp = new HashMap<String,Map<String,Object>>();
			mpp.put(preimpreso,mapa);
			Map<Long,Map<String,Map<String,Object>>> mpcb = new HashMap<Long,Map<String,Map<String,Object>>>();
			mpcb.put(cuentaBancariaId,mpp);
			Map<Long,Map<Long,Map<String,Map<String,Object>>>> mptp = new HashMap<Long,Map<Long,Map<String,Map<String,Object>>>>();
			mptp.put(tipoPagoId,mpcb);
			mapaGeneral.put(nombreEmpleado,mptp);
			
			resultado.add(mapa);
		}
	}

	private void sumarTotalPorBeneficiario(Map<String, Object> mapa,RolPagoDetalleIf rpd) {
		double total = (Double) mapa.get("total");
		total += rpd.getValor().doubleValue();
		mapa.put("total", Utilitarios.redondeoValor(total) );
		Collection<RolPagoDetalleIf> listaRubroEventuales = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
		listaRubroEventuales.add(rpd);
		mapa.put("detallesRolPago",listaRubroEventuales);
	}
	
	private Map<String,Map<String, Object>> buscarMapaPorBeneficiarioExistenteByRubroIdByTipoPagoByCuentaBancaria(
			Map<String,Map<Long,Map<Long,Map<String,Map<String, Object>>>>> mapaGeneral,
			String nombreBeneficiario,Long tipoPagoId,Long cuentaBancariaId) throws GenericBusinessException{
		
		if ( tipoPagoId == null )
			throw new GenericBusinessException("Tipo de Pago no establecido !!");
		Map<String,Map<String, Object>> mapa = null;
		Map<Long,Map<Long,Map<String,Map<String, Object>>>> mapaPorRubros = mapaGeneral.get(nombreBeneficiario);
		if ( mapaPorRubros != null ){
			Map<Long,Map<String,Map<String, Object>>> mapaPorTipoPago = mapaPorRubros.get(tipoPagoId);
			if ( mapaPorTipoPago != null ){
				mapa = mapaPorTipoPago.get(cuentaBancariaId);
			}
		}
		
		return mapa;
	}
	
	private void ingresarMapaRubroEventual(Collection<Map<String, Object>> resultado,Object[] fila,
			Map<Long,Map<Long,Map<Long,Map<String,Map<String, Object>>>>> mapaGeneral ) throws GenericBusinessException{
	//private void ingresarMapaRubroEventual(Map<String,Map<String, Object>> mapaResultado,Object[] fila){
		//Map<String,Object> mapa = null;
		RolPagoDetalleIf rpd = (RolPagoDetalleIf) fila[0];
		Long rubroId = (Long) fila[1];
		String nombreRubroEventual = (String) fila[2];
		Long tipoPagoId = rpd.getTipoPagoId();
		Long cuentaBancariaId = rpd.getCuentaBancariaId();
		String preimpreso = rpd.getPreimpreso();
		boolean crearNuevoRegistro = true;
		/*for ( Map<String, Object> m : resultado ){
			crearNuevoRegistro = true;
			Long rubroRegistrado = (Long)m.get("rubroId");
			String preimpresoRegistrado = (String) m.get("preimpreso");
			Long cuentaBancariaRegistrada = (Long) m.get("cuentaBancariaId");
			if ( rubroId.equals(rubroRegistrado) && 
					( preimpreso != null? preimpreso.equals(preimpresoRegistrado):false) &&
					//preimpreso == preimpresoRegistrado &&
					cuentaBancariaId.equals(cuentaBancariaRegistrada) 
			){
				//mapa = m;
				sumarTotalEnMapaRubroEventual(m, rpd);
				crearNuevoRegistro = false;
				break;
			} else if ( mapaSinPreimpreso!=null && preimpreso == null ) {
				//mapa = mapaSinPreimpreso;
				sumarTotalEnMapaRubroEventual(mapaSinPreimpreso,rpd);
				crearNuevoRegistro = false;
				break;
			}
		}*/
		Map<String,Map<String, Object>> mapaPorCuentaBancaria = buscarMapaExistenteByRubroIdByTipoPagoByCuentaBancaria(
			mapaGeneral,rubroId,tipoPagoId, cuentaBancariaId);
		Map<String,Object> mapaPorPreimpreso = null;
		if ( mapaPorCuentaBancaria != null ){
			if ( cuentaBancariaId != null ){
				mapaPorPreimpreso  = mapaPorCuentaBancaria.get(preimpreso);
				if ( mapaPorPreimpreso != null ){
					sumarTotalEnMapaRubroEventual(mapaPorPreimpreso,rpd);
					crearNuevoRegistro = false;
				}
			} else {
				mapaPorPreimpreso = mapaPorCuentaBancaria.get(null);
				sumarTotalEnMapaRubroEventual(mapaPorPreimpreso,rpd);
				crearNuevoRegistro = false;
			}
		}
		
		if ( crearNuevoRegistro ){
			Map<String,Object> mapa = new HashMap<String, Object>();
			mapa.put("rubroId", rubroId);
			mapa.put("nombreRubroEventual", nombreRubroEventual);
			mapa.put("tipoPagoId", tipoPagoId);
			mapa.put("cuentaBancariaId", cuentaBancariaId);
			mapa.put("preimpreso", preimpreso);
			mapa.put("total", Utilitarios.redondeoValor(rpd.getValor().doubleValue()) );
			Collection<RolPagoDetalleIf> listaRubroEventuales = new ArrayList<RolPagoDetalleIf>();
			listaRubroEventuales.add(rpd);
			mapa.put("detallesRolPago",listaRubroEventuales);
			
			Map<String,Map<String,Object>> mpp = new HashMap<String,Map<String,Object>>();
			mpp.put(preimpreso,mapa);
			Map<Long,Map<String,Map<String,Object>>> mpcb = new HashMap<Long,Map<String,Map<String,Object>>>();
			mpcb.put(cuentaBancariaId,mpp);
			Map<Long,Map<Long,Map<String,Map<String,Object>>>> mptp = new HashMap<Long,Map<Long,Map<String,Map<String,Object>>>>();
			mptp.put(tipoPagoId,mpcb);
			mapaGeneral.put(rubroId,mptp);
			
			resultado.add(mapa);
		}
	}
	
	private Map<String,Map<String, Object>> buscarMapaExistenteByRubroIdByTipoPagoByCuentaBancaria(
			Map<Long,Map<Long,Map<Long,Map<String,Map<String, Object>>>>> mapaGeneral,
			Long rubroId,Long tipoPagoId,Long cuentaBancariaId) throws GenericBusinessException{
		
		if ( tipoPagoId == null )
			throw new GenericBusinessException("Tipo de Pago no establecido !!");
		Map<String,Map<String, Object>> mapa = null;
		Map<Long,Map<Long,Map<String,Map<String, Object>>>> mapaPorRubros = mapaGeneral.get(rubroId);
		if ( mapaPorRubros != null ){
			Map<Long,Map<String,Map<String, Object>>> mapaPorTipoPago = mapaPorRubros.get(tipoPagoId);
			if ( mapaPorTipoPago != null ){
				mapa = mapaPorTipoPago.get(cuentaBancariaId);
			}
		}
		
		return mapa;
	}

	private void sumarTotalEnMapaRubroEventual(Map<String, Object> mapa, RolPagoDetalleIf rpd) {
		double totalRubro = (Double) mapa.get("total");
		double total = Utilitarios.redondeoValor(totalRubro + rpd.getValor().doubleValue());
		mapa.put("total", total);
		Collection<RolPagoDetalleIf> listaRubroEventuales = (Collection<RolPagoDetalleIf>) mapa.get("detallesRolPago");
		listaRubroEventuales.add(rpd);
	}
	
}
