package com.spirit.medios.session;

import java.util.List;

import javax.ejb.Stateless;

import com.spirit.medios.entity.HerramientasMediosEJB;
import com.spirit.medios.entity.HerramientasMediosIf;
import com.spirit.medios.session.generated._HerramientasMediosSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class HerramientasMediosSessionEJB extends _HerramientasMediosSession implements HerramientasMediosSessionRemote,HerramientasMediosSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


	public void procesarHerramientasMedios(List<HerramientasMediosIf> herramientasColeccion, List<HerramientasMediosIf> herramientasColeccionEliminado) throws com.spirit.exception.GenericBusinessException{
		for(HerramientasMediosIf herramienta : herramientasColeccion){
			if(herramienta.getId() == null){
				HerramientasMediosEJB model = registrarHerramienta(herramienta);
				manager.persist(model);
			}else{
				HerramientasMediosEJB model = registrarHerramienta(herramienta);
				manager.merge(model);
			}
		}
		
		for(HerramientasMediosIf herramientaEliminado : herramientasColeccionEliminado){
			if(herramientaEliminado.getId() != null){
				HerramientasMediosEJB model = manager.find(HerramientasMediosEJB.class, herramientaEliminado.getId());
				manager.remove(model);
				manager.flush();
			}
		}
	}
	
	public HerramientasMediosEJB registrarHerramienta(HerramientasMediosIf herramienta){
		
		HerramientasMediosEJB model = new HerramientasMediosEJB();
		model.setClienteOficinaId(herramienta.getClienteOficinaId());
		model.setId(herramienta.getId());
		model.setProveedorOficinaId(herramienta.getProveedorOficinaId());
		model.setFrecuencia(herramienta.getFrecuencia());
		
		return model;
	}
}
