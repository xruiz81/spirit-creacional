package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.general.entity.ProvinciaEJB;

public class ProvinciaLevel extends Level{

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return ProvinciaEJB.class;
	}

	@Override
	public Object construirBlanco() {
		ProvinciaEJB provinciaEJB=new ProvinciaEJB();
		provinciaEJB.setId(ID_SIN);
		provinciaEJB.setNombre("NINGUNA");
		return provinciaEJB;
	}

}
