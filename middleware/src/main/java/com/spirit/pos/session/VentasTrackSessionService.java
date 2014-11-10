package com.spirit.pos.session;



import java.util.*;

import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.entity.VentasTrackData;
import com.spirit.pos.entity.VentasTrackIf;
import com.spirit.pos.session.generated._VentasTrackSessionService;

/**
 * The <code>VentasTrackSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 */
public interface VentasTrackSessionService extends _VentasTrackSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	 public Long procesarVentasTrack(VentasTrackIf venta) throws GenericBusinessException;

   
}
