package com.spirit.nomina.session;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoGastoDeducibleEJB;
import com.spirit.nomina.entity.ContratoGastoDeducibleIf;
import com.spirit.nomina.session.generated._ContratoGastoDeducibleSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class ContratoGastoDeducibleSessionEJB extends _ContratoGastoDeducibleSession implements ContratoGastoDeducibleSessionRemote,ContratoGastoDeducibleSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	
	public void actualizarContratoGastoDeducible(Collection<ContratoGastoDeducibleIf> contratoGastosDeduciblesRemovidos,
			Collection<ContratoGastoDeducibleIf> contratoGastoDeducibleColleccion) throws GenericBusinessException {
		
		
		for ( ContratoGastoDeducibleIf cgd : contratoGastosDeduciblesRemovidos ) {
			deleteContratoGastoDeducible(cgd.getId());
		}
		
		for ( ContratoGastoDeducibleIf cgd : contratoGastoDeducibleColleccion ){
			ContratoGastoDeducibleIf registrado = registrarContratoGastoDeducible(cgd);
			if ( registrado.getId() == null ){
				addContratoGastoDeducible(registrado);
			} else {
				saveContratoGastoDeducible(registrado);
			}
		}
		
		
	}
	
	private ContratoGastoDeducibleIf registrarContratoGastoDeducible(ContratoGastoDeducibleIf  cgd){
		
		ContratoGastoDeducibleIf nuevo = null;
		if ( cgd.getId() == null ){
			nuevo = new ContratoGastoDeducibleEJB();
		} else {
			nuevo = cgd;
		}
		
		nuevo.setContratoId(cgd.getContratoId());
		nuevo.setGastoDeducibleId(cgd.getGastoDeducibleId());
		nuevo.setValor(cgd.getValor());
		nuevo.setFecha(cgd.getFecha());
		//nuevo.set
		
		return nuevo;
	}

	
	public Collection<ContratoGastoDeducibleIf> findContratoGastoDeducibleByFechas(Long contratoId,Date fecha){
		
		int enero = 0;
		int primeroEnero = 1;
		//Los gastos deducibles se setean el primero de enero de cada año.
		java.sql.Date fechaInicio = new java.sql.Date(fecha.getYear(), enero, primeroEnero);
		
		ArrayList<ContratoGastoDeducibleIf> cgds = new ArrayList<ContratoGastoDeducibleIf>();
		
		/*String queryString = "select cgd from ContratoGastoDeducibleEJB cgd" +
				" where cgd.contratoId = :contratoId and :fecha >= cgd.fecha ";*/
		
		//SOLO SE BUSCAN LOS GASTOS DEDUCIBLES DEL AÑO EN CURSO
		String queryString = "select cgd from ContratoGastoDeducibleEJB cgd" +
				" where cgd.contratoId = :contratoId and cgd.fecha >= :fechaInicio and cgd.fecha <= :fecha ";
		
		queryString += " order by cgd.fecha desc";
		Query query = manager.createQuery(queryString);
		query.setParameter("contratoId", contratoId);
		query.setParameter("fecha", fecha);
		query.setParameter("fechaInicio", fechaInicio);
		ArrayList<ContratoGastoDeducibleIf> resultado = (ArrayList<ContratoGastoDeducibleIf>)query.getResultList();
		for ( ContratoGastoDeducibleIf cgd : resultado ){
			if ( !existeGastoDeducible(cgds, cgd) ){
				cgds.add(cgd);
			}
		}
		return cgds;
		
	}
	
	private boolean existeGastoDeducible(ArrayList<ContratoGastoDeducibleIf> cgds,ContratoGastoDeducibleIf cgd){
		for ( ContratoGastoDeducibleIf cg : cgds ){
			if ( cg.getGastoDeducibleId().equals(cgd.getGastoDeducibleId()) ){
				return true;
			}
		}
		return false;
	}
	
}
