package com.spirit.nomina.session;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
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
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualEJB;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.session.generated._RubroEventualSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.util.Utilitarios;

/**
 * The <code>RubroEventualSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:21 $
 *
 */
@Stateless
public class RubroEventualSessionEJB extends _RubroEventualSession implements RubroEventualSessionRemote,RubroEventualSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

	@EJB
	UtilitariosSessionLocal utilitariosLocal;
   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(RubroEventualSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findRubroEventualByContratoIdByTipoRolIdByFechaRolPago(Long contratoId,Long tipoRolId,Date fechaRolPago,String... estados) throws GenericBusinessException {
		try{
			String queryString = "select re from RubroEventualEJB re,RubroEJB r,TipoRolEJB tr " +
			" where re.rubroId = r.id and r.tiporolId = tr.id " +
			" and re.contratoId = :contratoId and re.tipoRolId = :tipoRolId " +
			" and TRUNC(re.fecha, 'MONTH') = :fechaRolPago ";
			if ( estados.length > 0 ){
				queryString += " and ";
				for ( String estado : estados ){
					queryString += (" re.estado='"+estado+"' and");
				}
				queryString = queryString .substring(0,queryString.length()-3);
			}
			String orderByPart = "";
			orderByPart += " order by re.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("contratoId", contratoId);
			query.setParameter("tipoRolId", tipoRolId);
			query.setParameter("fechaRolPago", fechaRolPago);
			return query.getResultList();
		} catch ( Exception e ){
			e.printStackTrace();
			throw new GenericBusinessException("Error al buscar Rubros Eventuales de Contratos, por Id de Contrato e Id de Tipo de Rol");
		}
	}

	public java.util.Collection<Object> findRubroEventualByQueryByEstados(Map aMap,Map<String,Date> mapaFechas,
		Collection<Long> contratosId,Boolean detallado,String tipoRubro,String... estados) throws GenericBusinessException {
		try{
			if ( aMap.get("contratoId")!=null && contratosId!=null && contratosId.size()>0)
				throw new GenericBusinessException("No pueden estar ContratoId y ContratosSet establecidos en esta consulta !!");
			if ( contratosId!=null && contratosId.size() > 0 )
				aMap.remove("contratoId");
			
			Set<Long> tiposRolesCobro = (Set<Long>) aMap.remove("tiposRolesCobro");
			
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "";
			if (detallado)
				queryString = "select e from RubroEventualEJB e,RubroEJB r " +
					" where e.rubroId = r.id ";
			else 
				queryString = "select e.rubroId,e.identificacion,sum(e.valor) from RubroEventualEJB e,RubroEJB r " +
					" where e.rubroId = r.id ";
			
			Date fechaCobroInicio = mapaFechas.get("fechaCobroInicio");
			Date fechaCobroFin = mapaFechas.get("fechaCobroFin");
			
			if ( tipoRubro != null ){
				queryString += " and r.tipoRubro = :tipoRubro "; 
			}
			
			if ( fechaCobroInicio != null ){
				queryString += " and e.fechaCobro >= :fechaCobroInicio ";
			}
			
			if ( fechaCobroFin != null ){
				//if ( fechaCobroInicio != null )
					queryString += " and ";
				queryString += " e.fechaCobro <= :fechaCobroFin ";
			}
	
			if ( contratosId!= null && contratosId.size() > 0 ){
				//if ( fechaCobroInicio != null || fechaCobroFin != null )
					queryString += " and "; 
				queryString += " ( ";
				for ( Long contratoId : contratosId ){
					queryString += (" e.contratoId="+contratoId+" or");
				}
				queryString = queryString.substring(0,queryString.length()-3);
				queryString += " ) ";
			}
			
			if ( tiposRolesCobro != null && tiposRolesCobro.size() > 0){
				//if ( fechaCobroInicio != null || fechaCobroFin != null || 
				//		 (contratosId!= null && contratosId.size() > 0) )
					queryString += " and ";
				queryString += " ( ";
				for ( Long tipoRolId : tiposRolesCobro ){
					queryString += (" e.tipoRolIdCobro="+tipoRolId+" or");
				}
				queryString = queryString.substring(0,queryString.length()-3);
				queryString += " )";
			}
			
			if ( estados!= null && estados.length > 0 ){
				//if ( fechaCobroInicio != null || fechaCobroFin != null || 
				//	 (contratosId!= null && contratosId.size() > 0) || 
				//	 (tiposRolesCobro != null && tiposRolesCobro.size() > 0) )
					queryString += " and "; 
				queryString += " ( ";
				for ( String estado : estados ){
					queryString += (" e.estado='"+estado+"' or");
				}
				queryString = queryString.substring(0,queryString.length()-3);
				queryString += " )";
			}
			
			//if (aMap.size() > 0){
				queryString += (" and "+where);
			//}
			
			if (!detallado)
				queryString += " group by e.rubroId,e.identificacion ";
			
			if (detallado)
				queryString += " order by e.fechaCobro ";
			
			Query query = manager.createQuery(queryString);
	
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
	
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
						
			if ( fechaCobroInicio != null ){
				Calendar calInicio = new GregorianCalendar();
				calInicio.setTimeInMillis(fechaCobroInicio.getTime());
				calInicio.set(Calendar.DAY_OF_MONTH,1);
				query.setParameter("fechaCobroInicio", new Date(calInicio.getTime().getTime()) );
			}
			
			if ( fechaCobroFin != null ){
				Calendar calFin = new GregorianCalendar();
				calFin.setTimeInMillis(fechaCobroFin.getTime());
				calFin.set( Calendar.DAY_OF_MONTH , calFin.getActualMaximum(Calendar.DAY_OF_MONTH) );
				query.setParameter("fechaCobroFin", new Date(calFin.getTime().getTime()) );
			}
			
			if ( tipoRubro != null ){
				query.setParameter("tipoRubro", tipoRubro );
			}
			
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en consulta de Rubros Eventuales !!");
		}
	}

	public java.util.Collection<Object[]> findRubroEventualByQueryByMesCobroByAnioCobroByTipoContratoId(Map aMap,String mes,String anio,Long tipoContratoId) throws GenericBusinessException {
		try{
			int mesInteger = Integer.parseInt(mes)-1;
			int anioInteger = Integer.parseInt(anio);
			Date fechaInicio = new Date(new GregorianCalendar(anioInteger,mesInteger,1).getTimeInMillis());
			Date fechaFin = new Date(utilitariosLocal.getCalendarFinMes(mesInteger, anioInteger).getTimeInMillis());
			
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			
			if(!where.trim().equals("")){
				where = where + "and ";
			}
			
			String queryString = "select e, em.nombres, em.apellidos, r.nombre from RubroEventualEJB e, ContratoEJB c, EmpleadoEJB em, RubroEJB r " +
					" where e.contratoId = c.id and c.empleadoId = em.id and e.rubroId = r.id and " + where;
			queryString += " e.fechaCobro >= :fechaInicio and e.fechaCobro <= :fechaFin ";
			
			if ( tipoContratoId != null )
				queryString += " and c.tipocontratoId = "+tipoContratoId;
			
			queryString += " order by r.nombre ";
			
			Query query = manager.createQuery(queryString);
	
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
	
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
	
			ArrayList<Object[]> lista = (ArrayList<Object[]>) query.getResultList();
			ArrayList<Object[]> listaNueva = new ArrayList<Object[]>();
			for ( Object[]  o : lista ){
				listaNueva.add(new Object[]{o[0],o[1]+" "+o[2],o[3]});
			}
			return listaNueva;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en consulta de Rubros Eventuales !!");
		}
	}
	
	public Collection<Map<String, Object>> findRubroEventualByQueryByEstadosAgrupadosRubroByMesByAnioParaAutorizacion(
			RolPagoIf rolPagoIf,Map<String,Object> aMap,String... estados) throws GenericBusinessException {
		try{
			//Se incluye los pagos individuales que son agrupados
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			Collection<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
			
			int mesInteger = Integer.parseInt(rolPagoIf.getMes())-1;
			int anioInteger = Integer.parseInt(rolPagoIf.getAnio());
			Date fechaInicio = new Date(new GregorianCalendar(anioInteger,mesInteger,1).getTimeInMillis());
			Date fechaFin = new Date(utilitariosLocal.getCalendarFinMes(mesInteger, anioInteger).getTimeInMillis());
			
			String queryString = "select e,r.id,r.nombre " +
			" from RubroEventualEJB e,RubroEJB r  " +
			" where e.rubroId = r.id and e.fechaPago is not null " +
			" and r.pagoIndividual = 'N' "+
			" and e.tipoRolIdPago = :tipoRolPago "+
			//" and e.fechaPago >= :fechaInicio and e.fechaPago <= :fechaFin and "+where;
			" and e.fechaPago >= :fechaInicio and e.fechaPago <= :fechaFin ";
			if ( aMap.size() > 0 )
				queryString += (" and " + where);
			
			if ( estados!=null && estados.length > 0 ){
				queryString += " and (";
				for (String estado : estados){
					queryString += (" e.estado = '"+estado+"' or "  );
				}
				queryString = queryString.substring(0, queryString.length()-3);
				queryString += ")"; 
			}
			queryString += " order by r.id";
			//queryString += " group by r.id,r.nombre ";
			
			Query query = manager.createQuery(queryString);
	
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
	
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
	
			}
			//query.setParameter("mes", mes);
			//query.setParameter("anio", anio);
			query.setParameter("tipoRolPago", rolPagoIf.getTiporolId());
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			
			List<Object[]> lista =  query.getResultList();
			
			//if ( lista.size() == 0 )
			//	return resultado;
			Iterator<Object[]> itDetalle = lista.iterator();
			while( itDetalle.hasNext()) {
				Object[] fila = itDetalle.next();
				RubroEventualIf re = (RubroEventualIf) fila[0];
				Long rubroId = (Long) fila[1];
				String nombre = (String) fila[2];
				ingresarMapaRubroEventual(resultado,re,rubroId,nombre);
			} 
			
			//Se incluye los pago que NO son agrupados.
			/*
			queryString = "select e,r.id,r.nombre||' - '||em.nombres||' '||em.apellidos " +
			" from RubroEventualEJB e,RubroEJB r,ContratoEJB c, EmpleadoEJB em  " +
			" where e.rubroId = r.id and e.contratoId = c.id and c.empleadoId = em.id and " +
			" e.fechaPago is not null and "+
			" r.pagoIndividual = 'S' and "+ 
			" to_char(e.fechaPago,'mm') = :mes and to_char(e.fechaPago,'yyyy') = :anio and "+ where;
			 */
			queryString = "select e,r.id,r.nombre||' - '||em.nombres||' '||em.apellidos " +
			" from RubroEventualEJB e,RubroEJB r,ContratoEJB c, EmpleadoEJB em  " +
			" where e.rubroId = r.id and e.contratoId = c.id and c.empleadoId = em.id and " +
			" e.fechaPago is not null and "+
			" r.pagoIndividual = 'S' and "+ 
			" e.tipoRolIdPago = :tipoRolPago and "+
			//" e.fechaPago >= :fechaInicio and e.fechaPago <= :fechaFin and "+where;
			" e.fechaPago >= :fechaInicio and e.fechaPago <= :fechaFin ";
			if ( aMap.size() > 0 )
				queryString += (" and " + where);
			
			if ( estados!=null && estados.length > 0 ){
				queryString += " and ( ";
				for (String estado : estados){
					queryString += (" e.estado = '"+estado+"' or "  );
				}
				queryString = queryString.substring(0, queryString.length()-3);
				queryString += ")"; 
			}
			queryString += " order by r.id ";
			//queryString += " group by r.id,r.nombre ";
			
			query = manager.createQuery(queryString);

			keys = aMap.keySet();
			it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			//query.setParameter("mes", mes);
			//query.setParameter("anio", anio);
			query.setParameter("tipoRolPago", rolPagoIf.getTiporolId());
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			lista =  query.getResultList();

			//if ( lista.size() == 0 )
			//	return resultado;
			itDetalle = lista.iterator();
			while( itDetalle.hasNext() ){
				agruparPorNombreRubroEventual(resultado,itDetalle);
			}
			
			return resultado;
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en consulta de Rubros Eventuales !!");
		}
	}

	private Map<String, Object> agruparPorNombreRubroEventual(
			Collection<Map<String, Object>> resultado,
			Iterator<Object[]> itDetalle) {
		Object[] fila = itDetalle.next();
		RubroEventualIf re = (RubroEventualIf) fila[0];
		Long rubroId = (Long) fila[1];
		String nombre = (String) fila[2];
		
		/*
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("rubroId", rubroId);
			mapa.put("nombreRubroEventual", nombre);
			mapa.put("total", Utilitarios.redondeoValor(re.getValor().doubleValue()) );
			Collection<RubroEventualIf> listaRubroEventuales = new ArrayList<RubroEventualIf>();
			listaRubroEventuales.add(re);
			mapa.put("detalles",listaRubroEventuales);*/
			
		Map<String, Object> mapa = buscarMapaRubroEventual(resultado, nombre);
		if ( mapa == null ){
			mapa = new HashMap<String, Object>();
			mapa.put("rubroId", rubroId);
			mapa.put("nombreRubroEventual", nombre);
			mapa.put("total", Utilitarios.redondeoValor(re.getValor().doubleValue()) );
			Collection<RubroEventualIf> listaRubroEventuales = new ArrayList<RubroEventualIf>();
			listaRubroEventuales.add(re);
			mapa.put("detalles",listaRubroEventuales);
			resultado.add(mapa);
		} else {
			double total = (Double)mapa.get("total");
			total += re.getValor().doubleValue();
			mapa.put("total", Utilitarios.redondeoValor(total) );
			Collection<RubroEventualIf> listaRubroEventuales = (Collection<RubroEventualIf>) mapa.get("detalles");
			listaRubroEventuales.add(re);
			mapa.put("detalles",listaRubroEventuales);
		}
		return mapa;
	}
	
	private Map<String, Object> buscarMapaRubroEventual(Collection<Map<String, Object>> resultado,String nombre){
		for ( Map<String, Object> m: resultado ){
			if ( nombre.equals(m.get("nombreRubroEventual")) ){
				return m;
			}
		}
		return null;
	}
	
	private void ingresarMapaRubroEventual(Collection<Map<String, Object>> resultado,RubroEventualIf re,Long rubroId,String nombre){
		Map<String, Object> mapa = null;
		for ( Map<String, Object> m : resultado ){
			Long r = (Long)m.get("rubroId");
			if ( rubroId.equals(r) ){
				mapa = m;
				double totalRubro = (Double) mapa.get("total");
				double total = Utilitarios.redondeoValor(totalRubro + re.getValor().doubleValue());
				mapa.put("total", total);
				Collection<RubroEventualIf> listaRubroEventuales = (Collection<RubroEventualIf>) mapa.get("detalles");
				listaRubroEventuales.add(re);
				break;
			}
		}
		if ( mapa == null ){
			mapa = new HashMap<String, Object>();
			mapa.put("rubroId", rubroId);
			mapa.put("nombreRubroEventual", nombre);
			mapa.put("total", Utilitarios.redondeoValor(re.getValor().doubleValue()) );
			Collection<RubroEventualIf> listaRubroEventuales = new ArrayList<RubroEventualIf>();
			listaRubroEventuales.add(re);
			mapa.put("detalles",listaRubroEventuales);
			resultado.add(mapa);
		}
	}
	
	
	public Collection<RolPagoIf> getRolPagoAnticiposList(String... estadoDetalle) throws GenericBusinessException{
		try {
			String queryRolesPago = "select re from RubroEventualEJB re " +
				" where re.fechaPago is not null  ";
			
			if ( estadoDetalle!= null && estadoDetalle.length > 0 ){
				queryRolesPago += "and (";
				for ( String estado : estadoDetalle ){
					queryRolesPago += (" re.estado='"+estado+"' or");
				}
				queryRolesPago = queryRolesPago.substring(0,queryRolesPago.length()-3);
				queryRolesPago += " )";
			}
			
			Query query = manager.createQuery(queryRolesPago);
			//query.setParameter("estado", estadoDetalle);
			ArrayList<RubroEventualIf> rubrosEventuales = (ArrayList<RubroEventualIf>) query.getResultList();
			Set<BasicoRubroEventual> setRubros = new HashSet<BasicoRubroEventual>();
			for (RubroEventualIf re : rubrosEventuales){
				BasicoRubroEventual bre = new BasicoRubroEventual();
				bre.transformarRubroEventual(re);
				setRubros.add(bre);
			}
			
			if ( setRubros.size() == 0 )
				return new ArrayList<RolPagoIf>();
			
			String queryString = "select distinct rp from RolPagoEJB rp " +
					" where  ";
			if ( setRubros.size() > 0 ){
				DecimalFormat formatoDosEnteros = new DecimalFormat("00");
				for ( BasicoRubroEventual bre : setRubros ){
					queryString += (" ( rp.tiporolId = "+bre.getTipoRol()+" and "+
						" rp.mes = '"+formatoDosEnteros.format( bre.getMes()+1 )+"' "+
						" and rp.anio = '"+bre.getAnio()+"' ) ");
					queryString += " or";
				}
				queryString = queryString .substring(0,queryString.length()-2);
			}
			queryString += " order by rp.anio,rp.mes";
			query = manager.createQuery(queryString);
			
			/*String queryRolesPago = "select distinct rp.mes,rp.anio from RolPagoEJB rp";
			Query query = manager.createQuery(queryRolesPago);
			ArrayList<Object[]> rolesPago = (ArrayList<Object[]>) query.getResultList();
			ArrayList<Date> fechasInicio = new ArrayList<Date>();
			ArrayList<Date> fechasFin = new ArrayList<Date>();
			String queryString = "select distinct rp "+
				" from RolPagoEJB rp,RubroEventualEJB re " + 
				" where rp.tiporolId = re.tipoRolIdPago  " +
				" and re.fechaPago is not null " +
				" and re.estado = :estado "+
				" and ";
			int contadorFechas = 0;
			for ( Object[] rp : rolesPago ){
				contadorFechas++;
				int mesInteger = Integer.parseInt((String)rp[0])-1;
				int anioInteger = Integer.parseInt((String)rp[1]);
				Date fechaInicio = new Date(new GregorianCalendar(anioInteger,mesInteger,1).getTimeInMillis());
				Date fechaFin = new Date(utilitariosLocal.getCalendarFinMes(mesInteger, anioInteger).getTimeInMillis());
				queryString += " (re.fechaPago >= :fechaInicio"+contadorFechas+" and re.fechaPago <= :fechaFin"+contadorFechas+")";
				queryString += " or";
				fechasInicio.add(fechaInicio);
				fechasFin.add(fechaFin);
			}
			queryString = queryString.substring(0, queryString.length()-2);
			
			queryString += " order by rp.id";
			query = manager.createQuery(queryString);
			query.setParameter("estado", estadoDetalle);
			
			for ( int i = 1 ; i<=contadorFechas ; i++  ){
				Date fi = fechasInicio.get(i-1);
				Date ff = fechasFin.get(i-1);
				query.setParameter("fechaInicio"+i, fi);
				query.setParameter("fechaFin"+i, ff);
			}*/
			
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error al concultar Lista de Rol de Pago");
		}
	}
	
	class BasicoRubroEventual{
		Long tipoRol = null;
		Integer mes = null;
		Integer anio = null;
		
		public BasicoRubroEventual() {
		}
		
		@Override
		public boolean equals(Object obj) {
			if ( obj instanceof BasicoRubroEventual ){
				BasicoRubroEventual re = (BasicoRubroEventual) obj;
				if ( tipoRol.equals(re.getTipoRol()) &&
					 mes.equals(re.getMes()) &&
					 anio.equals(re.getAnio()) )
					 return true;
			}
			return super.equals(obj);
		}

		@Override
		public int hashCode() {
			return 1;
		}
		
		public void transformarRubroEventual(RubroEventualIf re){
			tipoRol = re.getTipoRolIdPago();
			Calendar cal = new GregorianCalendar();
			java.util.Date fecha = new java.util.Date(re.getFechaPago().getTime());
			cal.setTime(fecha);
			mes = cal.get(Calendar.MONTH);
			anio = cal.get(Calendar.YEAR);
			
		}
		
		public Date getFechaInicio(){
			Calendar cal = new GregorianCalendar(anio,mes,1);
			return new Date(cal.getTime().getTime()); 
		}
		
		public Long getTipoRol() {
			return tipoRol;
		}

		public void setTipoRol(Long tipoRol) {
			this.tipoRol = tipoRol;
		}

		public Integer getMes() {
			return mes;
		}

		public void setMes(Integer mes) {
			this.mes = mes;
		}

		public Integer getAnio() {
			return anio;
		}

		public void setAnio(Integer anio) {
			this.anio = anio;
		}
		
	}
	
	public Collection<RubroEventualIf> findRubroEventualesByTipoRolCobroIdByContratoByMesByAnioByEstado(Long tipoRolCobroId,Long contratoId,String mes,String anio,String... estadosDetalle) throws GenericBusinessException{
		try {
			
			int mesInteger = Integer.parseInt(mes)-1;
			int anioInteger = Integer.parseInt(anio);
			Date fechaInicio = new Date(new GregorianCalendar(anioInteger,mesInteger,1).getTimeInMillis());
			Date fechaFin = new Date(utilitariosLocal.getCalendarFinMes(mesInteger, anioInteger).getTimeInMillis());
			
			/*
			String queryString = "select distinct re from RubroEventualEJB re "+ 
				" where re.fechaCobro is not null and re.tipoRolIdCobro = :tipoRolCobroId" +
				" and re.contratoId = :contratoId "+
				" and to_char(re.fechaCobro,'mm') = :mes and to_char(re.fechaCobro,'yyyy') = :anio ";
			*/
			String queryString = "select distinct re from RubroEventualEJB re "+ 
				" where re.fechaCobro is not null and re.tipoRolIdCobro = :tipoRolCobroId" +
				" and re.contratoId = :contratoId "+
				" and re.fechaCobro >= :fechaInicio and re.fechaCobro <= :fechaFin ";
			
			if ( estadosDetalle!=null && estadosDetalle.length > 0 ){
				queryString += " and (";
				for (String estado : estadosDetalle){
					queryString += (" re.estado = '"+estado+"' or"  );
				}
				queryString = queryString.substring(0, queryString.length()-3);
				queryString += ")"; 
			}
			queryString += " order by re.id";
			Query query = manager.createQuery(queryString);
			//query.setParameter("estado", estadoDetalle);
			query.setParameter("tipoRolCobroId", tipoRolCobroId);
			query.setParameter("contratoId", contratoId);
			//query.setParameter("mes", mes);
			//query.setParameter("anio", anio);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar Lista de Rol de Pago");
		}
	}
	
	public RubroEventualIf registrarRubroEventual(RubroEventualIf modelRubroEventual) {
		RubroEventualIf rubroEventual = null;
		   if ( modelRubroEventual.getId() == null ){
			   rubroEventual = new RubroEventualEJB();
			   rubroEventual.setEstado(modelRubroEventual.getEstado());
		   }else 
			   rubroEventual = getRubroEventual(modelRubroEventual.getId());
		   
		   rubroEventual.setContratoId(modelRubroEventual.getContratoId());
		   rubroEventual.setFechaCobro(modelRubroEventual.getFechaCobro());
		   rubroEventual.setId(modelRubroEventual.getId());
		   rubroEventual.setObservacion(modelRubroEventual.getObservacion());
		   rubroEventual.setRubroId(modelRubroEventual.getRubroId());
		   rubroEventual.setTipoRolIdCobro(modelRubroEventual.getTipoRolIdCobro());
		   rubroEventual.setValor(modelRubroEventual.getValor());
		   rubroEventual.setFechaPago(modelRubroEventual.getFechaPago());
		   rubroEventual.setTipoRolIdPago(modelRubroEventual.getTipoRolIdPago());
		   rubroEventual.setIdentificacion(modelRubroEventual.getIdentificacion());
		   
		   return rubroEventual;
	   }
	
}
