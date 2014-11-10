package com.spirit.bi.cubes.facturacion.dim;

import java.util.HashMap;
import java.util.Map;

import com.spirit.bi.Dimension;
import com.spirit.bi.EJBDataSource;
import com.spirit.bi.cubes.facturacion.levels.TipoClienteLevel;
import com.spirit.bi.cubes.facturacion.levels.TipoProveedorLevel;
import com.spirit.bi.entity.BiClienteDimEJB;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoProveedorIf;

public class ClienteDim extends Dimension {

	private String query = "SELECT C FROM ClienteOficinaEJB CO, ClienteEJB C where CO.clienteId=C.id AND CO.id=:clienteOfinaId";
	private HashMap<String, Object> mapaParametros = new HashMap<String, Object>();
	private TipoClienteLevel tipoClienteHelper = new TipoClienteLevel();
	private TipoProveedorLevel tipoProveedorEJB = new TipoProveedorLevel();

	@Override
	public Object construir(Object objetoConsulta) {
		Map mapaConsulta = (Map) objetoConsulta;
		BiClienteDimEJB biClienteDimEJB = new BiClienteDimEJB();
		biClienteDimEJB.setNombre((String) mapaConsulta.get("nombre"));
		biClienteDimEJB.setOrigenId((Long) mapaConsulta.get("origenId"));

		TipoClienteIf tipoClienteIf = (TipoClienteIf) mapaConsulta
				.get("tipoCliente");
		biClienteDimEJB.setTipoClienteId(tipoClienteIf.getId());
		biClienteDimEJB.setTipoCliente(tipoClienteIf.getNombre());

		TipoProveedorIf tipoProveedorIf = (TipoProveedorIf) mapaConsulta
				.get("tipoProveedor");
		biClienteDimEJB.setTipoProveedorId(tipoProveedorIf.getId());
		biClienteDimEJB.setTipoProveedor(tipoProveedorIf.getNombre());
		return biClienteDimEJB;
	}

	@Override
	public Object construirBlanco() {
		BiClienteDimEJB biClienteDimEJB = new BiClienteDimEJB();
		biClienteDimEJB.setOrigenId(ID_SIN);
		biClienteDimEJB.setNombre("SIN CLIENTE");
		biClienteDimEJB.setTipoCliente("SIN TIPO CLIENTE");
		biClienteDimEJB.setTipoProveedor("NINGUNO");
		return biClienteDimEJB;
	}

	@Override
	public Object consultar(Object origenID) {
		mapaParametros.put("clienteOfinaId", origenID);
		ClienteIf clienteIf = (ClienteIf) EJBDataSource.getJpaManagerLocal()
				.executeQuerySingle(query, mapaParametros);
		HashMap<String, Object> mapaConsulta = new HashMap<String, Object>();
		mapaConsulta.put("tipoCliente", tipoClienteHelper
				.getObjetoDimension(clienteIf.getTipoclienteId()));
		mapaConsulta.put("tipoProveedor", tipoProveedorEJB
				.getObjetoDimension(clienteIf.getTipoproveedorId()));
		mapaConsulta.put("origenId", origenID);
		mapaConsulta.put("nombre", clienteIf.getRazonSocial());
		return mapaConsulta;
	}

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return BiClienteDimEJB.class;
	}

}
