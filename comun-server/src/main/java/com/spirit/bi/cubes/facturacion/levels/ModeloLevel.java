package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.inventario.entity.ModeloEJB;

public class ModeloLevel extends Level{

	@Override
	public Class getDataClass() {
		return ModeloEJB.class;
	}

	@Override
	public Object construirBlanco() {
		ModeloEJB modeloEJB=new ModeloEJB();
		modeloEJB.setId(ID_SIN);
		modeloEJB.setNombre("NINGUNO");
		
		return modeloEJB;
	}

}
