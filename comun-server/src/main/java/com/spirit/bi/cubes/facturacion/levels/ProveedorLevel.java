package com.spirit.bi.cubes.facturacion.levels;

import java.util.HashMap;

import com.spirit.bi.Dimension;
import com.spirit.bi.EJBDataSource;
import com.spirit.bi.Level;
import com.spirit.crm.entity.ClienteData;
import com.spirit.crm.entity.ClienteEJB;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;

public class ProveedorLevel extends Level {
	private String query = "SELECT C FROM ClienteOficinaEJB CO, ClienteEJB C where CO.clienteId=C.id AND CO.id=:clienteOfinaId";
	private HashMap<String, Object> mapaParametros=new HashMap<String, Object>();

	@Override
	public Class getDataClass() {
		return ClienteEJB.class;
	}

	@Override
	public Object construirBlanco() {
		ClienteData clienteData=new ClienteData();
		clienteData.setId(ID_SIN);
		clienteData.setRazonSocial("SIN PROVEEDOR");
		return clienteData;
	}


	@Override
	public Object consultar(Object origenID) {
		mapaParametros.put("clienteOfinaId", origenID);
		ClienteIf clienteIf = (ClienteIf)EJBDataSource.getJpaManagerLocal().executeQuerySingle(query, mapaParametros);
		return clienteIf;
	}
}
