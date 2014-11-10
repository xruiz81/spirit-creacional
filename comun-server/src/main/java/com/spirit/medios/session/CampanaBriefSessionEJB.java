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

import com.spirit.medios.entity.CampanaBriefEJB;
import com.spirit.medios.entity.CampanaBriefIf;
import com.spirit.medios.session.generated._CampanaBriefSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CampanaBriefSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CampanaBriefSessionEJB extends _CampanaBriefSession implements CampanaBriefSessionRemote, CampanaBriefSessionLocal {

	@PersistenceContext(unitName="spirit")
   private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CampanaBriefEJB registrarCampanaBrief(CampanaBriefIf modelBrief,String urlCarpetaSevidor) {
		CampanaBriefEJB campanaBrief = new CampanaBriefEJB();
		
		int posicionUltimoSlash = -1;
		if(modelBrief.getUrlDescripcion() != null) 
			posicionUltimoSlash = modelBrief.getUrlDescripcion().lastIndexOf("\\");
		
		String nombreArchivo = "";
		if(posicionUltimoSlash != -1)
			nombreArchivo = modelBrief.getUrlDescripcion().substring(posicionUltimoSlash+1);

		campanaBrief.setId(modelBrief.getId());
		campanaBrief.setTipoBriefId(modelBrief.getTipoBriefId());
		campanaBrief.setCampanaId(modelBrief.getCampanaId());
		campanaBrief.setDescripcion(modelBrief.getDescripcion());
		
		if (!nombreArchivo.equals("")){
			int puntoExt = nombreArchivo.lastIndexOf(".");
	    	String strFilename = nombreArchivo.substring(0, puntoExt) + ".zip";	    	
	    	campanaBrief.setUrlDescripcion(urlCarpetaSevidor + strFilename.replaceAll(" ", "_"));
			//campanaBrief.setUrlDescripcion(urlCarpetaSevidor + nombreArchivo.replaceAll(" ", "_"));
		}
				
		return campanaBrief;
	}

}
