package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.inventario.entity.PresentacionEJB;

public class PresentacionLevel extends Level{

	@Override
	public Class getDataClass() {
		return PresentacionEJB.class;
	}

	@Override
	public Object construirBlanco() {
		PresentacionEJB presentacionEJB=new PresentacionEJB();
		presentacionEJB.setId(ID_SIN);
		presentacionEJB.setNombre("NINGUNA");
		return presentacionEJB;
	}

}
