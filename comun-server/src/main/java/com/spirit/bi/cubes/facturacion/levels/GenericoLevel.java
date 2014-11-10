package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.inventario.entity.GenericoData;
import com.spirit.inventario.entity.GenericoEJB;

public class GenericoLevel extends Level{

	@Override
	public Class getDataClass() {
		return GenericoEJB.class;
	}

	@Override
	public Object construirBlanco() {
		GenericoEJB genericoEJB=new GenericoEJB();
		genericoEJB.setId(ID_SIN);
		genericoEJB.setNombre("NINGUNO");
		return genericoEJB;
	}

}
