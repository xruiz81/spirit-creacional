package com.spirit.nomina.session;

import java.math.BigDecimal;
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
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.ContratoEJB;
import com.spirit.nomina.entity.ContratoPlantillaDetalleEJB;
import com.spirit.nomina.entity.ContratoPlantillaDetalleIf;
import com.spirit.nomina.entity.ContratoPlantillaEJB;
import com.spirit.nomina.entity.ContratoPlantillaIf;
import com.spirit.nomina.entity.ContratoRubroEJB;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.entity.RubroEventualEJB;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.handler.ModoOperacionRubro;
import com.spirit.nomina.session.generated._ContratoPlantillaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.util.FindQuery;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class ContratoPlantillaSessionEJB extends _ContratoPlantillaSession implements ContratoPlantillaSessionRemote,ContratoPlantillaSessionLocal  {

	 private static Logger log = LogService.getLogger(ContratoSessionEJB.class);
	   
	 @Resource private SessionContext ctx;
	 
	 private @EJB ContratoPlantillaDetalleSessionLocal contratoPlantillaDetalleLocal;
	 
	 private @EJB RubroSessionLocal rubroLocal;
	 
	 private @EJB UtilitariosSessionLocal utilitariosLocal;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	   public void procesarContratoPlantilla(ContratoPlantillaIf model,
			   Map<Long, BigDecimal> mapaRubrosContrato)
	   throws GenericBusinessException {

		   Collection<ContratoPlantillaIf> existentes = findContratoPlantillaByTipoContratoId(model.getTipoContratoId());
		   if ( existentes.size() > 0 )
			   throw new GenericBusinessException("Ya existe una plantilla para Tipo de Contrato seleccionado !!");
		   
		   existentes = findContratoPlantillaByCodigo(model.getCodigo());
		   if ( existentes.size() > 0 )
			   throw new GenericBusinessException("Ya existe una plantilla con ese mismo código !!");

		   try {
			   ContratoPlantillaIf contrato = registrarContratoPlantilla(model);
			   contrato = addContratoPlantilla(contrato);

			   for(Long rubroId : mapaRubrosContrato.keySet()){
				   BigDecimal valor = null;
				   RubroIf rubroIf = rubroLocal.getRubro(rubroId);
				   if(rubroIf.getModoOperacion().equals(ModoOperacionRubro.REGISTRADO.getLetra()))
					   valor = mapaRubrosContrato.get(rubroIf);
				   ContratoPlantillaDetalleIf detalle = registrarPlantillaDetalle(rubroIf, valor);
				   detalle.setContratoPlantillaId(contrato.getPrimaryKey());
				   contratoPlantillaDetalleLocal.addContratoPlantillaDetalle(detalle);
			   }

		   } catch (Exception e) {
			   ctx.setRollbackOnly();
			   log.error("Error al guardar información en ContratoEJB", e);
			   throw new GenericBusinessException("Error al insertar información en ContratoPlantilla - ContratoPlantillaDetalle !!");
		   }
	   }

	   public void actualizarContratoPlantilla(ContratoPlantillaIf model,
			   Collection<ContratoPlantillaDetalleIf> detalleColleccion,
			   Collection<RubroIf> detallesRemovidos)
	   throws GenericBusinessException {

		   Collection<ContratoPlantillaIf> existentes = findContratoPlantillaByTipoContratoId(model.getTipoContratoId());
		   if ( existentes.size() > 1 )
			   throw new GenericBusinessException("Existe mas de una plantilla para Tipo de Contrato seleccionado !!");

		   ContratoPlantillaIf existente = getContratoPlantilla(model.getId());
		   if ( !existente.getTipoContratoId().equals(model.getTipoContratoId()) ){
			   existentes = findContratoPlantillaByTipoContratoId(model.getTipoContratoId());
			   if ( existentes.size() > 0 )
				   throw new GenericBusinessException("Ya existe una plantilla para Tipo de Contrato seleccionado !!");
		   }
		   existentes = findContratoPlantillaByCodigo(model.getCodigo());
		   for ( ContratoPlantillaIf cp : existentes  ){
			   if ( cp.getCodigo().equals(model.getCodigo()) && !existente.getId().equals(cp.getId()) )
				   throw new GenericBusinessException("Ya existe una plantilla con ese mismo código !!");
		   }
		   
		   try {
			   ContratoPlantillaIf contratoPlantilla = registrarContratoPlantilla(model);
			   saveContratoPlantilla(contratoPlantilla);			
			   Long contratoPlantillaId = contratoPlantilla.getId()!=null?contratoPlantilla.getId():contratoPlantilla.getPrimaryKey();

			   for(RubroIf rubroRemovido : detallesRemovidos){
				   Map<String,Object> aMap = new HashMap<String,Object>();
				   aMap.put("contratoPlantillaId",contratoPlantillaId);
				   aMap.put("rubroId",rubroRemovido.getId());
				   Collection<ContratoPlantillaDetalleIf> detalles = contratoPlantillaDetalleLocal.findContratoPlantillaDetalleByQuery(aMap);
				   for (ContratoPlantillaDetalleIf detalle : detalles ){
					   contratoPlantillaDetalleLocal.deleteContratoPlantillaDetalle(detalle.getId());
				   }
			   }
			   for (ContratoPlantillaDetalleIf detalle : detalleColleccion) {
				   ContratoPlantillaDetalleIf detalleNuevo = registrarPlantillaDetalle(detalle);
				   detalleNuevo.setContratoPlantillaId(contratoPlantillaId);
				   if ( detalleNuevo.getId()!= null )
					   contratoPlantillaDetalleLocal.saveContratoPlantillaDetalle(detalleNuevo);
				   else
					   contratoPlantillaDetalleLocal.saveContratoPlantillaDetalle(detalleNuevo);
			   }

		   } catch (Exception e) {
			   ctx.setRollbackOnly();
			   log.error("Error al actualizar información en ContratoEJB", e);
			   e.printStackTrace();
			   throw new GenericBusinessException("Error al actualizar información en ContratoPlantilla - ContratoPlantillaDetalle !!");
		   }

	   }

	   public void eliminarContratoPlantilla(Long contratoPlantillaId)
	   throws GenericBusinessException {

		   try {
			   ContratoPlantillaIf contratoPlantilla = getContratoPlantilla(contratoPlantillaId);
			   if ( contratoPlantilla == null )
				   throw new GenericBusinessException("Contrato Plantilla no existe !!");
			   
			   Collection<ContratoPlantillaDetalleIf> detalles = 
				   contratoPlantillaDetalleLocal.findContratoPlantillaDetalleByContratoPlantillaId(contratoPlantilla.getId());
			   for( ContratoPlantillaDetalleIf detalle : detalles ){
				   contratoPlantillaDetalleLocal.deleteContratoPlantillaDetalle(detalle.getId());
			   }
			   deleteContratoPlantilla(contratoPlantilla.getId());

		   } catch (GenericBusinessException e) {
			   ctx.setRollbackOnly();
			   log.error(e.getMessage(), e);
			   throw new GenericBusinessException(e.getMessage());
		   } catch (Exception e) {
			   ctx.setRollbackOnly();
			   log.error("Error al eliminar información en ContratoPlantilla.", e);
			   throw new GenericBusinessException("Error al eliminar información en Contrato");
		   }

	   }
	   

	   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public java.util.Collection findContratoPlantillaByQuery(int startIndex, int endIndex,Map aMap) throws GenericBusinessException {
		   if ((endIndex - startIndex) < 0) {
			   return new ArrayList();
		   }
		   try{
			   String objectName = "e";
			   String cadenaQuery = "from ContratoPlantillaEJB " + objectName + " where ";
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
		   } catch(Exception e){
			   e.printStackTrace();
			   throw new GenericBusinessException("Error en consulta de Contrato Plantilla !!");
		   }

	   }

	   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public int getContratoPlantillaListSize(Map aMap) throws GenericBusinessException {
		   try {
			   String objectName = "e";
			   String cadenaQuery = "select distinct count(*) from ContratoPlantillaEJB " + objectName + " where ";
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
		   } catch(Exception e){
			   e.printStackTrace();
			   throw new GenericBusinessException("Error en consulta de Contrato Plantilla !!");
		   }
	   }



	   
	   

	   private ContratoPlantillaIf registrarContratoPlantilla(ContratoPlantillaIf model) {

		   ContratoPlantillaIf contrato = null;
		   if ( model.getId() == null )
			   contrato = new ContratoPlantillaEJB();
		   else 
			   contrato = model;

		   contrato.setCodigo(model.getCodigo());
		   contrato.setTipoContratoId(model.getTipoContratoId());
		   contrato.setObservacion(model.getObservacion());

		   return contrato;
	   }

	   private ContratoPlantillaDetalleIf registrarPlantillaDetalle(RubroIf modelRubro,BigDecimal valor) {
		   ContratoPlantillaDetalleIf detalle = new ContratoPlantillaDetalleEJB();
		   detalle.setRubroId(modelRubro.getId());
		   if ( valor != null ){
			   Double valorD = valor.doubleValue();
			   detalle.setValor(new BigDecimal(valorD));
		   }
		   return detalle;
	   }

	   private ContratoPlantillaDetalleIf registrarPlantillaDetalle(ContratoPlantillaDetalleIf modelDetalle) {
		   ContratoPlantillaDetalleIf detalle = null;
		   if ( modelDetalle.getId() == null )
			   detalle = new ContratoPlantillaDetalleEJB();
		   else 
			   detalle = modelDetalle;

		   detalle.setRubroId(modelDetalle.getRubroId());
		   Double valorD = modelDetalle.getValor() != null ? 
				   utilitariosLocal.redondeoValor( modelDetalle.getValor().doubleValue() ): 
					   null;
				   BigDecimal valorBD = valorD != null ? new BigDecimal(valorD): null; 
				   detalle.setValor(valorBD);

				   return detalle;
	   }

}
