package com.spirit.bi.cubes.facturacion.levels;

import java.util.List;

import com.spirit.bi.Dimension;
import com.spirit.bi.Level;
import com.spirit.bi.entity.BiVendedorDimEJB;
import com.spirit.general.entity.TipoClienteData;
import com.spirit.general.entity.TipoClienteEJB;

public class TipoClienteLevel extends Level {

	@Override
	public Object construirBlanco() {
		TipoClienteData tipoClienteData=new TipoClienteData();
		tipoClienteData.setNombre("SIN TIPO CLIENTE");
		tipoClienteData.setId(ID_SIN);
		return tipoClienteData;
	}

	@Override
	public Class getDataClass() {
		return TipoClienteEJB.class;
	}

}
