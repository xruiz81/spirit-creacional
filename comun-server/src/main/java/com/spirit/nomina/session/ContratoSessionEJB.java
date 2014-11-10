package com.spirit.nomina.session;

import java.math.BigDecimal;
import java.sql.Date;
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
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.ContratoEJB;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.ContratoRubroData;
import com.spirit.nomina.entity.ContratoRubroEJB;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.entity.RubroEventualEJB;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.handler.DatoGeneralContrato;
import com.spirit.nomina.session.generated._ContratoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.util.FindQuery;

/**
 * The <code>ContratoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.2 $, $Date: 2014/04/26 00:25:45 $
 *
 */
@Stateless
public class ContratoSessionEJB extends _ContratoSession implements ContratoSessionRemote,ContratoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   
   private static final String MODO_OPERACION_REGISTRADO = "R";
   
   @EJB private ContratoRubroSessionLocal contratoRubroLocal;
   @EJB private RubroEventualSessionLocal rubroEventualLocal;
   @EJB private UtilitariosSessionLocal utilitariosLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ContratoSessionEJB.class);
   
   @Resource private SessionContext ctx;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void procesarContrato(ContratoIf model, Collection<ContratoRubroIf> contratoRubroColleccion) throws GenericBusinessException {
	   
	   Collection<ContratoIf> existentes = findContratoByCodigo(model.getCodigo());
	   if ( existentes.size() > 0 )
		   throw new GenericBusinessException("Ya existe un contrato con ese mismo código !!");

	   try {
			ContratoIf contrato = registrarContrato(model);
			contrato = addContrato(contrato);

			for(ContratoRubroIf cr : contratoRubroColleccion){
				ContratoRubroIf rubroContrato = registrarRubroContrato(cr);
				rubroContrato.setContratoId(contrato.getPrimaryKey());
				contratoRubroLocal.addContratoRubro(rubroContrato);
			}
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al guardar información en ContratoEJB", e);
			throw new GenericBusinessException("Error al insertar información en Contrato - ContratoRubro");
		}
   }
  
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void actualizarContrato(ContratoIf model, Collection<ContratoRubroIf> contratosRubroColeccion, 
		   Collection<RubroIf> rubrosContratoRemovidos) throws GenericBusinessException {
	   
	   ContratoIf existente = getContrato(model.getId());
	   Collection<ContratoIf> existentes = findContratoByCodigo(model.getCodigo());
	   for ( ContratoIf cp : existentes  ){
		   if ( cp.getCodigo().equals(model.getCodigo()) && !existente.getId().equals(cp.getId()) )
			   throw new GenericBusinessException("Ya existe un contrato con ese mismo código !!");
	   }
	   
	   try {
			ContratoIf contrato = registrarContrato(model);
			saveContrato(contrato);			
			
			for(RubroIf rubroRemovido : rubrosContratoRemovidos){
				Map<String,Object> aMap = new HashMap<String,Object>();
				aMap.put("contratoId",contrato.getPrimaryKey());
				aMap.put("rubroId",rubroRemovido.getId());
				Collection<ContratoRubroIf> contratoRubros = contratoRubroLocal.findContratoRubroByQuery(aMap);
				for (ContratoRubroIf contratoRubro : contratoRubros ){
					contratoRubroLocal.deleteContratoRubro(contratoRubro.getId());
				}
			}
			for (ContratoRubroIf modelContratoRubro : contratosRubroColeccion) {
				modelContratoRubro.setContratoId(contrato.getId()!=null?contrato.getId():contrato.getPrimaryKey());
				ContratoRubroIf contratoRubro = registrarRubroContrato(modelContratoRubro);
				if ( contratoRubro.getId()!= null )
					contratoRubroLocal.saveContratoRubro(contratoRubro);
				else
					contratoRubroLocal.addContratoRubro(contratoRubro);
			}
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al actualizar información en ContratoEJB", e);
			e.printStackTrace();
			throw new GenericBusinessException("Error al actualizar información en Contrato - ContratoRubro");
		}
	}
  
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void eliminarContrato(Long contratoId) throws GenericBusinessException {
		try {
			ContratoEJB data = manager.find(ContratoEJB.class, contratoId);
			Collection<ContratoRubroIf> contratoRubroEliminadoList = contratoRubroLocal.findContratoRubroByContratoId(contratoId);
			Collection<RubroEventualIf> rubroEventualEliminadoList = rubroEventualLocal.findRubroEventualByContratoId(contratoId);
			
			for (ContratoRubroIf contratoRubroEliminado : contratoRubroEliminadoList) {
				ContratoRubroEJB dataRubroEliminado = manager.find(ContratoRubroEJB.class, contratoRubroEliminado.getId());
				manager.remove(dataRubroEliminado);
			}
			for (RubroEventualIf rubroEventualEliminado : rubroEventualEliminadoList) {
				RubroEventualEJB dataRubroEventual = manager.find(RubroEventualEJB.class, rubroEventualEliminado.getId());
				manager.remove(dataRubroEventual);
			}
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en ContratoEJB.", e);
			throw new GenericBusinessException("Error al eliminar información en Contrato");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findContratoByFechaRolPagoByTipoContratoIdByEstado(Date fechaInicioRolPago,Date fechaFinRolPago,Long tipoContratoId,String estado) throws GenericBusinessException {
		try {
			String queryString = "from ContratoEJB e " +
					" where e.estado = :estado " +
					" and e.tipocontratoId = :tipoContratoId "+
					" and ( (e.fechaInicio <= :fechaFinRolPago and e.fechaFin >= :fechaFinRolPago) " +
					" or  (e.fechaFin >= :fechaInicioRolPago) and e.fechaFin <= :fechaFinRolPago) )";
		      String orderByPart = "";
		      orderByPart += " order by e.id";
		      queryString += orderByPart;
		      Query query = manager.createQuery(queryString);
		      query.setParameter("tipoContratoId", tipoContratoId);
		      query.setParameter("estado", estado);
		      query.setParameter("fechaInicioRolPago", fechaInicioRolPago);
		      query.setParameter("fechaFinRolPago", fechaFinRolPago);
		      return query.getResultList();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error en busqueda de Contrato por fecha de Rol de Pago.", e);
			throw new GenericBusinessException("Error en busqueda de Contrato por fecha de Rol de Pago.");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findContratoBy(Date fechaInicioRolPago,Date fechaFinRolPago,Long tipoContratoId,String estado) throws GenericBusinessException {
		try {
			String queryString = "from ContratoEJB e " +
					" where e.estado = :estado " +
					" and e.tipocontratoId = :tipoContratoId "+
					" and ( (e.fechaInicio <= :fechaFinRolPago and e.fechaFin >= :fechaFinRolPago) " +
					" or  (e.fechaFin >= :fechaInicioRolPago) and e.fechaFin <= :fechaFinRolPago) )";
		      String orderByPart = "";
		      orderByPart += " order by e.id";
		      queryString += orderByPart;
		      Query query = manager.createQuery(queryString);
		      query.setParameter("tipoContratoId", tipoContratoId);
		      query.setParameter("estado", estado);
		      query.setParameter("fechaInicioRolPago", fechaInicioRolPago);
		      query.setParameter("fechaFinRolPago", fechaFinRolPago);
		      return query.getResultList();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error en busqueda de Contrato por fecha de Rol de Pago.", e);
			throw new GenericBusinessException("Error en busqueda de Contrato por fecha de Rol de Pago.");
		}
   }
   
   /*@TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findContratoDecimosByAnio(int anio) throws GenericBusinessException {
		try {
			Date fechaDecimoInicio = new Date(anio-1,11,1);	//1-Dic-Anio
			Date fechaDecimoFin = new Date(anio,10,30);		//30-Nov-Anio
			String queryString = "from ContratoEJB e where " +
					" (e.fechaInicio <= :fechaDecimoInicio and e.fechaFin <= :fechaDecimoFin) or " +
					" (e.fechaInicio >= :fechaDecimoInicio and e.fechaFin <= :fechaDecimoFin) or " +
					" (e.fechaInicio >= :fechaDecimoInicio and e.fechaFin >= :fechaDecimoFin) or " +
					" (e.fechaInicio <= :fechaDecimoInicio and e.fechaFin >= :fechaDecimoFin) ";
		      String orderByPart = "";
		      orderByPart += " order by e.id";
		      queryString += orderByPart;
		      Query query = manager.createQuery(queryString);
		      query.setParameter("fechaDecimoInicio", fechaDecimoInicio);
		      query.setParameter("fechaDecimoFin", fechaDecimoFin);
		      return query.getResultList();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error en busqueda de Contrato por fecha de Rol de Pago.", e);
			throw new GenericBusinessException("Error en busqueda de Contrato por fecha de Rol de Pago.");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findContratoByFechaRolPagoByEmpleadoId(Date fechaRolPago,Long empleadoId) throws GenericBusinessException {
		try {
			String queryString = "from ContratoEJB e where e.fechaInicio <= :fechaRolPago " +
					" and e.fechaFin >= :fechaRolPago and e.empleadoId = :empleadoId";
		      String orderByPart = "";
		      orderByPart += " order by e.id";
		      queryString += orderByPart;
		      Query query = manager.createQuery(queryString);
		      query.setParameter("fechaRolPago", fechaRolPago);
		      query.setParameter("empleadoId", empleadoId);
		      return query.getResultList();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error en busqueda de Contrato por fecha de Rol de Pago.", e);
			throw new GenericBusinessException("Error en busqueda de Contrato por fecha de Rol de Pago.");
		}
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findContratoByFechaRolPagoByContratoId(Date fechaRolPago,Long contratoId) throws GenericBusinessException {
		try {
			String queryString = "from ContratoEJB e where e.fechaInicio <= :fechaRolPago " +
					" and e.id = :contratoId";
		      String orderByPart = "";
		      orderByPart += " order by e.id";
		      queryString += orderByPart;
		      Query query = manager.createQuery(queryString);
		      query.setParameter("fechaRolPago", fechaRolPago);
		      query.setParameter("contratoId", contratoId);
		      return query.getResultList();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error en busqueda de Contrato por fecha de Rol de Pago.", e);
			throw new GenericBusinessException("Error en busqueda de Contrato por fecha de Rol de Pago.");
		}
   }*/
  
   private ContratoIf registrarContrato(ContratoIf model) {
	   
	   ContratoIf contrato = null;
	   if ( model.getId() == null )
		   contrato = new ContratoEJB();
	   else 
		   contrato = model;
	   
	   contrato.setCodigo(model.getCodigo());
	   contrato.setEmpleadoId(model.getEmpleadoId());
	   contrato.setEstado(model.getEstado());
	   contrato.setFechaElaboracion(model.getFechaElaboracion());
	   contrato.setFechaFin(model.getFechaFin());
	   contrato.setFechaInicio(model.getFechaInicio());
	   contrato.setId(model.getId());
	   contrato.setObservacion(model.getObservacion());
	   contrato.setTipocontratoId(model.getTipocontratoId());
	   
	   return contrato;
   }
  
   private ContratoRubroIf registrarRubroContrato(ContratoRubroIf modelContratoRubro) {
	   ContratoRubroIf rubroContrato = null;
	   if ( modelContratoRubro.getId() == null )
		   rubroContrato = new ContratoRubroData();
	   else 
		   rubroContrato = modelContratoRubro;
	   
	   rubroContrato.setContratoId(modelContratoRubro.getContratoId());
	   rubroContrato.setEstado(modelContratoRubro.getEstado());
	   rubroContrato.setFechaInicio(modelContratoRubro.getFechaInicio());
	   rubroContrato.setFechaFin(modelContratoRubro.getFechaFin());
	   //rubroContrato.setId(modelContratoRubro.getId());
	   rubroContrato.setRubroId(modelContratoRubro.getRubroId());
	   Double valorD = modelContratoRubro.getValor() != null ? 
			   utilitariosLocal.redondeoValor( modelContratoRubro.getValor().doubleValue() ): 
			   null;
	   BigDecimal valorBD = valorD != null ? new BigDecimal(valorD): null; 
	   rubroContrato.setValor(valorBD);
	   
	   return rubroContrato;
   }
   
   /*private RubroEventualEJB registrarRubroEventual(RubroEventualIf modelRubroEventual) {
	   RubroEventualEJB rubroEventual = new RubroEventualEJB();
	   
	   rubroEventual.setContratoId(modelRubroEventual.getContratoId());
	   rubroEventual.setEstado(modelRubroEventual.getEstado());
	   rubroEventual.setFechaCobro(modelRubroEventual.getFechaCobro());
	   rubroEventual.setId(modelRubroEventual.getId());
	   rubroEventual.setObservacion(modelRubroEventual.getObservacion());
	   rubroEventual.setRubroId(modelRubroEventual.getRubroId());
	   rubroEventual.setTipoRolIdCobro(modelRubroEventual.getTipoRolIdCobro());
	   rubroEventual.setValor(modelRubroEventual.getValor());
	   rubroEventual.setFechaPago(modelRubroEventual.getFechaPago());
	   rubroEventual.setTipoRolIdPago(rubroEventual.getTipoRolIdPago());
	   
	   return rubroEventual;
   }*/
  
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findContratoByQueryConFecha(Map aMap) throws GenericBusinessException {
	   try{
			String objectName = "e";
			String cadenaQuery = "from ContratoEJB " + objectName + " where ";
			//Query query = manager.createQuery(queryString);
			String orden = "";
			Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);
	
			Set keys = aMap.keySet();
			Iterator it = keys.iterator();
	
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
	
			}
			return query.getResultList();
	   } catch(Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error en la consulta de contrato !!");
	   }

	}
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findContratoByQuery(int startIndex, int endIndex,Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		String objectName = "e";
		String cadenaQuery = "from ContratoEJB " + objectName + " where ";
		//Query query = manager.createQuery(queryString);
		String orden = "";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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
	public int getContratoListSize(Map aMap) {
		String objectName = "e";
		String cadenaQuery = "select distinct count(*) from ContratoEJB " + objectName + " where ";
		//Query query = manager.createQuery(queryString);
		String orden = "";
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);

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

	//public Map<ContratoIf,Collection<RubroIf>> getContratosSinFondoReserva(Collection<Long> contratosId) throws GenericBusinessException{
	public Collection<ContratoIf> getContratosSinFondoReserva(Collection<Long> contratosId,Collection<String> codigosFondoReserva) throws GenericBusinessException{
		
		/*Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
		Map<ContratoIf,Collection<RubroIf>> resultado = new HashMap<ContratoIf, Collection<RubroIf>>();
		for ( Long contratoId : contratosId ){
			ContratoIf contratoIf = getContrato(contratoId);
			Collection<ContratoRubroIf> cRubros = SessionServiceLocator.getContratoRubroSessionService().findContratoRubroByContratoId(contratoId);
			ArrayList<RubroIf> rubros = new ArrayList<RubroIf>();
			for (ContratoRubroIf cr : cRubros){
				RubroIf r = utilesLocal.verificarRubrosEnMapa(mapaRubros, null, cr.getRubroId());
				rubros.add(r);
			}
			resultado.put(contratoIf, rubros);
		}
		return resultado;*/
		
		String objectName = "e";
		String queryString = "select distinct c from ContratoEJB c,ContratoRubroEJB cr,RubroEJB r " +
		" where c.id = cr.contratoId and cr.rubroId = r.id ";
		if ( contratosId.size() > 0 ){
			queryString += " and (";
			for ( Long contratoId : contratosId ){
				queryString += (" c.id ="+contratoId+" or");
			}
			queryString = queryString.substring(0,queryString.length()-2);
			queryString += " ) ";
		}
		if ( codigosFondoReserva.size() > 0 ){
			queryString += " and r.codigo not in (";
			for ( String codigoRubroFondoReserva : codigosFondoReserva ){
				queryString += ("'"+codigoRubroFondoReserva+"',");
			}
			queryString = queryString.substring(0,queryString.length()-1);
			queryString += " )";
		}
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public Collection<DatoGeneralContrato> getContratosByQueryByFechaInicioFechaFinContrato(Map<String,Object> mapa, Date fechaInicio,Date fechaFin){
		Collection<DatoGeneralContrato> resultado = new ArrayList<DatoGeneralContrato>();
		
		String objectName = "c";
		String cadenaQuery = "select c.id,e.nombres,e.apellidos,tc.nombre,c.fechaInicio " +
			" from ContratoEJB c,EmpleadoEJB e,TipoContratoEJB tc " +
			" where c.empleadoId = e.id and c.tipocontratoId = tc.id "; 
		if ( mapa.size() > 0 || fechaInicio != null || fechaFin != null )
			cadenaQuery += " and ";
		
		if ( fechaInicio != null )
			mapa.put("fechaInicio", fechaInicio);
		
		if ( fechaFin != null )
			mapa.put("fechaFin", fechaFin);
		
		Query query = FindQuery.findQueryByDates(mapa, objectName, cadenaQuery, null, manager);

		Set keys = mapa.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = mapa.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		
		List<Object[]> queryResult = query.getResultList();
		for ( Object[] fila : queryResult ){
			Long contratoId = (Long) fila[0];
			String nombres = (String) fila[1];
			String apellidos = (String) fila[2];
			String tipoContrato = (String) fila[3];
			Date fechaIngreso = (Date) fila[4];
			
			DatoGeneralContrato dgc = new DatoGeneralContrato();
			dgc.setContratoId(contratoId);
			dgc.setNombreEmpleado(apellidos+" "+nombres);
			dgc.setTipoContrato(tipoContrato);
			dgc.setFechaIngreso(fechaIngreso);
			
			resultado.add(dgc);
		}
		
		return resultado;
	}
	
	public java.util.Collection<Object[]> getContratosEmpleados(Long empresaId) throws GenericBusinessException {
		//SELECT DISTINCT C.ID, E.NOMBRES, E.APELLIDOS FROM CONTRATO C, EMPLEADO E WHERE C.EMPLEADO_ID = E.ID AND E.EMPRESA_ID = 1 AND C.ESTADO = 'A' ORDER BY E.NOMBRES ASC, E.APELLIDOS ASC
		String queryString = "select distinct c, e from ContratoEJB c, EmpleadoEJB e, ContratoRubroEJB cr, RubroEJB r where c.empleadoId = e.id and e.empresaId = :empresaId and c.estado = 'A' and c.id = cr.contratoId and cr.rubroId = r.id and (r.codigo = 'TV' or r.codigo = 'HE50' or r.codigo = 'HE100') order by e.nombres asc, e.apellidos asc";
		Query query = manager.createQuery(queryString);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
}
