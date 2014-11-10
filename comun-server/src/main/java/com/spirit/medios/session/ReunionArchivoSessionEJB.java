package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.medios.entity.ReunionArchivoEJB;
import com.spirit.medios.entity.ReunionArchivoIf;
import com.spirit.medios.session.generated._ReunionArchivoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>ReunionArchivoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class ReunionArchivoSessionEJB extends _ReunionArchivoSession implements ReunionArchivoSessionRemote, ReunionArchivoSessionLocal {

	   @PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ReunionArchivoEJB registrarReunionArchivo(ReunionArchivoIf modelReunionArchivo, String urlCarpetaSevidor) {
		ReunionArchivoEJB reunionArchivo = new ReunionArchivoEJB();
		
		int posicionUltimoSlash = -1;
		if(modelReunionArchivo.getUrlDescripcion() != null) 
			posicionUltimoSlash = modelReunionArchivo.getUrlDescripcion().lastIndexOf("\\");
		
		String nombreArchivo = "";
		if(posicionUltimoSlash != -1)
			nombreArchivo = modelReunionArchivo.getUrlDescripcion().substring(posicionUltimoSlash+1);

		reunionArchivo.setId(modelReunionArchivo.getId());
		reunionArchivo.setReunionId(modelReunionArchivo.getReunionId());
		reunionArchivo.setTipoArchivoId(modelReunionArchivo.getTipoArchivoId());
		reunionArchivo.setFecha(modelReunionArchivo.getFecha());
		
		if (!nombreArchivo.equals("")){
			
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
	        reunionArchivo.setUrlDescripcion(urlCarpetaSevidor + strFilename.replaceAll(" ", "_"));
			//reunionArchivo.setUrlDescripcion(urlCarpetaSevidor + nombreArchivo.replaceAll(" ", "_"));
		}
		
		reunionArchivo.setEstado(modelReunionArchivo.getEstado());
		
		return reunionArchivo;
	}

}
