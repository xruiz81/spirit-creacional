package com.spirit.crm.session;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
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

import com.spirit.crm.entity.ClienteIndicadorEJB;
import com.spirit.crm.entity.ClienteIndicadorIf;
import com.spirit.crm.session.generated._ClienteIndicadorSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ClienteIndicadorSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class ClienteIndicadorSessionEJB extends _ClienteIndicadorSession implements ClienteIndicadorSessionRemote,ClienteIndicadorSessionLocal{

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   @EJB private UtilitariosSessionLocal utilitariosLocal;
   
   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ClienteIndicadorSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   private DecimalFormat formatoEntero = new DecimalFormat("000");

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public ClienteIndicadorEJB registrarClienteIndicador(ClienteIndicadorIf modelClienteIndicador) {
		ClienteIndicadorEJB clienteIndicador = new ClienteIndicadorEJB();
		
		if ( modelClienteIndicador.getId() == null ){
			String codigo = getMaximoCodigoClienteIndicador(modelClienteIndicador.getClienteOficinaId() );
			if(codigo.equals("")){
				codigo = "001";
			}else{
				codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
			}
			clienteIndicador.setCodigo(codigo);
		} else {
			clienteIndicador.setCodigo(modelClienteIndicador.getCodigo());
		}

		clienteIndicador.setId(modelClienteIndicador.getId());
		clienteIndicador.setClienteOficinaId(modelClienteIndicador.getClienteOficinaId());
		clienteIndicador.setTipoindicadorId(modelClienteIndicador.getTipoindicadorId());
		clienteIndicador.setValor(utilitariosLocal.redondeoValor(modelClienteIndicador.getValor()));
		
		return clienteIndicador;
	}
   
   public String getMaximoCodigoClienteIndicador(Long clienteOficinaId){
		String queryString = "select max(ci.codigo) from ClienteIndicadorEJB ci where ci.clienteOficinaId = " + clienteOficinaId;
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}

}
