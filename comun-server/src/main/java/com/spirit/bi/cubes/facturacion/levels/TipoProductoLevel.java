package com.spirit.bi.cubes.facturacion.levels;

import com.spirit.bi.Level;
import com.spirit.inventario.entity.TipoProductoEJB;

public class TipoProductoLevel extends Level{

	@Override
	public Object construirBlanco() {
		TipoProductoEJB tipoProductoEJB=new TipoProductoEJB();
		tipoProductoEJB.setId(ID_SIN);
		tipoProductoEJB.setNombre("SIN TIPO");
		return tipoProductoEJB;
	}

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return TipoProductoEJB.class;
	}

}
