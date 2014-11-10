package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.medios.entity.MarcaProductoEJB;

public class MarcaLevel extends Level{

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return MarcaProductoEJB.class;
	}

	@Override
	public Object construirBlanco() {
		MarcaProductoEJB marcaProductoEJB=new MarcaProductoEJB();
		marcaProductoEJB.setId(ID_SIN);
		marcaProductoEJB.setNombre("NINGUNA");
		return marcaProductoEJB;
	}

}
