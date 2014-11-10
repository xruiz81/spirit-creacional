package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.general.entity.PaisEJB;

public class PaisLevel extends Level{

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return PaisEJB.class;
	}

	@Override
	public Object construirBlanco() {
		PaisEJB paisEJB=new PaisEJB();
		paisEJB.setId(ID_SIN);
		paisEJB.setNombre("NINGUNO");
		return paisEJB;
	}

}
