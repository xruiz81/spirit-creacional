package com.spirit.pos.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.pos.session.generated._VentasConsolidadoSessionService;

/**
 * The <code>VentasConsolidadoSessionService</code> bean exposes the business
 * methods in the interface.
 * 
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 */
public interface VentasConsolidadoSessionService extends _VentasConsolidadoSessionService {

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	public VentasConsolidadoIf getVentasConsolidado(String codigoCaja,String identificacionEmpleado) throws Exception;
	
	 public java.util.Collection getVentasConsolidadoFechaApertura(String cajaCodigo,String fechaCierre) throws Exception;
	  
	 public java.util.Collection findVentasConsolidadoByFechaAperturaVarioscajaCodigo(java.sql.Timestamp fechaApertura,String cajaCodigo) ;
	 
}
