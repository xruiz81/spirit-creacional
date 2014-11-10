package com.spirit.bi.cubes.facturacion.dim;

import java.util.List;

import com.spirit.bi.Dimension;
import com.spirit.bi.EJBDataSource;
import com.spirit.bi.entity.BiVendedorDimEJB;
import com.spirit.general.entity.EmpleadoEJB;
import com.spirit.general.entity.EmpleadoIf;

public class VendedorDim extends Dimension {

	@Override
	public Object construir(Object objetoConsulta) {
		EmpleadoIf empleadoIf = (EmpleadoIf) objetoConsulta;
		BiVendedorDimEJB biVendedorDimEJB = new BiVendedorDimEJB();
		biVendedorDimEJB.setNombre(empleadoIf.getNombres() + " "
				+ empleadoIf.getApellidos());
		biVendedorDimEJB.setOrigenId(empleadoIf.getId());
		return biVendedorDimEJB;
	}

	@Override
	public Object construirBlanco() {
		BiVendedorDimEJB biVendedorDimEJB = new BiVendedorDimEJB();
		biVendedorDimEJB.setNombre("SIN VENDEDOR");
		biVendedorDimEJB.setOrigenId(ID_SIN);
		return biVendedorDimEJB;
	}

	@Override
	public Object consultar(Object origenID) {
		return EJBDataSource.getJpaManagerLocal().find(EmpleadoEJB.class,
				(Long) origenID);
	}

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return BiVendedorDimEJB.class;
	}

}
