package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.medios.entity.ReunionAsistenteEJB;
import com.spirit.medios.entity.ReunionAsistenteIf;
import com.spirit.medios.session.generated._ReunionAsistenteSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ReunionAsistenteSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class ReunionAsistenteSessionEJB extends _ReunionAsistenteSession implements ReunionAsistenteSessionRemote, ReunionAsistenteSessionLocal {

	@PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ReunionAsistenteEJB registrarReunionAsistenteAgencia(ReunionAsistenteIf modelReunionAsistenteAgencia, EmpleadoIf modelEmpleado) {
		ReunionAsistenteEJB reunionAsistenteAgencia = new ReunionAsistenteEJB();
		
		reunionAsistenteAgencia.setId(modelReunionAsistenteAgencia.getId());
		reunionAsistenteAgencia.setReunionId(modelReunionAsistenteAgencia.getReunionId());
		reunionAsistenteAgencia.setClienteContactoId(modelReunionAsistenteAgencia.getClienteContactoId());
		reunionAsistenteAgencia.setEmpleadoId(modelEmpleado.getId());
		reunionAsistenteAgencia.setClienteContacto(modelReunionAsistenteAgencia.getClienteContacto());
		
		return reunionAsistenteAgencia;
	}
	
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ReunionAsistenteEJB registrarReunionAsistenteCliente(ReunionAsistenteIf modelReunionAsistenteCliente, ClienteContactoIf modelClienteContacto) {
		ReunionAsistenteEJB reunionAsistenteCliente = new ReunionAsistenteEJB();
		
		reunionAsistenteCliente.setId(modelReunionAsistenteCliente.getId());
		reunionAsistenteCliente.setReunionId(modelReunionAsistenteCliente.getReunionId());
		reunionAsistenteCliente.setClienteContactoId(modelClienteContacto.getId());
		reunionAsistenteCliente.setEmpleadoId(modelReunionAsistenteCliente.getEmpleadoId());
		reunionAsistenteCliente.setClienteContacto(modelReunionAsistenteCliente.getClienteContacto());
		
		return reunionAsistenteCliente;
	}
	
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ReunionAsistenteEJB registrarReunionAsistenteProspectoCliente(ReunionAsistenteIf modelReunionAsistenteProspectoCliente, String modelProspectoCliente) {
		ReunionAsistenteEJB reunionAsistenteProspectoCliente = new ReunionAsistenteEJB();
		
		reunionAsistenteProspectoCliente.setId(modelReunionAsistenteProspectoCliente.getId());
		reunionAsistenteProspectoCliente.setReunionId(modelReunionAsistenteProspectoCliente.getReunionId());
		reunionAsistenteProspectoCliente.setClienteContactoId(modelReunionAsistenteProspectoCliente.getClienteContactoId());
		reunionAsistenteProspectoCliente.setEmpleadoId(modelReunionAsistenteProspectoCliente.getEmpleadoId());
		reunionAsistenteProspectoCliente.setClienteContacto(modelProspectoCliente);
		
		return reunionAsistenteProspectoCliente;
	}

}
