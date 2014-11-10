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

import com.spirit.medios.entity.CampanaArchivoEJB;
import com.spirit.medios.entity.CampanaArchivoIf;
import com.spirit.medios.session.generated._CampanaArchivoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CampanaArchivoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CampanaArchivoSessionEJB extends _CampanaArchivoSession implements CampanaArchivoSessionRemote, CampanaArchivoSessionLocal {

	@PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CampanaArchivoEJB registrarCampanaArchivo(CampanaArchivoIf modelArchivo,String urlCarpetaSevidor) {
		CampanaArchivoEJB campanaArchivo = new CampanaArchivoEJB();
		
		int posicionUltimoSlash = -1;
		if(modelArchivo.getUrlDescripcion() != null) 
			posicionUltimoSlash = modelArchivo.getUrlDescripcion().lastIndexOf("\\");
		
		String nombreArchivo = "";
		if(posicionUltimoSlash != -1)
			nombreArchivo = modelArchivo.getUrlDescripcion().substring(posicionUltimoSlash+1);

		campanaArchivo.setId(modelArchivo.getId());
		campanaArchivo.setTipoArchivoId(modelArchivo.getTipoArchivoId());
		campanaArchivo.setCampanaId(modelArchivo.getCampanaId());
		campanaArchivo.setFecha(modelArchivo.getFecha());
		
		if (!nombreArchivo.equals("")){
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
	    	campanaArchivo.setUrlDescripcion(urlCarpetaSevidor + strFilename.replaceAll(" ", "_"));
			//campanaArchivo.setUrlDescripcion(urlCarpetaSevidor+ nombreArchivo.replaceAll(" ", "_"));
		}
		
		return campanaArchivo;
	}

}
