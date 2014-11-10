package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.general.entity.CiudadEJB;

public class CiudadLevel extends Level{

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return CiudadEJB.class;
	}

	@Override
	public Object construirBlanco() {
		CiudadEJB ciudadEJB=new CiudadEJB();
		ciudadEJB.setId(ID_SIN);
		ciudadEJB.setNombre("NINGUNA");
		return ciudadEJB;
	}

}
