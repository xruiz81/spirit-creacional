package com.spirit.bi.cubes.facturacion.levels;

import java.util.List;

import com.spirit.bi.Dimension;
import com.spirit.bi.Level;
import com.spirit.bi.entity.BiVendedorDimEJB;
import com.spirit.general.entity.TipoClienteEJB;
import com.spirit.general.entity.TipoProveedorData;
import com.spirit.general.entity.TipoProveedorEJB;

public class TipoProveedorLevel extends Level {

	@Override
	public Object construirBlanco() {
		TipoProveedorData tipoProveedorData = new TipoProveedorData();
		tipoProveedorData.setNombre("NINGUNO");
		tipoProveedorData.setId(ID_SIN);
		return tipoProveedorData;
	}

	@Override
	public Class getDataClass() {
		return TipoProveedorEJB.class;
	}

}
