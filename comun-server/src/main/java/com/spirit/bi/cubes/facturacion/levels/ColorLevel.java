package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.inventario.entity.ColorEJB;

public class ColorLevel extends Level{

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return ColorEJB.class;
	}

	@Override
	public Object construirBlanco() {
		ColorEJB colorEJB=new ColorEJB();
		colorEJB.setId(ID_SIN);
		colorEJB.setNombre("NINGUNO");
		return colorEJB;
	}

}
