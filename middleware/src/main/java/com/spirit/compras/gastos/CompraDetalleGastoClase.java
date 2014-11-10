package com.spirit.compras.gastos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.spirit.compras.entity.CompraDetalleGastoData;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.exception.GenericBusinessException;

public class CompraDetalleGastoClase implements Serializable{
	
	private static final long serialVersionUID = -5707872268761328095L;

	CompraGastoIf compraGasto = null;
	
	Map<CompraDetalleIf,CompraDetalleGastoIf> detalle = null;

	public CompraDetalleGastoClase(CompraGastoIf compraGasto) throws GenericBusinessException{
		this.compraGasto = compraGasto;
		this.detalle = new LinkedHashMap<CompraDetalleIf,CompraDetalleGastoIf>();
	}

	public void guardarDetalleGastoDesdeDetalleCompra(List<CompraDetalleIf> compraDetalle){
		for ( CompraDetalleIf cd : compraDetalle ){
			CompraDetalleGastoIf cdg = new CompraDetalleGastoData();
			cdg.setCompraGastoId(compraGasto!=null?compraGasto.getId():null);
			cdg.setValor(BigDecimal.ZERO);
			detalle.put(cd,cdg);
		}
	}
	
	public Map<CompraDetalleIf,CompraDetalleGastoIf> getDetalle() {
		return detalle;
	}

	public void setDetalle(Map<CompraDetalleIf,CompraDetalleGastoIf> detalle) {
		this.detalle = detalle;
	}
	
	
}