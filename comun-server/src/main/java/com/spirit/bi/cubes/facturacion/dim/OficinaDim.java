package com.spirit.bi.cubes.facturacion.dim;

import java.util.List;

import com.spirit.bi.Dimension;
import com.spirit.bi.EJBDataSource;
import com.spirit.bi.cubes.facturacion.levels.CiudadLevel;
import com.spirit.bi.cubes.facturacion.levels.PaisLevel;
import com.spirit.bi.cubes.facturacion.levels.ProvinciaLevel;
import com.spirit.bi.entity.BiDocumentoDimEJB;
import com.spirit.bi.entity.BiOficinaDimEJB;
import com.spirit.general.entity.CiudadEJB;
import com.spirit.general.entity.EmpleadoEJB;
import com.spirit.general.entity.OficinaEJB;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.PaisEJB;
import com.spirit.general.entity.ProvinciaEJB;

public class OficinaDim extends Dimension {

	private CiudadLevel ciudadHelper=new CiudadLevel();
	private ProvinciaLevel provinciaHelper=new ProvinciaLevel();
	private PaisLevel paisHelper=new PaisLevel();
	@Override
	public Object construir(Object objetoConsulta) {
		OficinaIf oficinaIf=(OficinaIf)objetoConsulta;
		
		BiOficinaDimEJB biOficinaDimEJB = new BiOficinaDimEJB();
		biOficinaDimEJB.setDescripcion(oficinaIf.getNombre());
		biOficinaDimEJB.setOrigenId(oficinaIf.getId());
		
		CiudadEJB ciudadEJB=(CiudadEJB)ciudadHelper.getObjetoDimension(oficinaIf.getCiudadId());
		ProvinciaEJB provinciaEJB=(ProvinciaEJB)provinciaHelper.getObjetoDimension(ciudadEJB.getProvinciaId());
		PaisEJB paisEJB=(PaisEJB)paisHelper.getObjetoDimension(provinciaEJB.getPaisId());
		
		biOficinaDimEJB.setCiudadId(ciudadEJB.getId());
		biOficinaDimEJB.setCiudad(ciudadEJB.getNombre());
		
		biOficinaDimEJB.setProvincia(provinciaEJB.getNombre());
		biOficinaDimEJB.setProvinciaId(provinciaEJB.getId());
		
		biOficinaDimEJB.setPais(paisEJB.getNombre());
		biOficinaDimEJB.setPaisId(paisEJB.getId());
		
		return biOficinaDimEJB;
	}

	@Override
	public Object construirBlanco() {
		BiOficinaDimEJB biOficinaDimEJB = new BiOficinaDimEJB();
		biOficinaDimEJB.setDescripcion("SIN OFICINA");
		biOficinaDimEJB.setOrigenId(ID_SIN);
		return biOficinaDimEJB;
	}

	@Override
	public Object consultar(Object origenID) {
		return EJBDataSource.getJpaManagerLocal().find(OficinaEJB.class, (Long)origenID);
	}

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return BiOficinaDimEJB.class;
	}

}
