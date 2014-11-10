package com.spirit.nomina.session;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoRubroData;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.nomina.handler.VentasHorasExtrasEmpleado;
import com.spirit.nomina.session.generated._ContratoRubroSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ContratoRubroSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:21 $
 *
 */
@Stateless
public class ContratoRubroSessionEJB extends _ContratoRubroSession implements ContratoRubroSessionRemote, ContratoRubroSessionLocal {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   @EJB	private RubroSessionLocal rubroSessionLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ContratoRubroSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
 * @throws GenericBusinessException 
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findContratoRubroByContratoIdByTipoRolId(Long contratoId,Long tipoRolId) throws GenericBusinessException {
	   try{
	     String queryString = "select cr from ContratoRubroEJB cr,RubroEJB r,TipoRolEJB tr " +
	     		" where cr.rubroId = r.id and r.tiporolId = tr.id " +
	     		" and cr.contratoId = :contratoId and tr.id = :tipoRolId";
	     String orderByPart = "";
	     orderByPart += " order by cr.id";
	     queryString += orderByPart;
	     Query query = manager.createQuery(queryString);
	     query.setParameter("contratoId", contratoId);
	     query.setParameter("tipoRolId", tipoRolId);
	     return query.getResultList();
	   } catch ( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al buscar Rubros de Contratos, por Id de Contrato y por Id de Tipo de Rol");
	   }
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findContratoRubroByContratoIdByTipoRol(Long contratoId,TipoRolIf tipoRolIf) throws GenericBusinessException {
	   try{
	     String queryString = "select cr from ContratoRubroEJB cr,RubroEJB r,TipoRolEJB tr " +
		     " where cr.rubroId = r.id and r.tiporolId = tr.id " +
	  		 " and cr.contratoId = :contratoId and tr.id = :tipoRolId ";
	     if ( tipoRolIf.getNombre().contains("QUINCENAL") ){		//si el tiporol es quincenal
	    	 queryString  += ( " and r.frecuencia = 'Q' " );	//busco rubro con frecuencia quincenal
	    	 //queryString  += ( " and r.tipoRubro = 'A' " );		//busco rubros con tipo anticipo
	     }
	     else if ( tipoRolIf.getNombre().contains("MENSUAL") ){	//si el tiporol es mensual
	    	 queryString  += ( " and r.frecuencia = 'M' " );	//busco rubro con frecuencia mensual
	     }
	     String orderByPart = "";
	     orderByPart += " order by cr.id";
	     queryString += orderByPart;
	     Query query = manager.createQuery(queryString);
	     query.setParameter("contratoId", contratoId);
	     query.setParameter("tipoRolId", tipoRolIf.getId());
	     return query.getResultList();
	   } catch ( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al buscar Rubros de Contratos, por Id de Contrato y por Id de Tipo de Rol");
	   }
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findContratoRubroByContratoIdByFrecuenciaSinProvisiones(Long contratoId,TipoRolIf tipoRolIf) throws GenericBusinessException {
	   try{
	     //String queryString = "select cr from ContratoRubroEJB cr,RubroEJB r,TipoRolEJB tr " +
		 //    " where cr.rubroId = r.id and r.tiporolId = tr.id " +
		     String queryString = "select cr from ContratoRubroEJB cr,RubroEJB r " +
		     " where cr.rubroId = r.id " +
	  		 " and cr.contratoId = :contratoId " +
	  		 //" and not r.tipoRubro = 'P' ";	//Sin rubros que sean de PROVISION
	  		" and not r.tipoRubro = '"+TipoRubro.PROVISION.getLetra()+"'";
	  		 //" and tr.id = :tipoRolId ";
	     if ( tipoRolIf.getNombre().contains("QUINCENAL") ){		//si el tiporol es quincenal
	    	 queryString  += ( " and r.frecuencia = 'Q' " );	//busco rubro con frecuencia quincenal
	    	 //queryString  += ( " and r.tipoRubro = 'A' " );		//busco rubros con tipo anticipo
	     }
	     else if ( tipoRolIf.getNombre().contains("MENSUAL") ){	//si el tiporol es mensual
	    	 queryString  += ( " and r.frecuencia = 'M' " );	//busco rubro con frecuencia mensual
	     }
	     String orderByPart = "";
	     orderByPart += " order by cr.id";
	     queryString += orderByPart;
	     Query query = manager.createQuery(queryString);
	     query.setParameter("contratoId", contratoId);
	     return query.getResultList();
	   } catch ( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al buscar Rubros de Contratos, por Id de Contrato y por Frecuencia");
	   }
   }
   
   /*@TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findContratoRubroByContratoIdRubroId(Long contratoId,Long rubroId) throws GenericBusinessException {
	   try{
	     String queryString = "select cr from ContratoRubroEJB cr " +
		     " where cr.contratoId = :contratoId " +
	  		 " and  cr.rubroId = :rubroId";
	  	 String orderByPart = "";
	     orderByPart += " order by cr.id";
	     queryString += orderByPart;
	     Query query = manager.createQuery(queryString);
	     query.setParameter("contratoId", contratoId);
	     query.setParameter("rubroId", rubroId);
	     return query.getResultList();
	   } catch ( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al buscar Rubros Decimos de Contratos, por Id de Contrato y por Frecuencia");
	   }
   }*/
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Map<String, BigDecimal> findMapaContratoRubroByRubroResgistrado(Long contratoId) throws GenericBusinessException {
	   try{
	     String queryString = "select r.codigo,cr.valor from ContratoRubroEJB cr,RubroEJB r " +
		     " where cr.rubroId = r.id and r.modoOperacion='R' "+
	  		 " and cr.contratoId = :contratoId ";
	     
	     String orderByPart = "";
	     orderByPart += " order by cr.id";
	     queryString += orderByPart;
	     Query query = manager.createQuery(queryString);
	     query.setParameter("contratoId", contratoId);
	     List lista = query.getResultList(); 
	     Map<String, BigDecimal> mapa = new HashMap<String, BigDecimal>();
	     for ( Iterator it = lista.iterator(); it.hasNext(); ){
	    	 Object[] codigoValor = (Object[]) it.next();
	    	 mapa.put((String)codigoValor[0], (BigDecimal)codigoValor[1]);
	     }
	     return mapa;
	   } catch ( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al buscar Rubros de Contratos, por rubros con modo de operacion \"REGISTRADO\"");
	   }
   }
   
   public java.util.Collection<ContratoRubroIf> findContratoRubroByMapByTipoRubro(Map<String,Object> mapaContratoRubro,String tipoRubro) throws GenericBusinessException {
	   try {
		   String where = QueryBuilder.buildWhere(mapaContratoRubro, "cr");
		   String queryString = "select cr from ContratoRubroEJB cr, RubroEJB r "+
		   		" where cr.rubroId = r.id and " +
		   		" r.tipoRubro = :tipoRubro and "+
		   		where;
		   
		   Query query = manager.createQuery(queryString);

		   Set keys = mapaContratoRubro.keySet();
		   Iterator it = keys.iterator();

		   while (it.hasNext()) {
			   String propertyKey = (String) it.next();
			   Object property = mapaContratoRubro.get(propertyKey);
			   query.setParameter(propertyKey, property);

		   }

		   query.setParameter("tipoRubro", tipoRubro);
		   
		   List countQueryResult = query.getResultList();
		   return countQueryResult;

	   } catch ( Exception e ){
		   e.printStackTrace();
		   throw new GenericBusinessException("Error al buscar Rubros de Contratos");
	   }
	   
   }
   
   @SuppressWarnings("unchecked")
   public void saveTotalVentasHorasExtrasEmpleado(Long idEmpresa, Vector<VentasHorasExtrasEmpleado> datosVector) throws GenericBusinessException {
	   Map<String, RubroIf> rubrosMap = new HashMap<String, RubroIf>();
	   try {
		   Iterator<RubroIf> rubroIterator = rubroSessionLocal.findRubroByEmpresaId(idEmpresa).iterator();
		   while (rubroIterator.hasNext()) {
			   RubroIf rubro = rubroIterator.next();
			   rubrosMap.put(rubro.getCodigo(), rubro);
		   }
		   Map<Long, Map<Long, ContratoRubroIf>> contratoRubrosMapByContradoId = new HashMap<Long, Map<Long, ContratoRubroIf>>();
		   Iterator<ContratoRubroIf> contratoRubroIterator = findContratoRubroTotalVentasHorasExtras(idEmpresa).iterator();
		   while (contratoRubroIterator.hasNext()) {
			   Map<Long, ContratoRubroIf> contratoRubroMap = new HashMap<Long, ContratoRubroIf>();
			   ContratoRubroIf contratoRubro = contratoRubroIterator.next();
			   if (contratoRubrosMapByContradoId.get(contratoRubro.getContratoId()) != null)
				   contratoRubroMap = contratoRubrosMapByContradoId.get(contratoRubro.getContratoId());
			   contratoRubroMap.put(contratoRubro.getRubroId(), contratoRubro);
			   contratoRubrosMapByContradoId.put(contratoRubro.getContratoId(), contratoRubroMap);
		   }
		   for (int i=0; i<datosVector.size(); i++) {
			   VentasHorasExtrasEmpleado data = datosVector.get(i);
			   if (contratoRubrosMapByContradoId.get(data.getContrato().getId()) != null) {
				   Map<Long, ContratoRubroIf> contratoRubroMap = contratoRubrosMapByContradoId.get(data.getContrato().getId());
				   Iterator<String> it = rubrosMap.keySet().iterator();
				   while (it.hasNext()) {
					   ContratoRubroIf contratoRubro = new ContratoRubroData();
					   RubroIf rubro = rubrosMap.get(it.next());
					   if (contratoRubroMap.get(rubro.getId()) != null)
						   contratoRubro = contratoRubroMap.get(rubro.getId());
					   else {
						   contratoRubro.setContratoId(data.getContrato().getId());
						   contratoRubro.setRubroId(rubro.getId());
					   }
					   if (rubro.getCodigo().equals("TV"))
						   contratoRubro.setValor(BigDecimal.valueOf(data.getTotalVentas()));
					   else if (rubro.getCodigo().equals("HE50"))
						   contratoRubro.setValor(BigDecimal.valueOf(data.getNumeroHorasExtras50()));
					   else if (rubro.getCodigo().equals("H100"))
						   contratoRubro.setValor(BigDecimal.valueOf(data.getNumeroHorasExtras100()));
					   if (contratoRubro.getId() == null)
						   addContratoRubro(contratoRubro);
					   else
						   saveContratoRubro(contratoRubro);
				   }
			   }
		   }
	   } catch (GenericBusinessException e) {
		   e.printStackTrace();
	   }
   }
   
   private Collection<ContratoRubroIf> findContratoRubroTotalVentasHorasExtras(Long idEmpresa) { 
	   //SELECT DISTINCT CR.*, R.* FROM CONTRATO_RUBRO CR, RUBRO R WHERE CR.RUBRO_ID = R.ID AND (R.CODIGO = 'TV' OR R.CODIGO = 'HE50' OR R.CODIGO = 'H100') AND R.EMPRESA_ID = 1 ORDER BY CR.CONTRATO_ID ASC
	   String queryString = "select distinct cr from ContratoRubroEJB cr, RubroEJB r where cr.rubroId = r.id and (r.codigo = 'TV' or r.codigo = 'HE50' or r.codigo = 'H100') and r.empresaId = :idEmpresa order by cr.contratoId asc";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("idEmpresa", idEmpresa);
	   return query.getResultList();
   }
}
