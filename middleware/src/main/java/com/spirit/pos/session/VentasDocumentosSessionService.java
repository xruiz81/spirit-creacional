package com.spirit.pos.session;



import java.util.*;

import com.spirit.pos.session.generated._VentasDocumentosSessionService;

/**
 * The <code>VentasDocumentosSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 */
public interface VentasDocumentosSessionService extends _VentasDocumentosSessionService{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	 public java.util.Collection findVentasDocumentosByQueryVariosId(Map aMap);

 
}
