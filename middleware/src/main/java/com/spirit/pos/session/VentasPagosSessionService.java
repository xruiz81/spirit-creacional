package com.spirit.pos.session;



import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.entity.VentasPagosIf;
import com.spirit.pos.session.generated._VentasPagosSessionService;

/**
 * The <code>VentasPagosSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 */
public interface VentasPagosSessionService extends _VentasPagosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/



	public void procesarVentasPagosVarios(List<VentasPagosIf> pagos) throws GenericBusinessException ;
	 public java.util.Collection findVentasPagosByQueryVariosId(Map aMap);
	 	 
	 public Collection findDonacionesDetalle(java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,Long idClienteOficina,Long color,Long modelo,Long talla,Long tipoProductoId,Long clienteoficinaId) throws GenericBusinessException ;
	 
   
}
