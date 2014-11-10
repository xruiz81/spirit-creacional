package com.spirit.compras.session;

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

import com.spirit.compras.entity.SolicitudCompraArchivoEJB;
import com.spirit.compras.entity.SolicitudCompraArchivoIf;
import com.spirit.compras.session.generated._SolicitudCompraArchivoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>SolicitudCompraArchivoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class SolicitudCompraArchivoSessionEJB extends _SolicitudCompraArchivoSession implements SolicitudCompraArchivoSessionRemote, SolicitudCompraArchivoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(SolicitudCompraArchivoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public SolicitudCompraArchivoEJB registrarSolicitudCompraArchivo(SolicitudCompraArchivoIf modelSolicitudCompraArchivo, String urlCarpetaSevidor) {
	   SolicitudCompraArchivoEJB solicitudCompraArchivo = new SolicitudCompraArchivoEJB();
		
		int posicionUltimoSlash = -1;
		if(modelSolicitudCompraArchivo.getUrl() != null) 
			posicionUltimoSlash = modelSolicitudCompraArchivo.getUrl().lastIndexOf("\\");
		
		String nombreArchivo = "";
		if(posicionUltimoSlash != -1)
			nombreArchivo = modelSolicitudCompraArchivo.getUrl().substring(posicionUltimoSlash+1);

		solicitudCompraArchivo.setId(modelSolicitudCompraArchivo.getId());
		solicitudCompraArchivo.setSolicitudCompraId(modelSolicitudCompraArchivo.getSolicitudCompraId());
		solicitudCompraArchivo.setTipoArchivoId(modelSolicitudCompraArchivo.getTipoArchivoId());
		
		if (!nombreArchivo.equals("")){
			
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
	    	solicitudCompraArchivo.setUrl(urlCarpetaSevidor + strFilename.replaceAll(" ", "_"));
		}
		
		return solicitudCompraArchivo;
	}

}
